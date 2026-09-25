package com.mertezer.checkpoint.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class LogEntryResponse {
    private Long id;
    private String note;
    private LocalDateTime createdAt;
}
