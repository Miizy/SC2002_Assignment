
public enum TicketType {
	SC("Senior Citizens"),
	ST("Students"),
	MW("Mon - Wed"),
	TH("Thu"),
	FB("Friday Before 6"),
	FA("Friday After 6"),
	SS("Sat & Sun"),
	CL("Preferred Credit & Loyalty Card"),
	EM("Empty");
	private String TicketType;
	
	TicketType(String Ticket){
		this.TicketType = Ticket;
	}
	public String getTicketType() {
		return TicketType;
	}
}
