package com.mertezer.checkpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor

public class CreateGameRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String genre;

    @Size(max = 255)
    private String producer;

    private LocalDate releaseDate;
}
