
public enum CinemaClass {
	plat("Platinum Movie Suites"),
	ulti("Ultima"),
	dolb("Dolby Atmos"),
	elit("Elite Club");

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
