
public enum MovieType {
	RD("Regular & Digital"),
	TD("3D"),
	EM("Empty"),;

	private String MovieType;
	MovieType(String Movie){
		this.MovieType = Movie;
	}
	public String getMovieType() {
		return MovieType;
	}
}
