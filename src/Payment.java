import java.util.Scanner;
import java.util.Calendar;
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
	public TicketType chooseTicketType() { //to implement holiday checking && loyalty cards
		TicketType TickType = TicketType.EM;
		int hr = Calendar.HOUR_OF_DAY;
		int day = Calendar.DAY_OF_WEEK;
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
}
