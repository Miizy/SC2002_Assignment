/**
 * Enumeration for the status of the movie
 */
public enum ShowStatus {
	/**
	 * Movie is not available now and will be coming soon
	 */
	cs("Coming Soon"),
	/**
	 * A preview of the movie is available
	 */
	pr("Preview"),
	/**
	 * Movie is now showing and users can purchase tickets
	 */
	ns("Now Showing"),
	/**
	 * Movie air time has ended and will no longer be available
	 */
	na("End Of Showing");
	
	private String showStatus;
	
	ShowStatus(String status){
		this.showStatus = status;
	}
	
	public String getStatus() {
		return showStatus;
	}
	
}
