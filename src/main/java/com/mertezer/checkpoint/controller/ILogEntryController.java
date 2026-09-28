package com.mertezer.checkpoint.controller;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;

public interface ILogEntryController {
    LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest, Long gameId);
}
