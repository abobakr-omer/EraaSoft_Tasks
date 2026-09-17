package com.spring.demo.service.impl;

import com.spring.demo.model.Player;
import com.spring.demo.repo.PlayerRepo;
import com.spring.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.demo.exception.ValidationException;
import com.spring.demo.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

   private final PlayerRepo playerRepo;


   @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }


    @Override
    public Player savePlayer(Player player) {
       validatePlayer(player);
       if (player.getId() != null) {
           throw new ValidationException("A new player must not have an ID");
       }
       return playerRepo.save(player);
    }

    @Override
    public Player showPlayer(Long id) {
       validateId(id);
       return playerRepo.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("Player not found: " + id));
    }

    @Override
    public List<Player> getAllPlayers() {
       return playerRepo.findAll();
    }

    @Override
    @Transactional
    public void deletePlayer(Long id) {
       playerRepo.delete(showPlayer(id));
    }

    @Override
    @Transactional
    public Player updatePlayer(Long id,Player player) {
       validateId(id);
       validatePlayer(player);
       if (player.getId() != null && !id.equals(player.getId())) {
           throw new ValidationException("Player body ID must match the path ID");
       }
       Player existingPlayer = showPlayer(id);

       existingPlayer.setName(player.getName());
       existingPlayer.setNumber(player.getNumber());
       existingPlayer.setSalary(player.getSalary());

       return playerRepo.save(existingPlayer);
    }


    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Player ID must be positive");
        }
    }

    private void validatePlayer(Player player) {
        if (player == null) {
            throw new ValidationException("Player is required");
        }
        if (player.getName() == null || player.getName().isBlank() || player.getName().length() > 255) {
            throw new ValidationException("Player name is required and must not exceed 255 characters");
        }
        if (player.getNumber() == null || player.getNumber() < 0) {
            throw new ValidationException("Player number is required and must be zero or greater");
        }
        if (player.getSalary() == null || !Double.isFinite(player.getSalary()) || player.getSalary() < 0) {
            throw new ValidationException("Player salary is required and must be a finite, non-negative number");
        }
    }
}
