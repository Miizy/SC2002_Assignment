import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cineplex {
	private ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();
	
	Cineplex(int [] numOfTheatre, FileWriter writer) throws IOException {
		writer.write("Cineplex \n");
		setListOfCinema(3, numOfTheatre, writer);
		writer.write("End Cineplex \n");
	}

	public ArrayList<Cinema> getListOfCinema() {
		return listOfCinema;
	}

	public void setListOfCinema(int numOfCinema, int [] numOfTheatre, FileWriter writer) throws IOException {
		for(int i=0; i<numOfCinema; i++) {
			listOfCinema.add(new Cinema(i, numOfTheatre[i], writer)); 
		}
	}
}
