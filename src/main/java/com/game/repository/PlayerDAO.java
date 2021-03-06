package com.game.repository;

import com.game.entity.DtoPlayerRequestParam;
import com.game.entity.Player;
import com.game.entity.PlayerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class PlayerDAO {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Player> getPlayers(DtoPlayerRequestParam param) {
    return jdbcTemplate.query(param.getSQLQuery(), new PlayerRowMapper());
  }
}
