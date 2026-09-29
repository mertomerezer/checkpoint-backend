package com.mertezer.checkpoint.service;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;

import java.util.List;

public interface ILogEntryService {
    LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest, Long gameId);
     List<LogEntryResponse> getNotesByGameId(Long gameId);
}
