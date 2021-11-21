/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.service;

import com.rntongo.bullsandcows.TestApplicationConfiguration;
import com.rntongo.bullsandcows.dao.GameDao;
import com.rntongo.bullsandcows.dao.RoundDao;
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
public class ServiceLayerTest {
    
    @Autowired
    ServiceLayer service;
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public ServiceLayerTest() {
    }
    
    @Before
    public void setUp() {
        Game game1 = new Game();
        game1.setAnswer("1356");
        game1.setFinished(false);
        gameDao.addGame(game1);
        
        Game game2 = new Game();
        game1.setAnswer("1359");
        game1.setFinished(false);
        gameDao.addGame(game2);
        
    }
    
    @After
    public void tearDown() {
        
        gameDao.deleteAllGames();
        
    }
    
    
    

    /**
     * Test of newGame method, of class ServiceLayer.
     */

    /**
     * Test of addGame method, of class ServiceLayer.
     */
    @Test
    public void testGameRound() {
        String guess = "1234";
        String answer = "1234";
        String result = service.calculateBullsAndCows(guess, answer);
        
        assertEquals("e:4:p:0", result);
    }
    

   
    
}
