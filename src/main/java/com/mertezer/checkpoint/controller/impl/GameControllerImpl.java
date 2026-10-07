package com.mertezer.checkpoint.controller.impl;

import com.mertezer.checkpoint.controller.IGameController;
import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import com.mertezer.checkpoint.service.IGameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkpoint")
public class GameControllerImpl implements IGameController {

    private final IGameService gameService;
    public GameControllerImpl(IGameService gameService) {
        this.gameService = gameService;
    }

    @Override
    @PostMapping("/games")
    public GameResponse saveGame (@RequestBody @Valid CreateGameRequest createGameRequest){
        return gameService.saveGame(createGameRequest);
    }
    @Override
    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId){
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
    @Override
    @GetMapping("/games")
    public List<GameResponse> getAllGames(){
        return gameService.getAllGames();
    }

    @Override
    @GetMapping("/games/{gameId}")
    public GameResponse getGameById(@PathVariable Long gameId){
        return gameService.getGameById(gameId);
    }





}
