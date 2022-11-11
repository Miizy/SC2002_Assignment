import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
	private ArrayList<MovieGoer> listOfGoers = new ArrayList<MovieGoer>();
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	private PricingList PriceList = new PricingList();
	
	public Cineplex() {}

	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}
	
	public ArrayList<MovieGoer> getListofGoers(){
		return listOfGoers;
	}
	
	public Cinema getCinema(int index) {
		return listOfCinema.get(index);
	}

	public MovieGoer getGoer(int index){
		return listOfGoers.get(index);
	}	
	public void addCinema(int cinemaID, int numOfTheatre) throws IOException{
		listOfCinema.add(new Cinema(cinemaID, numOfTheatre));
	}

	public void addGoer(int id,String name, String number, String email){
		listOfGoers.add(new MovieGoer(id, name, number, email));
	}
	
	public void setCinema(int cinemaID, Cinema cinema) {
		for(int i=0; i<listOfCinema.size(); i++) {
			if(listOfCinema.get(i).getCinemaID() == cinemaID) {
				listOfCinema.set(i, cinema);
				break;
			}
		}
	}
	public PricingList getPriceList(){
		return PriceList;
	}
}
