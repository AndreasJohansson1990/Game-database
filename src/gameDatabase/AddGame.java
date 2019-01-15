/**
 * 
 */
package gameDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Andreas Johansson
 *
 */
public class AddGame {	
	
	public void createNewGame() throws IOException {
		ObjectMapper mapper = new ObjectMapper(); 
		
		Game createGame = new Game();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Title: ");
		createGame.setTitle(scan.nextLine());
		
		System.out.println("Year: ");
		createGame.setYear(scan.nextInt());
		scan.nextLine();
		
		System.out.println("Brand: ");
		createGame.setBrand(scan.nextLine());

		System.out.println("Platform: ");
		createGame.setPlatform(scan.nextLine());
		
		System.out.println("Genre: ");
		createGame.setGenre(scan.nextLine());
		
		System.out.println("Digital: ");
		createGame.setDigital(scan.nextBoolean());
		scan.nextLine();
		
		System.out.println("Publisher: ");
		createGame.setPublishedBy(scan.nextLine());
		
		System.out.println("Publisher location: ");
		createGame.setPublisherLocation(scan.nextLine());
		
		System.out.println("Publisher founded: ");
		createGame.setPublisherFounded(scan.nextInt());
		scan.nextLine();
	
		System.out.println("PEGI: ");
		createGame.setPegi(scan.nextInt());
		
		System.out.println("Players: ");
		createGame.setPlayers(scan.nextInt());
		
		System.out.println("Online Multiplayer: ");
		createGame.setOnlineMultiplayer(scan.nextBoolean());
		
		System.out.println("Local Multiplayer: ");
		createGame.setLocalMultiplayer(scan.nextBoolean());
		
		if (createGame.getTitle().equals("abort")) {
			
		} else {
			
			String game = mapper.writeValueAsString(createGame);
			
			Files.write(new File("./games.json").toPath(), (game + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			
			System.out.println(createGame.toString() + "\n\n\n");
			createNewGame();
		}
		
		
		scan.close();
	}
}
