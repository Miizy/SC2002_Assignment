import java.io.Serializable;

public class Booking implements Serializable{
	private String transactionID = "";
	
	Booking(String theatreID, int year, int month, int day, int hour, int minutes){
		while(theatreID.length() < 3)
			theatreID = "0" + theatreID;
		transactionID = theatreID + String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(hour) + String.valueOf(minutes);
	}

	public String getTransactionID() {
		return transactionID;
	}
	
}
