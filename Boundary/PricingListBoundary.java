import java.io.Serializable;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;

public class PricingListBoundary{
	public void PriceList(PricingList Pricelist) {
		System.out.println("Ticket Type                     Regular & Digital Movies                     3D Movies");
		System.out.println("===================================================================================================");
		System.out.printf("Senior Citizens*                $%.2f                                        N.A      \n", Pricelist.getSenior());
		System.out.println("Mon - Fri, before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Students**                      $%.2f                                        $%.2f    \n", Pricelist.getStudent(), Pricelist.getStudent3D());
		System.out.println("Mon - Fri before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");		
		System.out.printf("Mon - Wed#                      $%.2f                                        $%.2f   \n", Pricelist.getMonWed(), Pricelist.getMonWed3D());
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Thu                             $%.2f                                        $%.2f   \n", Pricelist.getThur(), Pricelist.getThur3D());
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Fri                             $%.2f                                        $%.2f   \n", Pricelist.getFriB(), Pricelist.getFriB3D());
		System.out.println("Sessions before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Fri                             $%.2f                                       $%.2f   \n", Pricelist.getFriA(), Pricelist.getFriA3D());
		System.out.println("Sessions after 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("Sat & Sun^                      $%.2f                                       $%.2f  \n", Pricelist.getSS(), Pricelist.getSS3D());
		System.out.println("All Sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Preferred Credit &              N.A		                               N.A      ");
		System.out.println("Loyalty Cards");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("\n* For Patreons 55 years & older. Not valid on PH/eve of PH");
		System.out.println("**Not valid on PH/eve of PH");
		System.out.println("^Include PH/eve of PH and weekend sneaks");	
		System.out.printf("#Sneak preview at $%.2f between Mon to Wed, excluding PH/eve of PH\n", Pricelist.getSneakpreview());
		System.out.printf("\n\nTickets for movies denoted as 'BlockBuster' will be charged at $%.2f more than the prevailing rate\n", Pricelist.getBlockBusterPrice());
		System.out.println("---------------------------------------------------------------------------------------------------");
	}
	public TicketType chooseTicketType(Cinema cinema,Theatre theatre, int index) { //to implement loyalty cards
		Scanner scan = new Scanner(System.in);
		TicketType TickType = TicketType.EM;
		int hr = theatre.getTimeslot().get(index).getStartTime().getTime().getHours();
		int day = theatre.getTimeslot().get(index).getStartTime().getTime().getDay();
		
		ArrayList<GregorianCalendar> holidayList = cinema.getHolidayList();
		boolean holidayCheck=false;
		int bookedMonth = theatre.getTimeslot().get(index).getStartTime().get(Calendar.MONTH);
		int bookedDate = theatre.getTimeslot().get(index).getStartTime().get(Calendar.DATE);
		int bookedYear = theatre.getTimeslot().get(index).getStartTime().get(Calendar.YEAR);
		for(int i=0;i<cinema.getHolidayList().size();i++) {
			if((bookedMonth==cinema.getHolidayList().get(i).get(Calendar.MONTH))&&(bookedDate==cinema.getHolidayList().get(i).get(Calendar.DATE))&&(bookedYear==cinema.getHolidayList().get(i).get(Calendar.YEAR))){
				holidayCheck = true;
				break;
			}
		}
		switch (day) {
		case 1/*Monday*/:
		case 2/*Tuesday*/:
		case 3/*Wednesday*/:
			if(hr < 18 && !holidayCheck) {//if before 6pm
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
			if(hr < 18 && !holidayCheck) {//if before 6pm
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
			if(hr < 18 && !holidayCheck) {//if before 6pm
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
