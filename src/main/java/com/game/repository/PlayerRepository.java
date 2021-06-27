package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.SplittableRandom;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
