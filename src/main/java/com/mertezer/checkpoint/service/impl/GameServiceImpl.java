package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import com.mertezer.checkpoint.entity.Game;
import com.mertezer.checkpoint.exception.ResourceNotFoundException;
import com.mertezer.checkpoint.repository.GameRepository;
import com.mertezer.checkpoint.service.IGameService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements IGameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameResponse saveGame(CreateGameRequest request) {
        Game game = new Game();
        GameResponse response = new GameResponse();
        BeanUtils.copyProperties(request, game);
        Game savedGame = gameRepository.save(game);
        BeanUtils.copyProperties(savedGame, response);
        return response;
    }

    @Override
    public void deleteGame(Long gameId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Game deletion is temporarily unavailable while user-game relationships are being implemented.");
    }

    @Override
    public List<GameResponse> getAllGames() {
        List<GameResponse> responses = new ArrayList<>();
        for (Game game : gameRepository.findAll()) {
            GameResponse response = new GameResponse();
            BeanUtils.copyProperties(game, response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GameResponse getGameById(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + gameId));
        GameResponse response = new GameResponse();
        BeanUtils.copyProperties(game, response);
        return response;
    }
}
