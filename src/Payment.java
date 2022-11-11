import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

public class Payment extends PricingListBoundary{
	private double price = 0.00;
	private boolean paid = false;
	private double change = 0.00;
	private String TID;
	Scanner scan = new Scanner(System.in);
	public String GetTID(String theatreID, Calendar time) {
		DecimalFormat dateFormat = new DecimalFormat("00");
		TID = theatreID + String.valueOf(time.get(Calendar.YEAR)) + dateFormat.format(Double.valueOf(time.get(Calendar.MONTH))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.DAY_OF_WEEK))) + dateFormat.format(Double.valueOf(time.get(Calendar.HOUR_OF_DAY))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.MINUTE)));
		return TID;
	}
	public double totalPrice(Tickets[] Ticketarray, int noTicks) {
		price = 0.00;
		for(int a = 0; a < noTicks; a++) {
			price += getPrice(Ticketarray[a]);
		}
		return price;
	}
	public double getPayment() {
		return price;
	}
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
	public boolean checkPaid() {
		return paid;
	}
	public void setchange(double pay) {
		change = pay - price;
	}
	public double getchange() {
		return change;
	}

	public double getPrice(Tickets ticket) {
		double price = 0.00;
		boolean BlockBuster = ticket.getBB();
		boolean Sneakpreview = ticket.getSneak();
		SeatStatus Seat = ticket.getSeatStatus();
		MovieType Type = ticket.getMovieType();
		TheatreClass TC = ticket.getTheatreClass();
		switch(ticket.getTicketType()) {
		case SC:
			price = getSenior();
			break;
		case ST:
			if(Type == MovieType.RD) {
				price = getStudent();
			}
			else {
				price = getStudent3D();
			}
			break;
		case MW:
			if(Type == MovieType.RD) {
				if(Sneakpreview) {
					price = getSneakpreview();
				}
				else{
					price = getMonWed();
				}
			}
			else {
				price = getMonWed3D();
			}
			break;
		case TH:
			if(Type == MovieType.RD) {
				price = getThur();
			}
			else {
				price = getThur3D();
			}
			break;
		case FB:
			if(Type == MovieType.RD) {
				price = getFriB();
			}
			else {
				price = getFriB3D();
			}
			break;
		case FA:
			if(Type == MovieType.RD) {
				price = getFriA();
			}
			else {
				price = getFriA3D();
			}
			break;
		case SS:
			if(Type == MovieType.RD) {
				price = getSS();
			}
			else {
				price = getSS3D();
			}
			break;
		case CL:
			//price =not implemented; 
			break;
		default:
			break;
		}
		
		if(BlockBuster) { //if blockbuster
			price += getBlockBusterPrice();
		}
		switch(TC) {
		case plat:
			break;
		case elit:
			price+=getEliteTheatrePrice();
			break;
		default:
			break;
		}
		switch(Seat) {
		case ac: //couple seat
			price = price*2 + getCouplePrice();
			break;
		case ae: //elite seat
			price += getEliteSeat();
			break;
		case an:
			break;
		default:
			break;
		}
		return price;
	}
}
