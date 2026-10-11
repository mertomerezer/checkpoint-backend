package com.mertezer.checkpoint.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String passwordHash;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<UserGame> userGames;




    
    
}
