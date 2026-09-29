package com.mertezer.checkpoint.controller.impl;

import com.mertezer.checkpoint.controller.ILogEntryController;
import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.LogEntryResponse;
import com.mertezer.checkpoint.service.ILogEntryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkpoint")
public class LogEntryControllerImpl implements ILogEntryController {

    private final ILogEntryService logEntryService;

    public LogEntryControllerImpl(ILogEntryService logEntryService) {
        this.logEntryService = logEntryService;
    }

    @Override
    @PostMapping("/save-not/{gameId}/logs")
    public LogEntryResponse saveNote(@RequestBody @Valid CreateLogNoteRequest createLogNoteRequest,
                                     @PathVariable Long gameId) {
        return logEntryService.saveNote(createLogNoteRequest, gameId);
    }
    @Override
    @GetMapping("/note/{gameId}")
    public List<LogEntryResponse> getNotesByGameId(@PathVariable Long gameId){
        return logEntryService.getNotesByGameId(gameId);
    }
}
