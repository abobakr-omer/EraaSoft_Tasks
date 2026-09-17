package com.spring.demo.service;

import com.spring.demo.model.Player;

import java.util.List;

public interface PlayerService {


    Player savePlayer(Player player);

    Player showPlayer(Long id);

    List<Player> getAllPlayers();

    void deletePlayer(Long id);

    Player updatePlayer(Long id,Player player);


}
