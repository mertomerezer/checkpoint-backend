package com.mertezer.checkpoint.repository;

import com.mertezer.checkpoint.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry,Long> {
    List<LogEntry> findAllByGame_Id(Long gameId);


}
