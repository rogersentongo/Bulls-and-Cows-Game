/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dao;

import com.rntongo.bullsandcows.TestApplicationConfiguration;
import com.rntongo.bullsandcows.dto.Game;
import com.rntongo.bullsandcows.dto.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rogersentongo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoDBTest {
    
    @Autowired
    RoundDao roundDao;
    
    //We need access to the gameDao as well since we need to access it
    @Autowired
    GameDao gameDao;
    
    public RoundDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        
    }
    
    @Before
    public void setUp() {
        gameDao.deleteAllGames();
        
        
    }
    
    @After
    public void tearDown() {
        
    }
    
   

    /**
     * Test of getAllRoundsByGameId method, of class RoundDaoDB.
     */
    @Test
    public void testGetAdd() {
        
        //we create a game id
        //int gameId = 5;
        
        //We create a game
        Game game = new Game();
        game.setAnswer("2356");
        game.setFinished(false);
        //we add the game to the gameDao
        game = gameDao.addGame(game);
        int gameId = game.getGameId();
        
        //We create a new Round
        Round round = new Round();
        round.setGuess("3899");
        round.setResult("e:0:p:0");
        round.setGameId(gameId);
        
        //we add it to our Rounddao
        roundDao.addRound(round);
        
        //we create another round
        Round round2 = new Round();
        //we make the same guess
        round2.setGuess("3899");
        round2.setResult("e:0:p:0");
        //we set the game id the same since its the same game
        round2.setGameId(gameId);
        
        //Add the round to the game
        roundDao.addRound(round2);
        
        //Retrieve all rounds by gameId
        List<Round> retrieved = roundDao.getAllRoundsByGameId(gameId);
        
        //ASSERTIONS
        assertEquals(2, retrieved.size());
        
        
        
    }
    
}
