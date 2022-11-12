import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * PaymentController handles the payment base on what ticket is being booked
 */
public class PaymentController extends PricingListBoundary implements Serializable{
	/**
	 * price of ticket
	 */
	private double price;
	/**
	 * whether the movie goer has paid for the ticket
	 */
	private boolean paid;
	/**
	 * change to return to the movie goer
	 */
	private double change;
	/**
	 * unique transaction ID for the booking
	 */
	private String TID;
	Scanner scan = new Scanner(System.in);
	/**
	 *Constructor with initial values
	 */
	public PaymentController() {
		price = 0;
		paid = false;
		change = 0;
	}
	/**
	 * Generate the transaction ID based on the time and theatreID
	 * @param theatreID unique theatreID
	 * @param time current time
	 * @return unique transaction ID
	 */
	public String GetTID(String theatreID, Calendar time) {
		DecimalFormat dateFormat = new DecimalFormat("00");
		TID = theatreID + String.valueOf(time.get(Calendar.YEAR)) + dateFormat.format(Double.valueOf(time.get(Calendar.MONTH))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.DAY_OF_WEEK))) + dateFormat.format(Double.valueOf(time.get(Calendar.HOUR_OF_DAY))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.MINUTE)));
		return TID;
	}
	/**
	 * calculate the total price for the transaction
	 * @param ticketArray Array for all tickets selected
	 * @param noTicks Number of tickets in ticketArray
	 * @param priceList list of prices for the different type of tickets
	 * @return the total price for the transaction
	 */
	public double totalPrice(Tickets[] ticketArray, int noTicks, PricingList priceList) {
		price = 0.00;
		for(int a = 0; a < noTicks; a++) {
			price += getPrice(ticketArray[a], priceList);
		}
		return price;
	}
	/**
	 * Get the total price for the transaction
	 * @return the total price for the transaction
	 */
	public double getPayment() {
		return price;
	}
	/**
	 * Check if the amount paid is sufficient, return true if fully paid for
	 * @param pay Amount paid for the transaction
	 * @return true if amount is fully paid
	 */
	public boolean checkPayment(double pay) {
		if (pay == price) {
			paid = true;
		}
		else if(pay < price) {
			paid = false;
		}
		else {
			paid = true;
			setchange(pay);
		}
		return paid;
	}
	
	/**
	 * Check if the transaction has been paid
	 * @return true if the transaction has been paid
	 */
	public boolean checkPaid() {
		return paid;
	}
	
	/**
	 * Calculate the change to return
	 * @param pay Amount paid for the transaction
	 */
	public void setchange(double pay) {
		change = pay - price;
	}
	/**
	 * Get the change to return
	 * @return change for transaction
	 */
	public double getchange() {
		return change;
	}

	/**
	 * get the price for the ticket
	 * @param ticket Ticket type, e.g Monday, Tuesday, Senior, Student
	 * @param Pricelist List of prices for the different ticket types
	 * @return the price of the ticket
	 */
	public double getPrice(Tickets ticket, PricingList Pricelist) {
		double price = 0.00;
		boolean BlockBuster = ticket.getBB();
		boolean Sneakpreview = ticket.getSneak();
		SeatStatus Seat = ticket.getSeatStatus();
		MovieType Type = ticket.getMovieType();
		TheatreClass TC = ticket.getTheatreClass();
		switch(ticket.getTicketType()) {
		case SC:
			price = Pricelist.getSenior();
			break;
		case ST:
			if(Type == MovieType.RD) {
				price = Pricelist.getStudent();
			}
			else {
				price = Pricelist.getStudent3D();
			}
			break;
		case MW:
			if(Type == MovieType.RD) {
				if(Sneakpreview) {
					price = Pricelist.getSneakpreview();
				}
				else{
					price = Pricelist.getMonWed();
				}
			}
			else {
				price = Pricelist.getMonWed3D();
			}
			break;
		case TH:
			if(Type == MovieType.RD) {
				price = Pricelist.getThur();
			}
			else {
				price = Pricelist.getThur3D();
			}
			break;
		case FB:
			if(Type == MovieType.RD) {
				price = Pricelist.getFriB();
			}
			else {
				price = Pricelist.getFriB3D();
			}
			break;
		case FA:
			if(Type == MovieType.RD) {
				price = Pricelist.getFriA();
			}
			else {
				price = Pricelist.getFriA3D();
			}
			break;
		case SS:
			if(Type == MovieType.RD) {
				price = Pricelist.getSS();
			}
			else {
				price = Pricelist.getSS3D();
			}
			break;
		case CL:
			//price =not implemented; 
			break;
		default:
			break;
		}
		
		if(BlockBuster) { //if blockbuster
			price += Pricelist.getBlockBusterPrice();
		}
		switch(TC) {
		case plat:
			break;
		case elit:
			price += Pricelist.getEliteTheatrePrice();
			break;
		default:
			break;
		}
		switch(Seat) {
		case ac: //couple seat
			price = price*2 + Pricelist.getCouplePrice();
			break;
		case ae: //elite seat
			price += Pricelist.getEliteSeat();
			break;
		case an:
			break;
		default:
			break;
		}
		return price;
	}
}
