package com.game.repository;

import com.game.entity.DTOPlayerRequestParam;
import com.game.entity.Player;
import com.game.entity.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerDAO {


    @Autowired
    private  JdbcTemplate jdbcTemplate;

    public List<Player> getPlayers(DTOPlayerRequestParam param) {
         return jdbcTemplate.query(param.getSQLQuery(), new PlayerMapper());
    }
}
