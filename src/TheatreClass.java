
public enum TheatreClass {
	plat("Platinum Movie Suites"),
	ulti("Ultima"),
	dolb("Dolby Atmos"),
	elit("Elite Club");

	private String theatreClass;
	
	TheatreClass(String cinClass) {
		this.setTheatreClass(cinClass);
	}

	public String getTheatreClass() {
		return theatreClass;
	}

	public void setTheatreClass(String cinemaClass) {
		this.theatreClass = cinemaClass;
	}
	
}
