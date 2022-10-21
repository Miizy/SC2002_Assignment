import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cinema {
	private ArrayList<Theatre> listOfTheatre = new ArrayList<Theatre>();
	private int cinemaID;
	private CinemaClass cinemaClass;
	private ArrayList<Movie> listOfMovie = new ArrayList<Movie>();
	
	Cinema(int ID, int numOfTheatre, CinemaClass cinemaClass, FileWriter writer) throws IOException {
		writer.write("Cinema " + ID + "\n");
		setCinemaID(ID);
		setCinemaClass(cinemaClass);
		setListOfTheatre(numOfTheatre, writer);
		writer.write("End Cinema " + ID + "\n");
	}

	public ArrayList<Theatre> getListOfTheatre() {
		return listOfTheatre;
	}

	public void setListOfTheatre(int numOfTheatre, FileWriter writer) throws IOException {
		for(int i=0; i<numOfTheatre; i++) {
			listOfTheatre.add(new Theatre(i, writer));
		}
	}

	public int getCinemaID() {
		return cinemaID;
	}

	private void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	
	public ArrayList<Movie> getListOfMovie(){
		return this.listOfMovie;
	}
	
	public void addListOfMovie(Movie movie) {
		this.listOfMovie.add(movie);
	}

	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	
}
