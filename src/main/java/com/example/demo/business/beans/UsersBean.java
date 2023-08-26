package com.example.demo.business.beans;

import com.example.demo.persistence.dtos.UserDTO;
import com.example.demo.persistence.entities.User;
import com.example.demo.persistence.repositories.UsersRepository;
import com.example.demo.rest.exceptions.UserAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBean.class);

    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    public UsersBean(UsersRepository usersRepository, PasswordEncoder encoder, ObjectMapper objectMapper) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    public UserDTO save(User user) {
        if (user == null) {
            throw new RuntimeException("No user provided.");
        }

        checkUsernameAndEmail(user);

        user.setPassword(encoder.encode(user.getPassword()));

        return convert(usersRepository.save(user)) ;
    }

    public UserDTO edit(User user) {
        if (user == null) {
            throw new RuntimeException("No user provided");
        }

        User entity = findById(user.getId());
        if ( entity == null) {
            throw new RuntimeException("No user exists with id: " + user.getId());
        }

        checkUsernameAndEmail(user);

        if ( !ObjectUtils.isEmpty(user.getPassword())) {
            LOGGER.info("Updated user's: " + user.getId() + " password.");
            entity.setPassword(encoder.encode(user.getPassword()));
        }

        // Update changeable fields
        entity.setDob(user.getDob());
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getUsername());

        return convert(usersRepository.save(entity));
    }

    public List<UserDTO> getUsersByDateRange(LocalDate from, LocalDate to) {
        return usersRepository
                .findUserByDateRange(from, to)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public void deleteById( String id) {
        LOGGER.info("Deleting user with id: " + id);
        usersRepository.deleteById(id);
    }

    public void deleteAll() {
        LOGGER.warn("DELETING ALL USERS");
        usersRepository.deleteAll();
    }

    private User findById(String id) {
        return usersRepository.findById(id).orElse(null);
    }

    private UserDTO convert(User user) {
        return objectMapper.convertValue(user, UserDTO.class);
    }

    private void checkUsernameAndEmail(User user) {
        if ( !CollectionUtils.isEmpty(usersRepository.findUserByEmail(user.getEmail()))) {
            throw new UserAlreadyExistsException("User with email: " + user.getEmail() + " already exists.");
        }

        if ( !CollectionUtils.isEmpty(usersRepository.findUserByUsername(user.getUsername()))) {
            throw new UserAlreadyExistsException("Username: " + user.getUsername() + " already exists.");
        }
    }
}
