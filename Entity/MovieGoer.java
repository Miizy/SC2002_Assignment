import java.io.Serializable;
import java.util.ArrayList;
/**
 * MovieGoer is the class that represents the Movie goers
 */
public class MovieGoer implements Serializable{
	/**
	 * Unique ID for each moviegoer
	 */
	private int goerID;
	/**
	 * Name of the moviegoer
	 */
	private String name;
	/**
	 * Mobile number of moviegoer
	 */
	private String mobileNumber;
	/**
	 * Email of moviegoer
	 */
	private String email;
	/**
	 * Record of Transaction ID for past bookings made by the user
	 */
	private ArrayList<String> pastBooking = new ArrayList<String>();
	/**
	 * Constructs the Movie goer object and sets all of their given info
	 * @param goerID Unique ID for each moviegoer
	 * @param name Name of the moviegoer
	 * @param number Mobile number of moviegoer
	 * @param email Email of moviegoer
	 */
	MovieGoer(int goerID, String name, String number, String email){
		setGoerID(goerID);
		setName(name);
		setEmail(email);
		setMobileNumber(number);
	}

	/**
	 * Gets the ID of the moviegoer
	 * @return the ID of the moviegoer
	 */
	public int getGoerID(){
		return goerID;
	}

	/**
	 * Sets the ID of the moviegoer
	 * @param goerID Unique ID for each moviegoer
	 */
	private void setGoerID(int goerID){
		this.goerID = goerID;
	}
	
	/**
	 * Gets the name of the moviegoer
	 * @return the name of the moviegoer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the moviegoer
	 * @param name Name of the moviegoer
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the mobile number of the moviegoer
	 * @return the mobile number of the moviegoer
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	/**
	 * Sets the mobile number of the moviegoer
	 * @param mobileNumber Mobile number of moviegoer
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * Gets the email of the moviegoer
	 * @return the email of the moviegoer
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email of the moviegoer
	 * @param email Email of moviegoer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the list of past bookings made by the moviegoer
	 * @return the list of past bookings made by the moviegoer
	 */
	public ArrayList<String> getpastBooking() {
		return pastBooking;
	}
	
	/**
	 * Adds a transactionID to the list of past booking made by the moviegoer
	 * @param transactionID unique transaction ID generated during the purchase of a movie ticket
	 */
	public void addBooking(String transactionID) {
		this.pastBooking.add(transactionID);
	}
	
}
