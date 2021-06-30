package com.game.controller;

import com.game.entity.DtoPlayerCreateOrUpdate;
import com.game.entity.Player;
import com.game.entity.DtoPlayerRequestParam;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/rest")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @GetMapping("/players")
  public List<Player> getPlayers(@ModelAttribute DtoPlayerRequestParam param) {
    int pageSize = (param.getPageSize() == null) ? 3 : param.getPageSize();
    int pageNumber = (param.getPageNumber() == null) ? 0 : param.getPageNumber();

    List<Player> players = playerService.getPlayers(param);

    int indexOfBeginPage = pageNumber * pageSize;
    int indexOfEndPage = Math.min((pageNumber + 1) * pageSize, players.size());

    return players.subList(indexOfBeginPage, indexOfEndPage);
  }

  @GetMapping("/players/count")
  public Integer getPlayersCount(@ModelAttribute DtoPlayerRequestParam param) {
    return playerService.getPlayersCount(param);
  }

  @GetMapping("/players/{id}")
  public Player getPlayer(@PathVariable Long id) {
    if (id < 1) {
      throw new RuntimeException();
    }
    return playerService.getPlayer(id);
  }

  @PostMapping("/players")
  public Player createPlayer(@RequestBody DtoPlayerCreateOrUpdate dtoPlayerCreateOrUpdate) {
    Player player = new Player();

    player.setName(dtoPlayerCreateOrUpdate.getName());
    player.setTitle(dtoPlayerCreateOrUpdate.getTitle());
    player.setRace(dtoPlayerCreateOrUpdate.getRace());
    player.setProfession(dtoPlayerCreateOrUpdate.getProfession());
    player.setBirthday(new Date(dtoPlayerCreateOrUpdate.getBirthday()));
    player.setBanned(
        dtoPlayerCreateOrUpdate.getBanned() != null && dtoPlayerCreateOrUpdate.getBanned());
    player.setExperience(dtoPlayerCreateOrUpdate.getExperience());

    playerService.createOrUpdatePlayer(player);
    return player;
  }

  @DeleteMapping("/players/{id}")
  public HttpStatus deletePlayer(@PathVariable Long id) {
    playerService.deletePlayer(id);
    return HttpStatus.OK;
  }

  @PostMapping("/players/{id}")
  public Player updatePlayer(@RequestBody DtoPlayerCreateOrUpdate dtoPlayerCreateOrUpdate,
      @PathVariable Long id) {
    Player player = getPlayer(id);

    if (dtoPlayerCreateOrUpdate.getName() != null) {
      player.setName(dtoPlayerCreateOrUpdate.getName());
    }
    if (dtoPlayerCreateOrUpdate.getTitle() != null) {
      player.setTitle(dtoPlayerCreateOrUpdate.getTitle());
    }
    if (dtoPlayerCreateOrUpdate.getRace() != null) {
      player.setRace(dtoPlayerCreateOrUpdate.getRace());
    }
    if (dtoPlayerCreateOrUpdate.getProfession() != null) {
      player.setProfession(dtoPlayerCreateOrUpdate.getProfession());
    }
    if (dtoPlayerCreateOrUpdate.getBirthday() != null) {
      player.setBirthday(new Date(dtoPlayerCreateOrUpdate.getBirthday()));
    }
    if (dtoPlayerCreateOrUpdate.getExperience() != null) {
      player.setExperience(dtoPlayerCreateOrUpdate.getExperience());
    }
    if (dtoPlayerCreateOrUpdate.getBanned() != null) {
      player.setBanned(dtoPlayerCreateOrUpdate.getBanned());
    }

    playerService.createOrUpdatePlayer(player);
    return player;
  }
}
