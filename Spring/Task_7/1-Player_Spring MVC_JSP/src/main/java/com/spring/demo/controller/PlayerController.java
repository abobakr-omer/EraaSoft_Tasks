package com.spring.demo.controller;


import com.spring.demo.model.Player;
import com.spring.demo.service.PlayerService;
import com.spring.demo.exception.ValidationException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // MCV app BE and FE are in the same project  monolithic app
//@RestController // API app
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/show-players")
    public String showPlayers(Model model){

        List<Player> players = playerService.showPlayers();

        // Model : send data to the page : Controller -> Jsp
        model.addAttribute("players",players);

        return "show-players";
    }


    @GetMapping("/add-player")
    public String addPlayer(){
        return "player-form";
    }


    /*@ModelAttribute : Convert the submitted form fields into a Player object.
                         instead of using manual Setters
                         and Spring matches the values
                         because the HTML name values match the properties of your Player class
                          */
    @PostMapping("/save-player")
    public String savePlayer(@ModelAttribute("player") Player player,
                             BindingResult bindingResult, Model model, HttpServletResponse response){

        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            model.addAttribute("error", "Please enter valid numbers for the player number and salary.");
            return "player-form";
        }

        try {
            // An ID in the form means the player already exists, so this is an edit.
            if (player.getId() != null) {
                playerService.updatePlayer(player.getId(), player);
            } else {
                playerService.savePlayer(player);
            }
        } catch (ValidationException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            model.addAttribute("error", exception.getMessage());
            return "player-form";
        }

        return "redirect:/player/show-players";
    }

    @PostMapping("/delete-player/{id}")
    public String deletePlayer(@PathVariable("id") Long id){

        playerService.deletePlayer(id);

        return "redirect:/player/show-players";
    }


    @GetMapping("/show-player/{id}")
    public String showPlayer(@PathVariable("id") Long id, Model model){

        Player player =playerService.showPlayer(id);


        model.addAttribute("player",player);

        return "show-player";
    }

    @GetMapping("/edit-player/{id}")
    public String editPlayer(@PathVariable("id") Long id , Model model){

        Player player=playerService.showPlayer(id);


        model.addAttribute("player",player);

        return "player-form";

    }




}
