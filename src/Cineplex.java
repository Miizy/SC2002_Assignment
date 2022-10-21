import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cineplex {
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	
	public Cineplex() {}

	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}
	
	public void addCinema(int cinemaID, int numOfTheatre, FileWriter writer) throws IOException{
		listOfCinema.add(new Cinema(cinemaID, numOfTheatre, CinemaClass.dolb, writer));
	}
}
