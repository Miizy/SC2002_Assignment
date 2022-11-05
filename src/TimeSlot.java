
import java.io.Serializable;
import java.util.GregorianCalendar;

public class TimeSlot implements Serializable{
	private GregorianCalendar strtTime;
	private GregorianCalendar endTime;
	private Movie movieSpec;
	private SeatLayout seatTing; 

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

	public void initializeSeatTings( TheatreClass type){
		seatTing= new SeatLayout(type);
	}

	public SeatLayout getSeatTing(){
		return this.seatTing;
	}
}