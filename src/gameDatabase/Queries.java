/**
 * 
 */
package gameDatabase;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

/**
 * @author Andreas Johansson
 *
 */
public class Queries {
	private DefaultTableModel model = new DefaultTableModel(new String[]{"Title", "Year", "Platform", "Genre", "Digital", "Published by", "Players", "PEGI", "Online multiplayer", "Local multiplayer"}, 0);
	private DatabaseManager dbManager;
	
	public Queries (DatabaseManager databaseManager) {
		dbManager = databaseManager;
	}
	

	public DefaultTableModel gamesInAGenre(String str) {
		String genre = "";

		if (str.length() == 0) {
			
		} else {
			genre = "%" + str + "%";
		}
		String sqlQuery = "SELECT * "
						+ "FROM Game "
						+ "WHERE genre LIKE '" + genre + "' COLLATE NOCASE";

		try (Connection conn = dbManager.connect("games.db")) {
			PreparedStatement prepStateQuery = conn.prepareStatement(sqlQuery);

			ResultSet queryResult = prepStateQuery.executeQuery();
			model = new DefaultTableModel(new String[]{"Title", "Year", "Platform", "Genre", "Digital", "Published by", "Players", "PEGI", "Online multiplayer", "Local multiplayer"}, 0);
						
			while (queryResult.next()) {
				setGameJTable(queryResult);
				System.out.println("Game: " + queryResult.getString("title"));
	         }
	            
			prepStateQuery.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	public DefaultTableModel gamesInOrder(String str) {
		String name = "";
		if (str.toLowerCase().equals("local multiplayer")) {
			str = "local_multiplayer";
		} else if (str.toLowerCase().equals("online multiplayer")) {
			str = "online_multiplayer";
		}
		if (str.toLowerCase().equals("title") || str.toLowerCase().equals("year") || 
				str.toLowerCase().equals("platform") || str.toLowerCase().equals("genre") || 
				str.toLowerCase().equals("players") || str.toLowerCase().equals("published_by") || 
				str.toLowerCase().equals("digital") || str.toLowerCase().equals("pegi") || 
				str.toLowerCase().equals("online_multiplayer") || str.toLowerCase().equals("local_multiplayer")){
			name = str;
		
		String sqlQuery = "SELECT * "
						+ "FROM Game "
						+ "ORDER BY " + name + " COLLATE NOCASE";

		try (Connection conn = dbManager.connect("games.db")) {
			PreparedStatement prepStateQuery = conn.prepareStatement(sqlQuery);

			ResultSet queryResult = prepStateQuery.executeQuery();
			model = new DefaultTableModel(new String[]{"Title", "Year", "Platform", "Genre", "Digital", "Published by", "Players", "PEGI", "Online multiplayer", "Local multiplayer"}, 0);
						
			while (queryResult.next()) {
				setGameJTable(queryResult);
				System.out.println("Game: " + queryResult.getString("title"));
	         }
	            
			prepStateQuery.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			model = new DefaultTableModel(new String[]{"Title", "Year", "Platform", "Genre", "Digital", "Published by", "Players", "PEGI", "Online multiplayer", "Local multiplayer"}, 0);
		}
		return model;
	}
	public DefaultTableModel numberOfGamesPublished(String str) {
		String gamePublisher = str;
		
		String sqlQuery = "SELECT published_by, count(*) as total "
				+ "FROM Game "
				+ "WHERE published_by = '" + gamePublisher + "' COLLATE NOCASE";

		try (Connection conn = dbManager.connect("games.db")) {
			PreparedStatement prepStateQuery = conn.prepareStatement(sqlQuery);
			model = new DefaultTableModel(new String[]{"Publisher", "Number of games"}, 0);
			ResultSet queryResult = prepStateQuery.executeQuery();
			
			setNumberOfGamesPublished(queryResult);
			System.out.print(queryResult.getString("published_by") + " has published " + queryResult.getInt("total") + " games");
			prepStateQuery.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	public DefaultTableModel gamesOnPlatformsMadeBy(String str) {
		String brand = str;
		String sqlQuery = "SELECT g.title, b.platform, b.brand FROM Game G "
				+ "INNER JOIN Brand B ON b.platform=g.platform WHERE b.brand= '"
				+ brand + "' COLLATE NOCASE ";

		try (Connection conn = dbManager.connect("games.db")) {
			
			PreparedStatement prepStateQuery = conn.prepareStatement(sqlQuery);
			
			ResultSet queryResult = prepStateQuery.executeQuery();
			model = new DefaultTableModel(new String[]{"Brand", "Platform", "Title"}, 0);
			
			while (queryResult.next()) {
				setPlatformsMadeByTable(queryResult);
				System.out.println(" Game: " + queryResult.getString("title") + "\n Platform: " + queryResult.getString("platform"));									

			}
			prepStateQuery.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	public DefaultTableModel gamesWithPublisherFrom(String str) {
		String location = str;
		String sqlQuery = "SELECT g.title, p.published_by, p.publisher_location FROM Game G "
				+ "INNER JOIN Publisher P ON p.published_by=g.published_by WHERE p.publisher_location= '"
				+ location + "' COLLATE NOCASE";

		try (Connection conn = dbManager.connect("games.db")) {
			
			PreparedStatement prepStateQuery = conn.prepareStatement(sqlQuery);
			
			ResultSet queryResult = prepStateQuery.executeQuery();
			model = new DefaultTableModel(new String[]{"Publisher", "Publisher location", "Game"}, 0);
			
			while (queryResult.next()) {
				setPublisherLocationTable(queryResult);
				System.out.println(" Game: " + queryResult.getString("publisher_location") + "\n Published by: " + queryResult.getString("published_by"));									

			}
			prepStateQuery.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	public void setGameJTable(ResultSet queryResult) throws SQLException {
		String a = queryResult.getString("title");
		int b = queryResult.getInt("year");
		String c = queryResult.getString("platform");
		String d = queryResult.getString("genre");
		boolean e = queryResult.getBoolean("digital");
		String f = queryResult.getString("published_by");
		int g = queryResult.getInt("players");
		int h = queryResult.getInt("pegi");
		boolean i = queryResult.getBoolean("online_multiplayer");
		boolean j = queryResult.getBoolean("local_multiplayer");
		model.addRow(new Object[]{a,b,c,d,e,f,g,h,i,j});
	}
	public void setNumberOfGamesPublished(ResultSet queryResult) throws SQLException {
		String a = queryResult.getString("published_by");
		int b = queryResult.getInt("total");
		model.addRow(new Object[]{a,b});
	}
	public void setPublisherLocationTable(ResultSet queryResult) throws SQLException {
		String a = queryResult.getString("published_by");
		String b = queryResult.getString("publisher_location");
		String c = queryResult.getString("title");
		model.addRow(new Object[]{a,b,c});
	}
	public void setPlatformsMadeByTable(ResultSet queryResult) throws SQLException {
		String a = queryResult.getString("brand");
		String b = queryResult.getString("platform");
		String c = queryResult.getString("title");
		model.addRow(new Object[]{a,b,c});
	}
}
