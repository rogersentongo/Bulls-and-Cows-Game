/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dao;

import com.rntongo.bullsandcows.dto.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rogersentongo
 */

//Tells spring this is a class worth look at for storage retrieval etc
@Repository
public class GameDaoDB implements GameDao {
    
    //Needs access to the database
    @Autowired
    //We add a member variable for jdbc template which is our api for accessing sql databases
    private JdbcTemplate jdbc;
    
    //After this we can start inserting overriding the interface methods

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM Game;";
        
        //we need to create a rowmapper for the game table
        
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        //we need to create an sql script for the query to get by ID
        final String SELECT_GAME_BYID = "SELECT * FROM Game "
                + "WHERE game_id = ?;";
        
        //When asking for a single item we use queryForObject
        return jdbc.queryForObject(SELECT_GAME_BYID, new GameMapper(), gameId);
    }

    @Override
    public Game addGame(Game game) {
        //We create our sql statement
        final String ADD_GAME = "INSERT INTO Game(answer) VALUES(?);";
        
        //We use update to update the database with an add of a game
        jdbc.update(ADD_GAME, game.getAnswer());
        
        //We retrieve the id of the game thats been added
        //We use the queryfor object method then use the special arguments to get
        //the id of the last inserted object/ record
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        //We set the id for the game object
        game.setGameId(newGameId);
        
        return game;
        
        
    }

    
    //In this exercise we update game by setting it to finished.
    @Override
    public void updateGame(Game game) {
        //we create an sql script
        //We use update and set to set the id
        final String UPDATE_GAME = "UPDATE Game SET finished = ?"
                + "WHERE game_id = ?";
        
        jdbc.update(UPDATE_GAME, game.isFinished(), game.getGameId());
        
        
    }

    @Override
    public void deleteGame(int gameId) {
        final String DELETE_ROUND = "DELETE FROM Round WHERE game_id = ?";
        jdbc.update(DELETE_ROUND, gameId);
        
        
        final String DELETE_GAME = "DELETE FROM Game WHERE game_id = ?";
        
        jdbc.update(DELETE_GAME, gameId);
    }
    
    @Override
    public void deleteAllGames() {
        final String DELETE_ROUND = "DELETE FROM Round";
        jdbc.update(DELETE_ROUND);
        
        
        final String DELETE_GAME = "DELETE FROM Game";
        
        jdbc.update(DELETE_GAME);
    }
    
    //RowMapper for Game table
    private static final class GameMapper implements RowMapper<Game>{

        //We override the mapRow method
        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            //We create a new game object
            Game gameObj = new Game();
            
            //Set the values for the game object
            gameObj.setGameId(rs.getInt("game_id"));
            gameObj.setAnswer(rs.getString("answer"));
            gameObj.setFinished(rs.getBoolean("finished"));
            
            //We return the object
            return gameObj;
            
        }
        
        
        
        
        
    }
}
