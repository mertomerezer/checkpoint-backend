package com.mertezer.checkpoint.controller;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameController {
    public GameResponse saveGame(CreateGameRequest createGameRequest);
    public ResponseEntity<Void> deleteGame(Long gameId);
    public List<GameResponse> getAllGames();
    public GameResponse getGameById(Long gameId);


}
