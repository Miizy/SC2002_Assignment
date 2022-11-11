import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

public class Payment extends PricingList{
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
	public TicketType chooseTicketType(Theatre theatre, int index) { //to implement holiday checking && loyalty cards
		TicketType TickType = TicketType.EM;
		int hr = theatre.getTimeslot().get(index).getStartTime().getTime().getHours();
		int day = theatre.getTimeslot().get(index).getStartTime().getTime().getDay();
		switch (day) {
		case 1/*Monday*/:
		case 2/*Tuesday*/:
		case 3/*Wednesday*/:
			if(hr < 18) {//if before 6pm
				System.out.println("Select TicketType: ");
				System.out.println("=======================");
				System.out.println("1. Senior Citizens");
				System.out.println("2. Students");
				System.out.println("3. Adult");
				while(true) {
					int select = scan.nextInt();
					switch(select) {
					case 1:
						TickType = TicketType.SC;
						break;
					case 2:
						TickType = TicketType.ST;
						break;
					case 3:
						TickType = TicketType.MW;
						break;
					default:
						break;
					}
					if(select<=3 && select >0) {
						break;
					}
				}
			}
			else {
				TickType = TicketType.MW;
			}
			break;
		case 4/*Thursday*/:
			if(hr < 18) {//if before 6pm
				System.out.println("Select TicketType: ");
				System.out.println("=======================");
				System.out.println("1. Senior Citizens");
				System.out.println("2. Students");
				System.out.println("3. Adult");
				while(true) {
					int select = scan.nextInt();
					switch(select) {
					case 1:
						TickType = TicketType.SC;
						break;
					case 2:
						TickType = TicketType.ST;
						break;
					case 3:
						TickType = TicketType.TH;
						break;
					default:
						break;
					}
					if(select<=3 && select >0) {
						break;
					}
				}
			}
			else{
				TickType = TicketType.TH;
				}
			break;
		case 5/*Friday*/:
			if(hr < 18) {//if before 6pm
				System.out.println("Select TicketType: ");
				System.out.println("=======================");
				System.out.println("1. Senior Citizens");
				System.out.println("2. Students");
				System.out.println("3. Adult");
				while(true) {
					int select = scan.nextInt();
					switch(select) {
					case 1:
						TickType = TicketType.SC;
						break;
					case 2:
						TickType = TicketType.ST;
						break;
					case 3:
						TickType = TicketType.FB;
						break;
					default:
						break;
					}
					if(select<=3 && select >0) {
						break;
					}
				}
			}
			else{
				TickType = TicketType.FA;
				}
			break;
		case 6/*Saturday*/:
		case 7/*Sunday*/:
			TickType = TicketType.SS;
			break;
		default:
			break;
		}
		return TickType;
	}
	public void PriceList() {
		System.out.println("Ticket Type                     Regular & Digital Movies                     3D Movies");
		System.out.println("===================================================================================================");
		System.out.printf("Senior Citizens*                $%.2f                                        N.A      \n", getSenior());
		System.out.println("Mon - Fri, before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Students**                      $%.2f                                        $%.2f    \n", getStudent(), getStudent3D());
		System.out.println("Mon - Fri before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");		
		System.out.printf("Mon - Wed#                      $%.2f                                        $%.2f   \n", getMonWed(), getMonWed3D());
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Thu                             $%.2f                                        $%.2f   \n", getThur(), getThur3D());
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Fri                             $%.2f                                        $%.2f   \n", getFriB(), getFriB3D());
		System.out.println("Sessions before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Fri                             $%.2f                                       $%.2f   \n", getFriA(), getFriA3D());
		System.out.println("Sessions after 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Sat & Sun^                      $%.2f                                       $%.2f  \n", getSS(), getSS3D());
		System.out.println("All Sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Preferred Credit &             N.A		                                   N.A      ");
		System.out.println("Loyalty Cards");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("\n* For Patreons 55 years & older. Not valid on PH/eve of PH");
		System.out.println("**Not valid on PH/eve of PH");
		System.out.println("^Include PH/eve of PH and weekend sneaks");	
		System.out.printf("#Sneak preview at $%.2f between Mon to Wed, excluding PH/eve of PH\n", getSneakpreview());
		System.out.printf("\n\nTickets for movies denoted as 'BlockBuster' will be charged at $%.2f more than the prevailing rate\n", getBlockBusterPrice());
		System.out.println("---------------------------------------------------------------------------------------------------");
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
