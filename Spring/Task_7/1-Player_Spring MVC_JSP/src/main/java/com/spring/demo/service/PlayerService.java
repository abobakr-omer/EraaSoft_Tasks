package com.spring.demo.service;

import com.spring.demo.model.Player;

import java.util.List;

public interface PlayerService {


    void savePlayer(Player player);

    void updatePlayer(Long id, Player player);

    Player showPlayer(Long id);

    List<Player> showPlayers();

    void deletePlayer(Long id);



}
