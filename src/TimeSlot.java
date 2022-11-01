public class TimeSlot {
	private Time24Hr [] timeslot = new Time24Hr [2];
	private String movieTitle;
	
	public TimeSlot(int start, int end, String movieTitle) {
		timeslot[0] = new Time24Hr(start);
		timeslot[1] = new Time24Hr(end);
		this.movieTitle = movieTitle;
	}
	
	Time24Hr getStartTime() {
		return timeslot[0];
	}
	
	Time24Hr getEndTime() {
		return timeslot[1];
	}
	
	String getmovieTitle() {
		return movieTitle;
	}

}
