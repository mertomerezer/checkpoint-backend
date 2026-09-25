package com.mertezer.checkpoint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateLogNoteRequest {
    @NotBlank
    private String note;

}
