/**
 * 
 */
package gameDatabase;

import java.io.IOException;

/**
 * @author Andreas Johansson
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//This is the textfile that contains all game objects to insert into database
		//String inputFile = ".\\games.json";
		
		//This instantiates the database manager with the textfile containing all game objects
		//DatabaseManager DBManager = new DatabaseManager(inputFile);
		
		//Use this string when creating the database,the tables and when inserting data into the database
		//String dbFile = "games.db";
		
		//This is the class that I used to add more games to the textfile containing all the game objects
		//AddGame addGame = new AddGame();
		
		/*
		This method starts a console application that prompts for information for each of the attributes needed in the database
		It will only stop by terminating the program or by setting the "Title" to be named "abort" it will the continue to prompt for information
		but terminate after the last question.
		Cheatsheet:
		Title = letters
		Year = numbers
		Brand = letters
		Platform = letters
		Genre = letters
		Digital = true or false
		Publisher = letters
		Publisher location = letters
		Publisher founded = numbers
		PEGI = numbers
		Players = numbers
		Online multiplayer = true or false
		Local multiplayer = true or false
		*/
		//addGame.createNewGame();
		 
		//Create a new database
		//DBManager.createNewDatabase(dbFile);

		//Create tables for the database
		//DBManager.createTableGame(dbFile);
		//DBManager.createTablePublisher(dbFile);
		//DBManager.createTableBrand(dbFile);
		
		
		//Insert data from games.json
		//System.out.println("Migration to db table started with " + inputFile );
		//DBManager.insertData(dbFile);
		
	}

}


