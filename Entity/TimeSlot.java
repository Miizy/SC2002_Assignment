
import java.io.Serializable;
import java.util.GregorianCalendar;

public class TimeSlot implements Serializable{
	private GregorianCalendar strtTime;
	private GregorianCalendar endTime;
	private Movie movieSpec;
	private SeatLayout seating; 

	public TimeSlot(GregorianCalendar start, GregorianCalendar end, Movie mov) {
		this.strtTime= start;
		this.endTime=end;
		this.movieSpec = mov;
	}

	public GregorianCalendar getStartTime() {
		return this.strtTime;
	}

	public GregorianCalendar getEndTime() {
		return this.endTime;
	}

	public Movie getMovie(){
		return this.movieSpec;
	}

	public void initializeSeatings( TheatreClass type){
		seating= new SeatLayout(type);
	}

	public SeatLayout getSeating(){
		return this.seating;
	}
}