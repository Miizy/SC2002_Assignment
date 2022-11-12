import java.io.Serializable;

/**
 * Tickets class to store and retrieve ticket traits
 *
 */
public class Tickets implements Serializable{
	/**
	 * The type of movie
	 */
	private MovieType MT;
	/**
	 * The type of Ticket
	 */
	private TicketType TT;
	/**
	 * Whether it is a blockbuster
	 */
	private boolean BB;
	/**
	 * Whether it is a sneak preview
	 */
	private boolean Sneak;
	/**
	 * The type of Theatre
	 */
	private TheatreClass TC;
	/**
	 * The type of Seats
	 */
	private SeatStatus SS;
	/**
	 * Tickets Constructor
	 * @param MT , Type of Movie
	 * @param TT , Type of Ticket
	 * @param BB , Whether its a BlockBuster Movie
	 * @param Sneak , whether its a Sneak Preview Movie
	 * @param TheatreClass , Type of Theatre
	 * @param SeatType , Type of Seat
	 */
	public Tickets(MovieType MT, TicketType TT, boolean BB, boolean Sneak, TheatreClass TheatreClass, SeatStatus SeatType) {
		this.MT = MT;
		this.TT = TT;
		this.BB = BB;
		this.Sneak = Sneak;
		this.TC = TheatreClass;
		this.SS = SeatType;
	}
	/**
	 * Get the Type of Movie
	 * @return the Type of Movie
	 */
	public MovieType getMovieType() {
		return MT;
	}
	/**
	 * Get the Type of Ticket
	 * @return the Type of Ticket
	 */
	public TicketType getTicketType() {
		return TT;
	}
	/**
	 * Get the boolean value of whether its a blockbuster
	 * @return the boolean value of whether is a blockbuster
	 */
	public boolean getBB() {
		return BB;
	}
	/**
	 * Get the boolean value of whether its a Sneak Preview
	 * @return the boolean value of whether its a Sneak Preview
	 */
	public boolean getSneak() {
		return Sneak;
	}
	/**
	 * Get the Type of Theatre
	 * @return the type of theatre
	 */
	public TheatreClass getTheatreClass() {
		return TC;
	}
	/**
	 * Get the type of Seat
	 * @return the type of Seat
	 */
	public SeatStatus getSeatStatus() {
		return SS;
	}
}
