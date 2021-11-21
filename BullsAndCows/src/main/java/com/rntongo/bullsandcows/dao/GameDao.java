/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dao;

import com.rntongo.bullsandcows.dto.Game;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public interface GameDao {
    
    //Returns all games
    List<Game> getAllGames();
    
    //Get a single game by id
    Game getGameById(int gameId);
    
    //Add a game
    Game addGame(Game game);
    
    //update a game
    void updateGame(Game round);
    
    void deleteGame(int gameId);
    
    void deleteAllGames();
    
}
