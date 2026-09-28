package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import com.mertezer.checkpoint.entity.Game;
import com.mertezer.checkpoint.entity.LogEntry;
import com.mertezer.checkpoint.repository.GameRepository;
import com.mertezer.checkpoint.repository.LogEntryRepository;
import com.mertezer.checkpoint.service.IGameService;
import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

@Service
public class GameServiceImpl implements IGameService {

    private final GameRepository gameRepository;
    private final LogEntryRepository logEntryRepository;
    public GameServiceImpl(GameRepository gameRepository,LogEntryRepository logEntryRepository){
        this.gameRepository=gameRepository;
        this.logEntryRepository=logEntryRepository;
    }
    @Override
    public GameResponse saveGame(CreateGameRequest createGameRequest){
        Game game = new Game();
        GameResponse responseGame = new GameResponse();
        BeanUtils.copyProperties(createGameRequest,game);
        Game saveGame = gameRepository.save(game);
        BeanUtils.copyProperties(saveGame,responseGame);
        return responseGame;
    }
    @Override
    @Override
    @Transactional
    public void deleteGame(Long gameId){
        Game game = gameRepository.findById(gameId).orElseThrow();
        List<LogEntry> deleteEntry = logEntryRepository.findAllByGame_Id(gameId);
        logEntryRepository.deleteAll(deleteEntry);
        gameRepository.delete(game);
    }
    @Override
    public List<GameResponse> getAllGames(){
        List<Game> allGames = gameRepository.findAll();
        List<GameResponse> allResponseGames = new ArrayList<>();
        for(int i = 0;i<allGames.size();i++){
            GameResponse responseGame = new GameResponse();
            Game saveGame = new Game();
            saveGame = allGames.get(i);
            BeanUtils.copyProperties(saveGame,responseGame);
            allResponseGames.add(responseGame);
        }
        return allResponseGames;
    }

    @Override
    public GameResponse getGameById(Long gameId){
        Game game = gameRepository.findById(gameId).orElseThrow();
        GameResponse responseGame = new GameResponse();
        BeanUtils.copyProperties(game,responseGame);
        return responseGame;
    }
}
