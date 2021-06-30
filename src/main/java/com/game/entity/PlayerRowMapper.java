package com.game.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlayerRowMapper implements RowMapper<Player> {

  @Override
  public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
    Player player = new Player();

    player.setId(rs.getLong("id"));
    player.setName(rs.getString("name"));
    player.setTitle(rs.getString("title"));
    player.setRace(Race.valueOf(rs.getString("race")));
    player.setProfession(Profession.valueOf(rs.getString("profession")));

    try {
      player.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
          .parse(rs.getString("birthday")));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    player.setBanned(rs.getBoolean("banned"));
    player.setExperience(rs.getInt("experience"));
    player.setLevel(rs.getInt("level"));
    player.setUntilNextLevel(rs.getInt("untilNextLevel"));

    return player;
  }
}
