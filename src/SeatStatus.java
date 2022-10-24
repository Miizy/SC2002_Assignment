public enum SeatStatus {
	an("normal"),
	ac("couple"),
	ae("elite"),
	au("ultima"),
	un("unavailable normal"),
	uc("unavailable couple"),
	ue("unavailable elite"),
	uu("unavailable ultima"),
	;
	
	private String SeatStatus;
	
	SeatStatus(String status){
		this.SeatStatus = status;
	}

	public String getSeatStatus() {
		return SeatStatus;
	}
}
