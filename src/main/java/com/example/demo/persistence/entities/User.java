package com.example.demo.persistence.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@Builder
@Document("USERS")
public class User {

    @Id
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private LocalDate dob;

    @NonNull
    private String password;

}
