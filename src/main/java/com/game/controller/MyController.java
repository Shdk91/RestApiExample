package com.game.controller;

import com.game.entity.Player;
import com.game.entity.DTOPlayerRequestParam;
import com.game.exception_handling.NoSuchPlayerException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class MyController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> getPlayers(@ModelAttribute DTOPlayerRequestParam param){

        List<Player> players = playerService.getPlayers(param );
        return players;
    }

    @GetMapping("/players/count")
    public Integer getPlayersCount(@ModelAttribute DTOPlayerRequestParam param){
        return playerService.getPlayersCount(param);
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable Long id){
        Player player = playerService.getPlayer(id);

        return player;
    }
    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player player){
        playerService.createNewPlayer(player);
        return player;
    }

    @DeleteMapping("/players/{id}")
    public HttpStatus deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);

        return HttpStatus.OK;
    }

    @PostMapping("/players/{id}")
    public Player createPlayer(@Valid @RequestBody Player player, @PathVariable Long id){
        playerService.editPlayer(player, id);
        return player;
    }
}
