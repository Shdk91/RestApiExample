package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> getPlayers(){
        List<Player> players = playerService.getPlayers();
        return players;
    }
}
