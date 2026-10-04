package com.mertezer.checkpoint.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String note;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "game_id",nullable = false)
    private Game game;
}
