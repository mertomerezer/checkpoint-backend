package com.mertezer.checkpoint.service;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.GameResponse;

import java.util.List;


public interface IGameService {
    public GameResponse saveGame(CreateGameRequest createGameRequest);
    public void deleteGame(Long gameId);
    public List<GameResponse> getAllGames();
    public GameResponse getGameById(Long gameId);





}
