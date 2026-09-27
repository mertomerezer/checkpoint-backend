package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.CreateGameRequest;
import com.mertezer.checkpoint.dto.CreateLogNoteRequest;
import com.mertezer.checkpoint.dto.GameResponse;
import com.mertezer.checkpoint.dto.LogEntryResponse;
import com.mertezer.checkpoint.entity.Game;
import com.mertezer.checkpoint.entity.LogEntry;
import com.mertezer.checkpoint.repository.GameRepository;
import com.mertezer.checkpoint.repository.LogEntryRepository;
import com.mertezer.checkpoint.service.IGameService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
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
    public LogEntryResponse saveNote(CreateLogNoteRequest createLogNoteRequest,Long gameId){
        Game game = gameRepository.findById(gameId).orElseThrow(); //Verilen idye ait oyunu bulduk eğer oyun yoksa metot burada durur.varsa game nesnesinden değişken oluşur
        LogEntry newLog = new LogEntry();
        LogEntryResponse saveEntry = new LogEntryResponse();
        BeanUtils.copyProperties(createLogNoteRequest,newLog);
        newLog.setGame(game);
        newLog.setCreatedAt(LocalDateTime.now());
        LogEntry savedLog = logEntryRepository.save(newLog);
        BeanUtils.copyProperties(savedLog,saveEntry);
        return saveEntry;
    }
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
}
