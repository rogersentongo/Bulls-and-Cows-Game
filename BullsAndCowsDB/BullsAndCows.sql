DROP DATABASE IF EXISTS BullsAndCows;
CREATE DATABASE BullsAndCows;
USE BullsAndCows;

CREATE TABLE game (
	game_Id INT PRIMARY KEY AUTO_INCREMENT,
    answer char(4),
    finished BOOLEAN DEFAULT false
    );
    

CREATE TABLE round (
	round_Id INT PRIMARY KEY AUTO_INCREMENT,
    game_game_Id INT NOT NULL,
    guess_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    result CHAR(7),
    FOREIGN KEY fk_gameid (game_game_id) REFERENCES game(game_id)
    );
   
    
    
    