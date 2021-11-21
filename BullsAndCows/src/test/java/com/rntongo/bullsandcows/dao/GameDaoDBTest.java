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
public class GameDaoDBTest {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public GameDaoDBTest() {
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
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        //We create two game objects
        Game game = new Game();
        game.setAnswer("2356");
        game.setFinished(false);
        gameDao.addGame(game);
        
        Game game2 = new Game();
        game.setAnswer("2357");
        game.setFinished(false);
        gameDao.addGame(game);
        
        List<Game> ourGames = gameDao.getAllGames();
        
        assertEquals(2, ourGames.size());
        assertTrue(ourGames.contains(game));
        
    }

    

    /**
     * Test of addGame method, of class GameDaoDB.
     */
    @Test
    public void testAddGetGame() {
        //We initialize our game object
        Game game = new Game();
        game.setAnswer("2356");
        game.setFinished(false);
        gameDao.addGame(game);
        
        //we get the game from the dao
        Game retrieved = gameDao.getGameById(game.getGameId());
        
        //We assert that the game is equals
        assertEquals(game, retrieved);
    }

    /**
     * Test of updateGame method, of class GameDaoDB.
     */
    @Test
    public void testUpdateGame() {
        
        //We create a new game and add it to the DB
        Game game = new Game();
        game.setAnswer("2356");
        game.setFinished(false);
        gameDao.addGame(game);
        
        //we get that same game
        Game retrieved = gameDao.getGameById(game.getGameId());
        
        //We check that they are both equal
        assertEquals(retrieved, game);
        
        
        //we set retrieved to finished true
        retrieved.setFinished(true);
        
        //we assert not equals
        assertNotEquals(retrieved, game);        
        
        
    }
    
}
