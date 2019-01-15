/**
 * 
 */
package gameDatabase;

/**
 * @author Andreas Johansson
 * 
 * A class that represents a videogame. 
 *
 */
public class Game {
	
	private String title;
	private String brand;
	private String platform;
	private String genre;
	private String published_by;
	private String publisher_location;
	
	private int year;
	private int publisher_founded;
	private int pegi;
	private int players;
	
	private boolean digital;
	private boolean local_multiplayer;
	private boolean online_multiplayer;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String str) {
		title = str;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String str) {
		brand = str;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String str) {
		platform = str;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String str) {
		genre = str;
	}
	public String getPublishedBy() {
		return published_by;
	}
	public void setPublishedBy(String str) {
		published_by = str;
	}
	public String getPublisherLocation() {
		return publisher_location;
	}
	public void setPublisherLocation(String str) {
		publisher_location = str;
	}
	public int getPublisherFounded() {
		return publisher_founded;
	}
	public void setPublisherFounded(int i) {
		publisher_founded = i;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int i) {
		year = i;
	}
	public int getPegi() {
		return pegi;
	}
	public void setPegi(int i) {
		pegi = i;
	}
	public int getPlayers() {
		return players;
	}
	public void setPlayers(int i) {
		players = i;
	}
	public boolean getDigital() {
		return digital;
	}
	public void setDigital(boolean bool) {
		digital = bool;
	}
	public boolean getOnlineMultiplayer() {
		return online_multiplayer;
	}
	public void setOnlineMultiplayer(boolean bool) {
		online_multiplayer = bool;
	}
	public boolean getLocalMultiplayer() {
		return local_multiplayer;
	}
	public void setLocalMultiplayer(boolean bool) {
		local_multiplayer = bool;
	}
	public String toString() {
		return "Title: " + title + "\n" +
				"Year: " + year + "\n" +
				"Console producer: " + brand + "\n" +
				"Platform: " + platform + "\n" +
				"Genre: " + genre + "\n" +
				"Digital: " + digital + "\n" +
				"Publisher: " + published_by + "\n" + 
				"PEGI: " + pegi + "\n" +
				"Players: " + players + "\n" +
				"Online multiplayer: " + online_multiplayer + "\n" +
				"Local multiplayer: " + local_multiplayer;
	}
}
