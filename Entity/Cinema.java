import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Cinema is the outlet of the company, e.g. Nex, AMK Hub
 *
 */
public class Cinema implements Serializable{
	/**
	 * List of theatre available at the outlet
	 */
	private ArrayList<Theatre> listOfTheatre = new ArrayList<Theatre>();
	/**
	 * Cinema's unique number ID
	 */
	private int cinemaID;
	/**
	 * Cineme's outlet name
	 */
	private String cinemaName = "";
	/**
	 * List of movie that has been aired or will air
	 */
	private ArrayList<Movie> listOfMovie = new ArrayList<Movie>();
	/**
	 * List of holiday dates
	 */
	private ArrayList<GregorianCalendar> holidayList = new ArrayList<GregorianCalendar>();
	
	/**
	 * Constructs the Cinema object
	 * @param ID The unique cinemaID
	 * @param numOfTheatre The number of theatres available in the cinema
	 */
	Cinema(int ID, int numOfTheatre) {
		setCinemaID(ID);
		setListOfTheatre(numOfTheatre);
	}

	/**
	 * gets the list of theatres in the cinema
	 * @return the list of theatres
	 */
	public ArrayList<Theatre> getListOfTheatre() {
		return listOfTheatre;
	}
	
	/**
	 * get the specific theatre given the index in the arrayList
	 * @param index Index of the theatre in the ArrayList
	 * @return the theatre at specified index of listOfTheatre
	 */
	public Theatre getTheatre(int index){
		return listOfTheatre.get(index);
	}
	
	/**
	 * replaces the theatre at the index with the input theatre
	 * @param index Index of the theatre in the ArrayList
	 * @param theatre Theatre that will replace the theatre at the index location
	 */
	public void replaceTheatre(int index, Theatre theatre) {
		listOfTheatre.set(index, theatre);
	}
	
	/**
	 * Initialise every Cinema with the input number of Theatre, each with unique Theatre ID and of elite class
	 * @param numOfTheatre Number of theatres to initialise in the cinema
	 */
	private void setListOfTheatre(int numOfTheatre){
		for(int i=0; i<numOfTheatre; i++) {
			listOfTheatre.add(new Theatre(i,3,this.cinemaID));
		}
	}
	
	/**
	 * Gets the cinema's unique ID
	 * @return the cinema's unique ID
	 */
	public int getCinemaID() {
		return cinemaID;
	}

	/**
	 * Sets the cinema's unique ID
	 * @param cinemaID the cinema's unique ID
	 */
	private void setCinemaID(int cinemaID) {
		
		this.cinemaID = cinemaID;
	}
	
	/**
	 * Gets the list of movie that has been aired or will air
	 * @return the list of movie that has been aired or will air
	 */
	public ArrayList<Movie> getListOfMovie(){
		return this.listOfMovie;
	}

	/**
	 * gets the movie in list of movie at specified index
	 * @param index Index of movie in the movie list
	 * @return the movie in list of movie at specified index
	 */
	public Movie getMovie(int index){
		return this.listOfMovie.get(index);
	}
	
	/**
	 * adds the input movie into the list of movies
	 * @param movie Movie to add into the list of movies
	 */
	public void addMovie(Movie movie) {
		this.listOfMovie.add(movie);
	}
	
	/**
	 * Replaces the movie at the index of the list of movie with the input movie
	 * @param index Index of the movie list to replace
	 * @param movie Movie that will replace the movie at index of movie list
	 */
	public void replaceMovie(int index, Movie movie) {
		this.listOfMovie.set(index, movie);
	}

	/**
	 * Get the name of the cinema
	 * @return name of cinema
	 */
	public String getCinemaName() {
		return cinemaName;
	}

	/**
	 * Set the name of the cinema
	 * @param cinemaName Name of cinema
	 */
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	/**
	 * Get list of holiday dates
	 * @return list of holiday dates
	 */
	public ArrayList<GregorianCalendar> getHolidayList(){
		return holidayList;
	}
	
	/**
	 * Set the list of holidays
	 * @param holidayList List of holidays to replace current list of holidays
	 */
	public void setHolidayList(ArrayList<GregorianCalendar> holidayList) {
		this.holidayList = holidayList;
	}
}
