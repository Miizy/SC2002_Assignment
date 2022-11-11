import java.io.Serializable;
import java.util.ArrayList;
/**
 * Movie is the movie available at the respective cinema
 */
public class Movie implements Serializable{
	/**
	 * Title of the movie
	 */
	private String movieTitle;
	/**
	 * Status of the show, e.g. Now Showing, End of Showing
	 */
	private ShowStatus showStatus;
	/**
	 * Rating of the movie, e.g. PG13, R
	 */
	private MovieRating movieRating;
	/**
	 * Type of Movie, e.g. Regular/ 3D
	 */
	private MovieType MovieType;
	/**
	 * Synopsis of the movie
	 */
	private String synopsis;
	/**
	 * Name of the director of the movie
	 */
	private String director;
	/**
	 * List of casts for the movie
	 */
	private ArrayList<String> cast = new ArrayList<String>();
	/**
	 * numerical rating of the movie
	 */
	private float overallRating;
	/**
	 * List of past reviewers for this movie
	 */
	private ArrayList<String> nameofPastReviewers = new ArrayList<String>();
	/**
	 * List of past reviews made by reviewers
	 */
	private ArrayList<Review> pastReview = new ArrayList<Review>();
	/**
	 * Whether the movie a blockbuster film
	 */
	private boolean BlockBuster = false;
	/**
	 * Whether the movie has sneak previews
	 */
	private boolean Sneakpreview = false;
	/**
	 * total sales made by the movie in the cinema
	 */
	private double Sales = 0.00;
	/**
	 * Constructs the Movie object
	 * @param movieTitle Title of the movie
	 * @param showStatus Status of the show, e.g. Now Showing, End of Showing
	 * @param movieRating Rating of the movie, e.g. PG13, R
	 * @param synopsis Synopsis of the movie
	 * @param director Name of the director of the movie
	 * @param BlockBuster Whether the movie a blockbuster film
	 * @param Sneakpreview Whether the movie has sneak previews
	 * @param Movietype Type of Movie, e.g. Regular/ 3D
	 */
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
	
	/**
	 * Set whether the movie is a blockbuster
	 * @param BlockBuster 
	 */
	public void setBlockBuster(boolean BlockBuster) {
		this.BlockBuster = BlockBuster;
	}
	/**
	 * Get whether the movie is a blockbuster
	 * @return true if movie is a blockbuster
	 */
	public boolean getBlockBuster() {
		return BlockBuster;
	}
	/**
	 * Set whether the movie has a sneak preview
	 * @param Sneakpreview 
	 */
	public void setSneakPreview(boolean Sneakpreview) {
		this.Sneakpreview = Sneakpreview;
	}
	/**
	 * Get whether the movie has a sneak preview
	 * @return true if movie has a sneak preview
	 */
	public boolean getSneakpreview() {
		return Sneakpreview;
	}
	/**
	 * Get the title of the movie
	 * @return the title of the movie
	 */
	public String getMovieTitle() {
		return movieTitle;
	}
	/**
	 * Set the title of the movie
	 * @param movieTitle Title of the movie
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	/**
	 * Get the status of the show
	 * @return the enum of ShowStatus, 1 Coming Soon, 2 Preview, 3 Now Showing, 4 End of Showing
	 */
	public ShowStatus getShowStatus() {
		return showStatus;
	}
	/**
	 * Set the type of the movie
	 * @param Movietype Type of Movie, 1 Regular/Digital, 2 3D, 3 Empty
	 */
	public void setMovieType(MovieType Movietype) {
		this.MovieType = Movietype;
	}
	/**
	 * Get the type of the movie
	 * @return Movietype Type of Movie, 1 Regular/Digital, 2 3D, 3 Empty
	 */
	public MovieType getMovieType() {
		return MovieType;
	}
	
	/**
	 * Set the status of the show
	 * @param showStatus Status of the show, 1 Coming Soon, 2 Preview, 3 Now Showing, 4 End of Showing
	 */
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
	
	/**
	 * Get the rating of the movie
	 * @return rating of the movie, 1 PG, 2 PG13, 3 R, 4 NC17, 5 G
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}

	/**
	 * Set the rating of the movie
	 * @param movieRating Rating of the movie, 1 PG, 2 PG13, 3 R21, 4 NC16, 5 G
	 */
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

	/**
	 * Get the synopsis of the movie
	 * @return the synopsis of the movie
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Set the synopsis of the movie
	 * @param synopsis Synopsis of the movie
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Get the name of the director of the movie
	 * @return the name of the director of the movie
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Set the name of the director of the movie
	 * @param director Name of the director of the movie
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get the list of the names of the cast of the movie
	 * @return the list of the names of the cast of the movie
	 */
	public ArrayList<String> getCast() {
		return cast;
	}
	
	/**
	 * Add the name of the cast of the movie to the list of cast
	 * @param cast Name of the cast
	 */
	public void addCast(String cast) {
		this.cast.add(cast);
	}
	
	/**
	 * Remove the cast that has the name of variable cast
	 * @param cast Name of cast to remove from list of cast
	 * @return true if cast is removed, false if cast name is not found
	 */
	public boolean removeCast(String cast) {
		if(this.cast.contains(cast)) {
			this.cast.remove(cast);
			return true;
		}
		return false;
	}

	/**
	 * Get the average numerical rating for the movie
	 * @return the average numerical rating for the movie
	 */
	public float getOverallRating() {
		return overallRating;
	}

	/**
	 * get list of past reviews for the movie
	 * @return list of past reviews for the movie
	 */
	public ArrayList<Review> getPastReview() {
		return pastReview;
	}
	
	/**
	 * get the review in the review list at the specified index
	 * @param index Index of review in the review list
	 * @return review in the review list at index
	 */
	public Review getReview(int index){
		return this.pastReview.get(index);
	}

	/**
	 * get the list of names of past reviewers for the movie
	 * @return the list of names of past reviewers for the movie
	 */
	public ArrayList<String> getNamesOfPastReviewers(){
		return nameofPastReviewers;
	}

	/**
	 * get the name of the reviewer in the reviewer list at the specified index
	 * @param index Index of reviewer in the reviewer list
	 * @return the name of the reviewer in the reviewer list at the specified index
	 */
	public String getNameofReviewer(int index){
		return this.nameofPastReviewers.get(index);
	}

	/**
	 * Add a review to the list of reviews
	 * @param review Written review of the movie
	 * @param rating Numerical rating of the movie
	 * @param nameOfReviewer Name of reviewer
	 */
	public void addReview(String review, float rating, String nameOfReviewer) {
		Review temp = new Review(review, rating);
		this.overallRating = (this.overallRating * this.pastReview.size() + rating) / (this.pastReview.size() + 1);
		this.pastReview.add(temp);
		this.nameofPastReviewers.add(nameOfReviewer);
	}
	
	/**
	 * Update the sales of the movie 
	 * @param payment Sales made from the movie
	 */
	public void addSales(double payment) {
		Sales += payment;
	}
	
	/**
	 * Get the total sales from the movie
	 * @return the total sales from the movie
	 */
	public double getSales() {
		return Sales;
	}
}
