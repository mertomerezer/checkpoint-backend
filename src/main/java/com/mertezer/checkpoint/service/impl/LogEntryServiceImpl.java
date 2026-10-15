package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;
import com.mertezer.checkpoint.entity.LogEntry;
import com.mertezer.checkpoint.exception.ResourceNotFoundException;
import com.mertezer.checkpoint.repository.LogEntryRepository;
import com.mertezer.checkpoint.service.ILogEntryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LogEntryServiceImpl implements ILogEntryService {
    private final LogEntryRepository logEntryRepository;

    public LogEntryServiceImpl(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    @Override
    public LogEntryResponse saveNote(CreateLogNoteRequest request, Long gameId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Note creation is temporarily unavailable while user-game relationships are being implemented.");
    }

    @Override
    public List<LogEntryResponse> getNotesByGameId(Long gameId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Note listing is temporarily unavailable while user-game relationships are being implemented.");
    }

    @Override
    public void deleteNote(Long logEntryId) {
        LogEntry deleteLog = logEntryRepository.findById(logEntryId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + logEntryId));
        logEntryRepository.delete(deleteLog);
    }

    @Override
    public LogEntryResponse updateNote(CreateLogNoteRequest request, Long logEntryId) {
        LogEntry oldNote = logEntryRepository.findById(logEntryId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + logEntryId));
        BeanUtils.copyProperties(request, oldNote);
        LogEntry updatedNote = logEntryRepository.save(oldNote);
        LogEntryResponse response = new LogEntryResponse();
        BeanUtils.copyProperties(updatedNote, response);
        return response;
    }
}
