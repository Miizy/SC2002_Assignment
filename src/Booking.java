import java.io.Serializable;

public class Booking implements Serializable{
	private String transactionID = "";
	
	Booking(String transactionID){
		setTransactionID(transactionID);
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String tID){
		this.transactionID = tID;
	}
	
}
