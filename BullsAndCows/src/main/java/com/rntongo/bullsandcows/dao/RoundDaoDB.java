/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dao;

import com.rntongo.bullsandcows.dto.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rogersentongo
 */

//We add repository so Spring knows to use this class as a dependency
@Repository
public class RoundDaoDB implements RoundDao {
    
    //We create our jdbc object
    @Autowired
    JdbcTemplate jdbc;
    
    //We override the methods

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM Round"
                + " WHERE game_game_id = ?";
        
        List<Round> rounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameId);
        
        return rounds;
    }

    @Override
    public Round getRoundById(int roundId) {
        final String SELECT_ROUND_BYID = "SELET * FROM Round "
                + "WHERE round_id = ?;";
        
        return jdbc.queryForObject(SELECT_ROUND_BYID, new RoundMapper(), roundId);
    }

    @Override
    public Round addRound(Round round) {
        
        //We only insert three values because round id and guess time is automatically inserted
        final String INSERT_ROUND = "INSERT INTO Round"
                + " (game_game_id, guess, result) VALUES(?,?,?);";
        
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getResult());
        
        //Get the round id from the table
        int roundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(roundId);
        
        return round;
        
    }

    @Override
    public void deleteRoundById(int roundId) {
        final String DELETE_ROUND = "DELETE FROM Round WHERE round_id = ?";
        jdbc.update(DELETE_ROUND, roundId);
    }
    
    //We implement roundmapper
    private static final class RoundMapper implements RowMapper<Round>{

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            //We create a round object
            Round roundObj = new Round();
            
            //We populate the round object
            roundObj.setGameId(rs.getInt("game_game_id"));
            roundObj.setRoundId(rs.getInt("Round_id"));
            roundObj.setGuess(rs.getString("guess"));
            
            Timestamp timestamp = rs.getTimestamp("guess_time");
            
            //Why do we set it back to local date time?
            roundObj.setGuessTime(timestamp.toLocalDateTime());
            roundObj.setResult(rs.getString("result"));
            
            return roundObj;
        }
        
        
        
    }
    
}
