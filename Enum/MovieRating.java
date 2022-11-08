
public enum MovieRating {
	pg("PG"),
	pgt("PG-13"),
	r("R"),
	nc("NC-17"),
	g("G");
	
	private String movieRating;
	
	MovieRating(String rating){
		this.movieRating = rating;
	}
	
	public String getmovieRating() {
		return movieRating;
	}
}
