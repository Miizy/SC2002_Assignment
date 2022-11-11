import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.text.DecimalFormat;

public class PaymentController extends PricingListBoundary implements Serializable{
	private double price = 0.00;
	private boolean paid = false;
	private double change = 0.00;
	private String TID;
	Scanner scan = new Scanner(System.in);
	public void Reset() {
		price = 0;
		paid = false;
		change = 0;
	}
	public String GetTID(String theatreID, Calendar time) {
		DecimalFormat dateFormat = new DecimalFormat("00");
		TID = theatreID + String.valueOf(time.get(Calendar.YEAR)) + dateFormat.format(Double.valueOf(time.get(Calendar.MONTH))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.DAY_OF_WEEK))) + dateFormat.format(Double.valueOf(time.get(Calendar.HOUR_OF_DAY))) + 
				dateFormat.format(Double.valueOf(time.get(Calendar.MINUTE)));
		return TID;
	}
	public double totalPrice(Tickets[] Ticketarray, int noTicks, PricingList Pricelist) {
		price = 0.00;
		for(int a = 0; a < noTicks; a++) {
			price += getPrice(Ticketarray[a], Pricelist);
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
