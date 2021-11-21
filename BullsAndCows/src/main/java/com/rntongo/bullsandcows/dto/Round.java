/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author rogersentongo
 */
public class Round {
    
    //private member variables
    private int roundId;
    private int gameId; 
    private String guess;
    private String result;
    private LocalDateTime guessTime;
    
    public Round(){
        
    }
    
    public Round(int gameId, String guess)
    {
        this.gameId = gameId;
        this.guess = guess;
    }
    
    public Round(int gameId, String guess, String result, LocalDateTime guessTime, int roundId)
    {
        this.gameId = gameId;
        this.guess = guess;
        this.roundId = roundId;
        this.guessTime = guessTime;
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.roundId;
        hash = 31 * hash + this.gameId;
        hash = 31 * hash + Objects.hashCode(this.guess);
        hash = 31 * hash + Objects.hashCode(this.result);
        hash = 31 * hash + Objects.hashCode(this.guessTime);
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
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", guess=" + guess + ", result=" + result + ", guessTime=" + guessTime + '}';
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }
    
    
    
}
