public enum SeatStatus {
	an("normal"),
	ac("couple"),
	ae("elite"),
	ap("Passage")
	;
	
	private String SeatStatus;
	
	SeatStatus(String status){
		this.SeatStatus = status;
	}

	public String getSeatStatus() {
		return SeatStatus;
	}
}
