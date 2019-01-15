/**
 * 
 */
package gameDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Andreas Johansson
 *
 */
 
public class DatabaseManager {
	
	private String databaseUrl = "jdbc:sqlite:C:/Container/db/";
	private MyFileReader fileReader;
	
	public DatabaseManager (String fileName) {
		fileReader = new MyFileReader(fileName);
	}
	
    /**
     * Connect to a sample database
     *
     * @param dbFile the database file name
     */
    public void createNewDatabase(String dbFile) {
    	
    	String url = databaseUrl + dbFile;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public Connection connect(String dbfile) {
        Connection conn = null;
        
        try {
            String url = databaseUrl + dbfile;
           
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        return conn;
    }
    
    public void insertData(String dbFile) throws IOException {

    	String sqlInsertPublisher = "INSERT OR IGNORE INTO Publisher(published_by, publisher_location, publisher_founded) VALUES(?, ?, ?)";
    	String sqlInsertGame = "INSERT OR IGNORE INTO Game(title, year, "
    								+ " platform, genre, digital, published_by, players, pegi, "
    								+ " online_multiplayer, local_multiplayer)"
    								+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	String sqlInsertBrand = "INSERT OR IGNORE INTO Brand(brand, platform) VALUES(?, ?)";
    	
    	Connection conn = null;
    	PreparedStatement prepStatePublisher = null;
    	PreparedStatement prepStateGame = null;
    	PreparedStatement prepStateBrand = null;
    	
    	try {
    		conn = connect(dbFile);
    		conn.setAutoCommit(false);
    		prepStatePublisher = conn.prepareStatement(sqlInsertPublisher);
    		prepStateGame = conn.prepareStatement(sqlInsertGame);
    		prepStateBrand = conn.prepareStatement(sqlInsertBrand);
    		
    		Game game = fileReader.readFile();
    		
    		while (game != null) {
    			
    			prepStatePublisher.setString(1, game.getPublishedBy());
    			prepStatePublisher.setString(2, game.getPublisherLocation());
    			prepStatePublisher.setInt(3, game.getPublisherFounded());
    			prepStatePublisher.executeUpdate();
    			
    			prepStateBrand.setString(1, game.getBrand());
    			prepStateBrand.setString(2, game.getPlatform());
    			prepStateBrand.executeUpdate();
    			
    			
    			prepStateGame.setString(1, game.getTitle());
    			prepStateGame.setInt(2, game.getYear());
    			prepStateGame.setString(3, game.getPlatform());
    			prepStateGame.setString(4, game.getGenre());
    			prepStateGame.setBoolean(5, game.getDigital());
    			prepStateGame.setString(6, game.getPublishedBy());
    			prepStateGame.setInt(7, game.getPlayers());
    			prepStateGame.setInt(8, game.getPegi());
    			prepStateGame.setBoolean(9, game.getOnlineMultiplayer());
    			prepStateGame.setBoolean(10, game.getLocalMultiplayer());
    			prepStateGame.executeUpdate();
    			
    			game = fileReader.readFile();
    		}
    		System.out.print("Finished!  ");

    		prepStateGame.close();
    		conn.commit();
    		conn.close();
    		
    	}  catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
        	System.err.println(e.getMessage());
        }
    }
    
    public void createTablePublisher(String dbFile) {
        
        // SQL statement for creating a new table
        String sql ="CREATE TABLE IF NOT EXISTS Publisher ("
        		+ " published_by text PRIMARY KEY,"
        		+ " publisher_location text NOT NULL,"
        		+ " publisher_founded text NOT NULL"
        		+ ");";
        Statement statement = null;
        try (Connection conn = connect(dbFile)){
        	
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
            System.out.println("Table for Publisher has been created");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void createTableGame(String dbFile) {
        
        // SQL statement for creating a new table
        String sql ="CREATE TABLE IF NOT EXISTS Game ("
        		+ " title text NOT NULL,"
        		+ " year integer NOT NULL,"
        		+ " platform text NOT NULL,"
        		+ " genre text NOT NULL,"
        		+ " digital integer NOT NULL,"
        		+ " published_by text NOT NULL,"
        		+ " players integer NOT NULL,"
        		+ " pegi integer NOT NULL,"
        		+ " online_multiplayer integer NOT NULL,"
        		+ " local_multiplayer integer NOT NULL,"
        		+ " PRIMARY KEY(title, year)"
        		+ ");";
        Statement statement = null;
       
        try (Connection conn = connect(dbFile)){
            // create a new table
        	statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
            System.out.println("Table for Game has been created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 

    }
    public void createTableBrand(String dbFile) {
        
        // SQL statement for creating a new table
        String sql ="CREATE TABLE IF NOT EXISTS Brand ("
        		+ " brand text NOT NULL,"
        		+ " platform text PRIMARY KEY"
        		+ ");";
        
        Statement statement = null;
        try (Connection conn = connect(dbFile)){
        	
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
            System.out.println("Table for Brand has been created");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}