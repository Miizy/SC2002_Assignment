import java.util.ArrayList;

public class Movie {
	private String movieTitle;
	private ShowStatus showStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast = new ArrayList<String>();
	private float overallRating;
	private ArrayList<String> pastReview = new ArrayList<String>();
	
	public Movie(String movieTitle, ShowStatus showStatus, String synopsis, String director) {
		setMovieTitle(movieTitle);
		setShowStatus(showStatus);
		setSynopsis(synopsis);
		setDirector(director);
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public ShowStatus getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void addCast(ArrayList <String> cast) {
		cast.forEach((castname) -> this.cast.add(castname));
	}
	
	public void addCast(String cast) {
		this.cast.add(cast);
	}
	
	public boolean removeCast(String cast) {
		if(this.cast.contains(cast)) {
			this.cast.remove(cast);
			return true;
		}
		return false;
	}

	public float getOverallRating() {
		return overallRating;
	}

	public ArrayList<String> getPastReview() {
		return pastReview;
	}

	public void addReview(String review, int rating) {
		this.overallRating = (this.overallRating * this.pastReview.size() + rating) / (this.pastReview.size() + 1);
		this.pastReview.add(review);
	}
}
