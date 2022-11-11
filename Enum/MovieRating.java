/**
 * Enumeration for the types of movie ratings
 *
 */
public enum MovieRating {
	
	/**
	 * Parental Guidance suggested for children. 
	 */
	pg("PG"),
	
	/**
	 * Parental Guidance strongly recommended for children 13 years old and under. 
	 */
	pgt("PG13"),
	
	/**
	 * Strictly for adults aged 21 and above
	 */
	r("R21"),
	
	/**
	 * Restricted to people aged 16 and above
	 */
	nc("NC16"),
	
	/**
	 * Suitable for all ages
	 */
	g("G");
	
	/**
	 * A string that holds the movie rating type
	 */
	private String movieRating;
	
	/**
	 * Constructs the movie rating based on input rating
	 * @param rating An abbreviated string for the movie rating enumeration
	 */
	MovieRating(String rating){
		this.movieRating = rating;
	}
	/**
	 * Gets the movie rating
	 * @return A string containing the movie rating
	 */
	public String getmovieRating() {
		return movieRating;
	}
}
