
public class PricingList {
	public void PriceList() {
		System.out.println("Ticket Type                     Regular & Digital Movies                     3D Movies");
		System.out.println("===================================================================================================");
		System.out.println("Senior Citizens*                $4.00                                        N.A      ");
		System.out.println("Mon - Fri, before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Students**                      $7.00                                        $9.00    ");
		System.out.println("Mon - Fri before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");		
		System.out.println("Mon - Wed#                      $8.50                                        $11.00   ");
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Thu                             $9.50                                        $11.00   ");
		System.out.println("All sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Fri                             $9.50                                        $15.00   ");
		System.out.println("Sessions before 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Fri                             $11.00                                       $15.00   ");
		System.out.println("Sessions after 6pm");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Sat & Sun^                      $11.00                                       $15.00   ");
		System.out.println("All Sessions");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Preferred Credit &              $7 - $9.50                                   N.A      ");
		System.out.println("Loyalty Cards");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("\n* For Patreons 55 years & older. Not valid on PH/eve of PH");
		System.out.println("**Not valid on PH/eve of PH");
		System.out.println("^Include PH/eve of PH and weekend sneaks");	
		System.out.println("#Sneak preview at $9.50 between Mon to Wed, excluding PH/eve of PH");
		System.out.println("\n\nTickets for movies denoted as 'BlockBuster' will be charged at $1 more than the prevailing rate");
		System.out.println("---------------------------------------------------------------------------------------------------");
	}
	public double getPrice(TicketType Ticket, MovieType Movie, boolean Blockbuster, boolean Sneak) {
		double price = 0.00;
		boolean yn = Blockbuster;
		boolean Sneakpreview = Sneak;
		MovieType Type = Movie;
		switch(Ticket) {
		case SC:
			price = 4.00;
			break;
		case ST:
			if(Type == MovieType.RD) {
				price = 7.00;
			}
			else {
				price = 9.00;
			}
			break;
		case MW:
			if(Type == MovieType.RD) {
				if(Sneak) {
					price = 9.50;
				}
				else{
					price = 8.50;
				}
			}
			else {
				price = 11.00;
			}
			break;
		case TH:
			if(Type == MovieType.RD) {
				price = 9.50;
			}
			else {
				price = 11.00;
			}
			break;
		case FB:
			if(Type == MovieType.RD) {
				price = 9.50;
			}
			else {
				price = 15.00;
			}
			break;
		case FA:
			if(Type == MovieType.RD) {
				price = 11.00;
			}
			else {
				price = 15.00;
			}
			break;
		case SS:
			if(Type == MovieType.RD) {
				price = 11.00;
			}
			else {
				price = 15.00;
			}
			break;
		case CL:
			//price = idk; 
			break;
		default:
			break;
		}
		
		if(yn) {
			price++;
		}
		return price;
	}
}
