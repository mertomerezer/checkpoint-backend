package com.mertezer.checkpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateLogNoteRequest {
    @NotBlank
    @Size(max = 2000)
    private String note;

}
