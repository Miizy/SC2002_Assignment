import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	
	public Cineplex() {}

	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}
	
	public void addCinema(int cinemaID, int numOfTheatre) throws IOException{
		listOfCinema.add(new Cinema(cinemaID, numOfTheatre));
	}
}
