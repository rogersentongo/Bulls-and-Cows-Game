/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dto;

import java.util.Objects;

/**
 *
 * @author rogersentongo
 */
public class Game {
    
    //Private member variables
    private int gameId; //is the id of the current game
    private String answer; //is the correct answer
    private boolean Finished;
    
    public Game()
    {
        
    }
    
    
    //We need two constructors
    //One for setting the answer and setting it finished
    public Game(String answer, boolean finished)
    {
        this.answer = answer;
        this.Finished = finished;
    }
    
    public Game(String answer, int gameId, boolean finished)
    {
        this.answer = answer;
        this.gameId = gameId;
        this.Finished = finished;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.gameId;
        hash = 23 * hash + Objects.hashCode(this.answer);
        hash = 23 * hash + (this.Finished ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.Finished != other.Finished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", Finished=" + Finished + '}';
    }
    
    
    
    
    

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return Finished;
    }

    public void setFinished(boolean Finished) {
        this.Finished = Finished;
    }
    
    
    
}
