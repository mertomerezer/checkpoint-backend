package com.mertezer.checkpoint.service;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import com.mertezer.checkpoint.dto.LogEntryResponse;

import java.util.List;


public interface IGameService {
    public GameResponse saveGame(CreateGameRequest createGameRequest);
    public LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest,Long gameId);
    public void deleteGame(Long gameId);
    public List<GameResponse> getAllGames();
    public GameResponse getGameById(Long gameId);





}
