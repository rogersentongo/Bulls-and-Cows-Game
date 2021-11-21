/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.controller;

import com.rntongo.bullsandcows.dto.Game;
import com.rntongo.bullsandcows.dto.Round;
import com.rntongo.bullsandcows.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rogersentongo
 */
@RestController
@RequestMapping("/api/")
public class controller {
    
    @Autowired
    ServiceLayer service;
    
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.newGame();
    }
    
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round)
    {
        return service.makeAGuess(round);
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return service.getAllGames();
    }
    
    @GetMapping("/game/{game_id}")
    public Game getGameById(@PathVariable("game_id") int gameId)
    {
        return service.getGameById(gameId);
    }
    
    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") int gameId)
    {
        return service.getAllRoundsByGameId(gameId);
    }
    
    
}
