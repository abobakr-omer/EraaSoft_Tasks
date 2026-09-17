package com.spring.demo.controller;

import com.spring.demo.model.Player;
import com.spring.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/show-player/{id}")
    public Player getPlayer(@PathVariable Long id){
       return playerService.showPlayer(id);
    }

    @GetMapping("/all-players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PostMapping("/save-player")
    @ResponseStatus(HttpStatus.CREATED)
    public Player savePlayer(@RequestBody Player player){
        return playerService.savePlayer(player);
    }

    @DeleteMapping("/delete-player/{id}")
    public void deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
    }

    @PutMapping("/update-player/{id}")
    public Player updatePlayer(@PathVariable Long id,@RequestBody Player player){
        return playerService.updatePlayer(id,player);
    }





}
