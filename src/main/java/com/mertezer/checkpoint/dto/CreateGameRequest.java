package com.mertezer.checkpoint.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor

public class CreateGameRequest {

    private String name;
    private String genre;
    private String producer;
    private LocalDate releaseDate;
}
