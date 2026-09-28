package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;
import com.mertezer.checkpoint.entity.Game;
import com.mertezer.checkpoint.entity.LogEntry;
import com.mertezer.checkpoint.repository.GameRepository;
import com.mertezer.checkpoint.repository.LogEntryRepository;
import com.mertezer.checkpoint.service.ILogEntryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogEntryServiceImpl implements ILogEntryService {

    private final GameRepository gameRepository;
    private final LogEntryRepository logEntryRepository;

    public LogEntryServiceImpl(GameRepository gameRepository, LogEntryRepository logEntryRepository) {
        this.gameRepository = gameRepository;
        this.logEntryRepository = logEntryRepository;
    }

    @Override
    public LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        LogEntry newLog = new LogEntry();
        BeanUtils.copyProperties(createLogNoteRequest, newLog);
        newLog.setGame(game);
        newLog.setCreatedAt(LocalDateTime.now());

        LogEntry savedLog = logEntryRepository.save(newLog);
        LogEntryResponse response = new LogEntryResponse();
        BeanUtils.copyProperties(savedLog, response);
        return response;
    }
}
