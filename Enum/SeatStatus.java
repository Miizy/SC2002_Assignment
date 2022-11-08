/**
 * Enumeration for different types of seats available in the theatres
 * @author Viral Mehta
 */
public enum SeatStatus {
	/**
	 * Normal type are the base, available in Platinum Theatres
	 */
	an("normal"),
	/**
	 * Couple seats are bought in pairs for couples in Platinum and Elite Theatres
	 */
	ac("couple"),
	/**
	 * Elite seats are more luxurious and expensive only available in Elite Theatres
	 */
	ae("elite"),
	/**
	 * A seat that is just a passage, to fill up empty spaces in a 2D array
	 */
	ap("Passage")
	;
	/**
     * A string that holds what type of seat it is
     */
	private String SeatStatus;
	
	/**
	 * Constructs the seat type based on the input
	 * @param status abbreviated string for the seat type enumeration
	 */
	SeatStatus(String status){
		this.SeatStatus = status;
	}
	/**
     * Gets the Seat Type
     * @return string that shows what type of Seat it is
     */
	public String getSeatStatus() {
		return SeatStatus;
	}
}
