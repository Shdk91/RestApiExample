package com.game.controller;

import com.game.entity.DtoPlayerCreate;
import com.game.entity.Player;
import com.game.entity.DtoPlayerRequestParam;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @GetMapping("/players")
  public List<Player> getPlayers(@ModelAttribute DtoPlayerRequestParam param) {
    return playerService.getPlayers(param);
  }

  @GetMapping("/players/count")
  public Integer getPlayersCount(@ModelAttribute DtoPlayerRequestParam param) {
    return playerService.getPlayersCount(param);
  }

  @GetMapping("/players/{id}")
  public Player getPlayer(@PathVariable Long id) {
    return playerService.getPlayer(id);
  }

  @PostMapping("/players")
  public Player createPlayer( @RequestBody DtoPlayerCreate dtoPlayerCreate) {
    System.out.println(dtoPlayerCreate);
    Player player = playerService.createNewPlayer(dtoPlayerCreate);
    System.out.println(player);
    return player;
  }

  @DeleteMapping("/players/{id}")
  public HttpStatus deletePlayer(@PathVariable Long id) {
    playerService.deletePlayer(id);
    return HttpStatus.OK;
  }

  @PostMapping("/players/{id}")
  public Player createPlayer(@Valid @RequestBody Player player, @PathVariable Long id) {
    playerService.editPlayer(player, id);
    return player;
  }
}
