/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.service;

import com.rntongo.bullsandcows.dao.GameDao;
import com.rntongo.bullsandcows.dao.RoundDao;
import com.rntongo.bullsandcows.dto.Game;
import com.rntongo.bullsandcows.dto.Round;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rogersentongo
 */
//Lets Spring Boot know this is a service
@Service
public class ServiceLayer {
    
    //Create the two DAOs
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    //Create a new game
    public int newGame() {
        Game game  = new Game();
        game.setAnswer(produceAnswer());
        game = gameDao.addGame(game);
        
        return game.getGameId();
    }
    
    //we create the service method for adding a game
    public Game addGame(Game game)
    {
        return gameDao.addGame(game);
    }
    
    
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        
        //if the game is not finished we set the answer to blank
        if(!game.isFinished())
        {
            game.setAnswer("");
        }
        
        return game;
    }
    
    //service method to get all games
    public List<Game> getAllGames()
    {
        //Create a list to store the games
        List<Game> games = gameDao.getAllGames();
        
        for(Game g: games)
        {
            //If the game is still running
            if(!g.isFinished())
            {
                //We set the answer to a blank
                g.setAnswer("");
            }
        }
        
        return games;
    }
    
    
    //service method to get all rounds
    public List<Round> getAllRoundsByGameId(int gameId)
    {
        //We create a list to store the rounds
        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);
        
        //We simply return them all
        return rounds;
        
    }
    
    
    //service method to make guess
    public Round makeAGuess(Round round)
    {
        //We get the answer for a game by first getting the gameId from the round data
        //then we get the answer from the game object
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        
        //We get the guess from the round
        String guess = round.getGuess();
        
        //We create a result string based off the result of the guess and the answer
        String result = calculateBullsAndCows(guess, answer);
        
        //We set the round's result
        round.setResult(result);
        
        //we check if the guess is the same as the answer
        if(guess.equals(answer))
        {
            //we get the game
            Game game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        
        return roundDao.addRound(round);
        
    }

    public String calculateBullsAndCows(String guess, String secret) {
        int e=0;
        int p=0;
        
        //Create a hashmap to store the answer
        HashMap<Character, Integer> store = new HashMap();
        
        //Insert into hashmap
        for(char c: secret.toCharArray())
        {
            //the value we insert for char c is 0 or +1 if it exists already
            store.put(c, store.getOrDefault(c, 0)+1);
        }
        
        //We iterate through to get the values of e and p
        for(int i = 0; i<guess.length(); i++)
        {
            //we get the index value
            char ch = guess.charAt(i);
            
            //if store contains the key then it is either in the correct position or not
            if(store.containsKey(ch))
            {
                
                if(ch ==secret.charAt(i))
                {
                    e++;
                    
                    if(store.get(ch)<=0)
                       p--;
                }else{
                   if(store.get(ch)>0)
                       p++;
               }
                    

                   

               
                store.put(ch, store.get(ch)-1);
            }
               
            
            
            
        }
        
        return "e:"+Integer.toString(e)+":"+"p"+":"+Integer.toString(p);
        
       
    }
    

    private String produceAnswer() {
        //We create a string of four random numbers
        
        //We create a random object
        Random randNum = new Random();
        
        //We initialize a set object
        Set<Integer> distinctNums = new HashSet();
        while(distinctNums.size() <4)
        {
            distinctNums.add(randNum.nextInt(10));
        }
        
        //Create a stringbuilder
        StringBuilder newString = new StringBuilder();
        for(int x: distinctNums)
        {
            //we append a digit converted from int to string
            newString.append(Integer.toString(x));
        }
        
        String finalResult = newString.toString();
        
        return finalResult;
    }
    
    
    
}
