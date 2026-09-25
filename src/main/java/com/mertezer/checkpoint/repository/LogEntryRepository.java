package com.mertezer.checkpoint.repository;

import com.mertezer.checkpoint.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry,Long> {

}
