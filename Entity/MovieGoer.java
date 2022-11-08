import java.io.Serializable;
import java.util.ArrayList;

public class MovieGoer implements Serializable{
	private int goerID;
	private String name;
	private String mobileNumber;
	private String email;
	private ArrayList<String> pastBooking = new ArrayList<String>();

	MovieGoer(int goerID, String name, String number, String email){
		setGoerID(goerID);
		setName(name);
		setEmail(email);
		setMobileNumber(number);
	}

	public int getGoerID(){
		return goerID;
	}

	private void setGoerID(int goerID){
		this.goerID = goerID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<String> getpastBooking() {
		return pastBooking;
	}
	public void addBooking(String transactionID) {
		this.pastBooking.add(transactionID);
	}
	
}
