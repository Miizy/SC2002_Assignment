
public class Tickets {
	private int noTickets;
	private MovieType MT;
	private TicketType TT;
	private boolean BB;
	private boolean Sneak;
	public Tickets(MovieType MT, TicketType TT, boolean BB, boolean Sneak) {
		this.MT = MT;
		this.TT = TT;
		this.BB = BB;
		this.Sneak = Sneak;
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
}
