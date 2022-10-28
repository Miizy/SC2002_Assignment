import java.io.IOException;

public class Testing {
	public static void main(String args[]) throws IOException {
		PricingList pricing = new PricingList();
		Theatre t1 = new Theatre(1,1);
		t1.initializeSeats();
		t1.showSeats();
		pricing.PriceList();
		double price = pricing.getPrice(TicketType.ST, MovieType.RD, false, false);
		System.out.println("Price = " + price);
	}
}
