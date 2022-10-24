import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	
	public Cineplex() {}

	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}
	
	public Cinema getCinema(int index) {
		return listOfCinema.get(index);
	}
	
	public void addCinema(int cinemaID, int numOfTheatre){
		listOfCinema.add(new Cinema(cinemaID, numOfTheatre));
	}
	
	public void setCinema(int cinemaID, Cinema cinema) {
		for(int i=0; i<listOfCinema.size(); i++) {
			if(listOfCinema.get(i).getCinemaID() == cinemaID) {
				listOfCinema.set(i, cinema);
				break;
			}
		}
	}
}
