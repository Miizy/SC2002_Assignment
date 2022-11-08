
import java.io.Serializable;
import java.util.GregorianCalendar;
/** 
 * Represents a movie slot with timings and it's seating information.
 * @author Viral Mehta
*/

public class TimeSlot implements Serializable{
	/**
     * The starting time of the movie show time.
     */
    private GregorianCalendar strtTime;

    /**
     * The ending time of the movie show time.
     */
    private GregorianCalendar endTime;

    /**
     * The movie for this show time slot.
     */
    private Movie movieSpec;
    /**
     * The seating layout associated with this time slot.
     */
	private SeatLayout seating; 
	/**
     * Creates a new TimeSlot with the given timings and movie.
     * The timing includes the time and also the date.
     * @param start This TimeSlot's starting time.
     * @param end   This TimeSlot's ending time.
     * @param mov   This TimeSlot's movie.
     */
	public TimeSlot(GregorianCalendar start, GregorianCalendar end, Movie mov) {
		this.strtTime= start;
		this.endTime=end;
		this.movieSpec = mov;
	}
	/**
     * Gets the TimeSlot's starting time.
     * @return this TimeSlot's start time.
     */
	public GregorianCalendar getStartTime() {
		return this.strtTime;
	}
	/**
     * Gets the TimeSlot's ending time.
     * @return this TimeSlot's end time.
     */
	public GregorianCalendar getEndTime() {
		return this.endTime;
	}
	/**
     * Gets the TimeSlot's movie.
     * @return this TimeSlot's movie.
     */
	public Movie getMovie(){
		return this.movieSpec;
	}
	/**
     * Initializes the TimeSlot's seat layout with the given Theatre type.
     * @param type Theatre type in which time slot will be associated too.
     */
	public void initializeSeatings( TheatreClass type){
		seating= new SeatLayout(type);
	}
	/**
     * Gets the seat layout and seat information of this TimeSlot.
     * @return this TimeSlot's seat layout.
     */
	public SeatLayout getSeating(){
		return this.seating;
	}
}