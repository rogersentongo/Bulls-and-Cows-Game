# Bulls-and-Cows-Game

Number Guessing Game built using Spring Boot.

How to Run the program:

Set up Database:
Import the SQL statements and run them in MYSQL Workbench.

Set up Spring App:
Import Bulls And Cow code into your IDE
Change the Application properties to load the database from your local version of MYSQL Workbench


RULES OF THE GAME:
In each game a four digit number is generated where each digit is not the same. A player can play for several rounds. In each round the player guesses the correct digit in the correct position. A partial match is when the player guesses the correct digit but in the wrong position. Once a number is guessed with all exact matches, the player wins the game.

Results are returned as "e:0:p:0" where e stands for exact matches and p stands for partial matches.

How to Run the Application:

The application uses REST endpoints to play the game:

Start the game with a POST to localhost:8080/api/begin to generate a number in the database.
Make a guess with a POST to localhost:8080/api/guess. The JSON body should have a number guess and a gameid. Results will be returned
Get a view of all games played so far with a GET to localhost:808/api/game
Get a single game with a GET to localhost:8080/api/game/{gameid}
Get a list of rounds played for a specific game with a GET to localhost:808/rounds/{gameid}

