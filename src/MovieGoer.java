import java.util.ArrayList;

public class MovieGoer {
	private String name;
	private int mobileNumber;
	private String email;
	private ArrayList<Booking> pastBooking = new ArrayList<Booking>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public boolean setMobileNumber(int mobileNumber) {
		if(String.valueOf(mobileNumber).length() == 8 && //check if number has 8 digits and starts with 8/9
			(String.valueOf(mobileNumber).substring(0,1) == String.valueOf(8) || String.valueOf(mobileNumber).substring(0,1) == String.valueOf(9))) {
			this.mobileNumber = mobileNumber;
			return true;
		}
		return false;
	}
	public String getEmail() {
		return email;
	}
	public boolean setEmail(String email) {
		if(email.contains("@") && email.contains(".com")) {
			this.email = email;
			return true;
		}
		return false;
	}
	public ArrayList<Booking> getpastBooking() {
		return pastBooking;
	}
	public void addBooking(Booking booking) {
		this.pastBooking.add(booking);
	}
	
}
