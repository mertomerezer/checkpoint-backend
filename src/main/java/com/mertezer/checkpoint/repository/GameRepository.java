package com.mertezer.checkpoint.repository;

import com.mertezer.checkpoint.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

}
