import java.io.Serializable;

public class Tickets implements Serializable{
	private MovieType MT;
	private TicketType TT;
	private boolean BB;
	private boolean Sneak;
	private TheatreClass TC;
	private SeatStatus SS;
	public Tickets(MovieType MT, TicketType TT, boolean BB, boolean Sneak, TheatreClass TheatreClass, SeatStatus SeatType) {
		this.MT = MT;
		this.TT = TT;
		this.BB = BB;
		this.Sneak = Sneak;
		this.TC = TheatreClass;
		this.SS = SeatType;
	}
	public MovieType getMovieType() {
		return MT;
	}
	public TicketType getTicketType() {
		return TT;
	}
	public boolean getBB() {
		return BB;
	}
	public boolean getSneak() {
		return Sneak;
	}
	public TheatreClass getTheatreClass() {
		return TC;
	}
	public SeatStatus getSeatStatus() {
		return SS;
	}
}
