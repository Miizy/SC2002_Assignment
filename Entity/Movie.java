import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{
	private String movieTitle;
	private ShowStatus showStatus;
	private MovieRating movieRating;
	private MovieType MovieType;
	private String synopsis;
	private String director;
	private ArrayList<String> cast = new ArrayList<String>();
	private float overallRating;
	private ArrayList<String> nameofPastReviewers = new ArrayList<String>();
	private ArrayList<Review> pastReview = new ArrayList<Review>();
	private boolean BlockBuster = false;
	private boolean Sneakpreview = false;
	private double Sales = 0.00;
	public Movie(String movieTitle, int showStatus, int movieRating, String synopsis, String director, boolean BlockBuster, boolean Sneakpreview, MovieType Movietype) {
		setMovieTitle(movieTitle);
		setShowStatus(showStatus);
		setMovieRating(movieRating);
		setSynopsis(synopsis);
		setDirector(director);
		setBlockBuster(BlockBuster);
		setSneakPreview(Sneakpreview);
		setMovieType(Movietype);
	}
	public void setBlockBuster(boolean BlockBuster) {
		this.BlockBuster = BlockBuster;
	}
	public boolean getBlockBuster() {
		return BlockBuster;
	}
	public void setSneakPreview(boolean Sneakpreview) {
		this.Sneakpreview = Sneakpreview;
	}
	public boolean getSneakpreview() {
		return Sneakpreview;
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
	public void setMovieType(MovieType Movietype) {
		this.MovieType = Movietype;
	}
	public MovieType getMovieType() {
		return MovieType;
	}
	public void setShowStatus(int showStatus) {
		if(showStatus == 1) {
			this.showStatus = ShowStatus.cs;
		}
		else if(showStatus == 2) {
			this.showStatus = ShowStatus.pr;
		}
		else if(showStatus == 3) {
			this.showStatus = ShowStatus.ns;
		}
		else if(showStatus == 4) {
			this.showStatus = ShowStatus.na;
		}
	}
	
	public MovieRating getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int movieRating) {
		if(movieRating == 1) {
			this.movieRating = MovieRating.pg;
		}
		else if(movieRating == 2) {
			this.movieRating = MovieRating.pgt;
		}
		else if(movieRating == 3) {
			this.movieRating = MovieRating.r;
		}
		else if(movieRating == 4) {
			this.movieRating = MovieRating.nc;
		}
		else {
			this.movieRating = MovieRating.g;
		}
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

	public ArrayList<Review> getPastReview() {
		return pastReview;
	}

	public Review getReview(int index){
		return this.pastReview.get(index);
	}

	public ArrayList<String> getNamesOfPastReviewers(){
		return nameofPastReviewers;
	}

	public String getNameofReviewer(int index){
		return this.nameofPastReviewers.get(index);
	}

	public void addReview(String review, float rating, String nameOfReviewer) {
		Review temp = new Review(review, rating);
		this.overallRating = (this.overallRating * this.pastReview.size() + rating) / (this.pastReview.size() + 1);
		this.pastReview.add(temp);
		this.nameofPastReviewers.add(nameOfReviewer);
	}
	
	public void addSales(double payment) {
		Sales += payment;
	}
	
	public double getSales() {
		return Sales;
	}
}
