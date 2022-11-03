import java.util.Date;

public class TimeSlot {
	private Date strtTime;
	private Date endTime;
	private Movie movieSpec;

	public TimeSlot(Date start, Date end, Movie mov) {
		this.strtTime= start;
		this.endTime=end;
		this.movieSpec = mov;
	}

	public Date getStartTime() {
		return this.strtTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public Movie getMovie(){
		return this.movieSpec;
	}
	//getMovietype to be added
}