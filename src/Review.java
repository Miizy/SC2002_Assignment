public class Review {
	private String review;
	private float rating;
	
	public Review(String review, float rating) {
		this.setReview(review);
		this.setRating(rating);
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}
