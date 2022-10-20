
public enum CinemaClass {
	d3("3D"),
	bb("BlockBuster"),
	reg("Regular");

	private String cinemaClass;
	
	CinemaClass(String cinClass) {
		this.setCinemaClass(cinClass);
	}

	public String getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(String cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	
}
