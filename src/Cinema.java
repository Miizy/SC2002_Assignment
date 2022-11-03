import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	private ArrayList<Theatre> listOfTheatre = new ArrayList<Theatre>();
	private int cinemaID;
	private String cinemaName = "";
	private ArrayList<Movie> listOfMovie = new ArrayList<Movie>();
	
	Cinema(int ID, int numOfTheatre) throws IOException {
		setCinemaID(ID);
		setListOfTheatre(numOfTheatre);
	}


	public ArrayList<Theatre> getListOfTheatre() {
		return listOfTheatre;
	}

	public Theatre getTheatre(int index){
		return listOfTheatre.get(index);
	}
	
	public void setListOfTheatre(int numOfTheatre) throws IOException{
		for(int i=0; i<numOfTheatre; i++) {
			listOfTheatre.add(new Theatre(i,3,this.cinemaID));
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

	public Movie getMovie(int index){
		return this.listOfMovie.get(index);
	}
	
	public void addMovie(Movie movie) {
		this.listOfMovie.add(movie);
	}
	
	public void replaceMovie(int index, Movie movie) {
		this.listOfMovie.set(index, movie);
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
}
