public enum ShowStatus {
	cs("Coming Soon"),
	pr("Preview"),
	ns("Now Showing"),
	na("End Of Showing");
	
	private String showStatus;
	
	ShowStatus(String status){
		this.showStatus = status;
	}
	
	public String getStatus() {
		return showStatus;
	}
	
}
