/**
 * 
 */
package gameDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;


/**
 * @author Andreas Johansson
 *
 * A class that reads a file and parse each JSONobject to a Game, 
 * and further on to a SQL database. 
 */
public class MyFileReader {
	
	private File fileToRead;
	private ObjectMapper mapper;
	private BufferedReader buffReader;
	
	public MyFileReader(String fileName) {
		fileToRead = new File(fileName);
		try {
			buffReader = new BufferedReader(new FileReader(fileToRead));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Game readFile() throws IOException {
		
		Game post = null;
		if (notEndOfFile()) {
			
			post = parseJsonToGame(buffReader.readLine());
		}
		return post;
	}
	
	private boolean notEndOfFile() throws IOException {
		return buffReader.ready();
	}
	
	private Game parseJsonToGame(String jsonString) {
		mapper = new ObjectMapper();
		Game post = new Game();
		
		try {
			post = mapper.readValue(jsonString, Game.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}
}
