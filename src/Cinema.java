import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	private ArrayList<Theatre> listOfTheatre = new ArrayList<Theatre>();
	private int cinemaID;
	private ArrayList<Movie> listOfMovie = new ArrayList<Movie>();
	
	Cinema(int ID, int numOfTheatre) {
		setCinemaID(ID);
		setListOfTheatre(numOfTheatre);
	}

	public ArrayList<Theatre> getListOfTheatre() {
		return listOfTheatre;
	}

	public void setListOfTheatre(int numOfTheatre){
		for(int i=0; i<numOfTheatre; i++) {
			listOfTheatre.add(new Theatre(i,TheatreClass.dolb));
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
	
}
