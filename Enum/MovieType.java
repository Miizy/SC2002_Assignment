/**
 * Enumeration for the types of movie
 *
 */
public enum MovieType {
	/**
	 * Regular & Digital Movies
	 */
	RD("Regular & Digital"),
	/**
	 * 3D Movies
	 */
	TD("3D"),;
	
	/**
	 * A string to hold the type of Movie
	 */
	private String MovieType;
	/**
	 * Construct an Object based on the input
	 * @param Movie abbreviated string for Movie Type
	 */
	MovieType(String Movie){
		this.MovieType = Movie;
	}
	/**
	 * Get Movie Type
	 * @return the abbreviated string of the Movie Type
	 */
	public String getMovieType() {
		return MovieType;
	}
}
