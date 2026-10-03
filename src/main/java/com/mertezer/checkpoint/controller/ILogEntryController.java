package com.mertezer.checkpoint.controller;

import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;

import java.util.List;

public interface ILogEntryController {
    LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest, Long gameId);
    List<LogEntryResponse> getNotesByGameId(Long gameId);
    void deleteNote(Long logEntryId);
}