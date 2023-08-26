package com.example.demo.persistence.repositories;

import com.example.demo.persistence.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {

    public List<User> findUserByUsername( String username);

    public List<User> findUserByEmail( String email);

    @Query("{ dob: { $gte: ?0, $lte: ?1 }}")
    public List<User> findUserByDateRange( LocalDate from, LocalDate to);

}
