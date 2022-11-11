import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Cineplex is the company of the movie industry, e.g. Shaw, Cathay
 */
public class Cineplex implements Serializable {
	/**
	 * List of movie goers who registered in the cineplex
	 */
	private ArrayList<MovieGoer> listOfGoers = new ArrayList<MovieGoer>();
	/**
	 * List of cinemas that the cineplex owns
	 */
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	/**
	 * List of prices for the different tickets
	 */
	private PricingList PriceList = new PricingList();
	
	public Cineplex() {}

	/**
	 * Get list of cinemas that the the cineplex owns
	 * @return list of cinemas
	 */
	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}
	
	/**
	 * Get list of movie goers who registered in the cineplex
	 * @return list of movie goers
	 */
	public ArrayList<MovieGoer> getListofGoers(){
		return listOfGoers;
	}
	
	/**
	 * Get the cinema in the list of cinemas at index
	 * @param index Index of the cinema in the list of cinemas
	 * @return the cinema in the list of cinemas at index
	 */
	public Cinema getCinema(int index) {
		return listOfCinema.get(index);
	}

	/**
	 * Get the MovieGoer in the list of movier goers at index
	 * @param index Index of the MovieGoer in the list of movier goers
	 * @return the MovieGoer in the list of movier goers at index
	 */
	public MovieGoer getGoer(int index){
		return listOfGoers.get(index);
	}	
	
	/**
	 * add a cinema to the the cinema list
	 * @param cinemaID unique cinema ID 
	 * @param numOfTheatre number of theatres the cinema has
	 */
	public void addCinema(int cinemaID, int numOfTheatre){
		listOfCinema.add(new Cinema(cinemaID, numOfTheatre));
	}

	/**
	 * add a movie goer to the list of movie goers
	 * @param id unique movie goer ID 
	 * @param name Name of the movie goer
	 * @param number Mobile number of the movie goer
	 * @param email Email of the movie goer
	 */
	public void addGoer(int id,String name, String number, String email){
		listOfGoers.add(new MovieGoer(id, name, number, email));
	}
	
	/**
	 * replaces the cinema with cinemaID as its cinemaID in cinema list with the input cinema
	 * @param cinemaID unique cinema ID 
	 * @param cinema replacement cinema 
	 */
	public void setCinema(int cinemaID, Cinema cinema) {
		for(int i=0; i<listOfCinema.size(); i++) {
			if(listOfCinema.get(i).getCinemaID() == cinemaID) {
				listOfCinema.set(i, cinema);
				break;
			}
		}
	}
	
	/**
	 * get the price list 
	 * @return the price list 
	 */
	public PricingList getPriceList(){
		return PriceList;
	}
}
