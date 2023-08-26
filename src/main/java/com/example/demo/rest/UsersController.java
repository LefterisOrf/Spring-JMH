package com.example.demo.rest;


import com.example.demo.business.beans.UsersBean;
import com.example.demo.persistence.dtos.UserDTO;
import com.example.demo.persistence.entities.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UsersController {

    private final UsersBean usersBean;

    public UsersController(UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    @PostMapping("/users/save")
    public UserDTO save(@RequestBody User user) {
        return usersBean.save(user);
    }

    @PatchMapping("/users/edit")
    public UserDTO edit(@RequestBody User user) {
        return usersBean.edit(user);
    }

    @GetMapping("/users")
    public List<UserDTO> get(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        return usersBean.getUsersByDateRange(from, to);
    }

    @DeleteMapping("/users")
    public String delete() {
        usersBean.deleteAll();
        return "Deleted all users";
    }

}
