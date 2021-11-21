/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dao;

import com.rntongo.bullsandcows.dto.Round;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public interface RoundDao {
    
    //Get all Rounds
    List<Round> getAllRoundsByGameId(int gameId);
    
    //get a single round by round id
    Round getRoundById(int rountId);
    
    //Add a round
    Round addRound(Round round);
    
    void deleteRoundById(int roundId);
    
    
}
