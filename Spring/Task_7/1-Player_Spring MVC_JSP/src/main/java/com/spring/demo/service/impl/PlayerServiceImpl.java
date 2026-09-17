package com.spring.demo.service.impl;

import com.spring.demo.model.Player;
import com.spring.demo.repo.PlayerRepo;
import com.spring.demo.service.PlayerService;
import com.spring.demo.exception.ValidationException;
import com.spring.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Transactional
    public void savePlayer(Player player) {
        validatePlayer(player);

        Player playerToSave = new Player();
        copyValues(player, playerToSave);

        playerRepo.save(playerToSave);
    }

    @Override
    @Transactional
    public void updatePlayer(Long id, Player player) {
        validateId(id);
        validatePlayer(player);

        if (player.getId() != null && !id.equals(player.getId())) {
            throw new ValidationException("Player form ID must match the path ID.");
        }

        Player existingPlayer = showPlayer(id);
        copyValues(player, existingPlayer);

        playerRepo.save(existingPlayer);
    }

    @Override
    public Player showPlayer(Long id) {

        validateId(id);
        return playerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found: " + id));
    }

    @Override
    public List<Player> showPlayers() {

       List<Player> players = playerRepo.findAll();

        return players;
    }

    @Override
    @Transactional
    public void deletePlayer(Long id) {
       playerRepo.delete(showPlayer(id));
    }


    private void validatePlayer(Player player) {
        if (player == null) {
            throw new ValidationException("Player is required.");
        }
        if (player.getName() == null || player.getName().isBlank()
                || player.getName().strip().length() > 255) {
            throw new ValidationException("Name is required and must contain at most 255 characters.");
        }
        if (player.getNumber() == null || player.getNumber() < 0) {
            throw new ValidationException("Player number is required and must be zero or greater.");
        }
        if (player.getSalary() == null || !Double.isFinite(player.getSalary())
                || player.getSalary() < 0) {
            throw new ValidationException("Salary must be a valid number greater than or equal to zero.");
        }
    }

    private void copyValues(Player source, Player target) {
        target.setName(source.getName().strip());
        target.setNumber(source.getNumber());
        target.setSalary(source.getSalary());
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Player ID must be positive.");
        }
    }
}
