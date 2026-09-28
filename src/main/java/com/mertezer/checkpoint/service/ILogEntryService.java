package com.mertezer.checkpoint.service;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;

public interface ILogEntryService {
    LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest, Long gameId);
}
