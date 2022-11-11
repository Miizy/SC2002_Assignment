import java.io.Serializable;
/**
 * Review is text review and the rating given for a movie
 */
public class Review implements Serializable{
	/**
	 * The written review for the movie
	 */
	private String review;
	/**
	 * The numerical rating of the movie
	 */
	private float rating;
	
	/**
	 * Constructs the Review object
	 * @param review written review for the movie
	 * @param rating numerical rating of the movie
	 */
	public Review(String review, float rating) {
		this.setReview(review);
		this.setRating(rating);
	}

	/**
	 * Gets the rating given for the movie
	 * @return the rating given for the movie
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * Sets the rating given for the movie
	 * @param rating numerical rating of the movie
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Gets the written review for the movie
	 * @return the written review for the movie
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Sets the written review for the movie
	 * @param review written review for the movie
	 */
	public void setReview(String review) {
		this.review = review;
	}
}
