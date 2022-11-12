/**
 * Enumeration for the types of Tickets
 *
 */
public enum TicketType {
	/**
	 * Senior Citizens
	 */
	SC("Senior Citizens"),
	/**
	 * Students
	 */
	ST("Students"),
	/**
	 * Monday to Wednesday
	 */
	MW("Mon - Wed"),
	/**
	 *Thursday
	 */
	TH("Thu"),
	/**
	 * Friday Before 6pm
	 */
	FB("Friday Before 6"),
	/**
	 * Friday After 6pm
	 */
	FA("Friday After 6"),
	/**
	 * Saturday and Sunday
	 */
	SS("Sat & Sun"),
	/**
	 * Credit and Loyalty Cards
	 */
	CL("Preferred Credit & Loyalty Card"),;
	/**
	 * A string to hold the type of ticket
	 */
	private String TicketType;
	/**
	 * Construct an Object of TicketType based on input
	 * @param Ticket Abbreviated string for Ticket Type 
	 */
	TicketType(String Ticket){
		this.TicketType = Ticket;
	}
	/**
	 * Get Ticket Type
	 * @return abbreviated string for Ticket Type
	 */
	public String getTicketType() {
		return TicketType;
	}
}
