package com.example.demo.persistence.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String username;
    private String email;
    private LocalDate dob;
}
