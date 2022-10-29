import java.util.Scanner;
import java.util.random.RandomGenerator.ArbitrarilyJumpableGenerator;
import java.util.ArrayList;

public class MovieGoerUse {

	public static Cineplex MovieGoerChoice(Cineplex cineplex) {
		
		//MovieGoerLogin();
		cineplex = GoerCinema(cineplex);
		return cineplex;
	}

	public void MovieGoerLogin() {
		
	}

	private static Cineplex GoerCinema(Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		int choice = Integer.MAX_VALUE;
			System.out.println("Select Cinema");
			for(int i=0; i<cineplex.getListOfCinema().size(); i++) {
				System.out.print((i+1) + ". Cinema ID " + cineplex.getCinema(i).getCinemaID());
				if(cineplex.getCinema(i).getCinemaName() != "") {
					System.out.print(", " + cineplex.getCinema(i).getCinemaName());
				}
				System.out.println();
			}
			while(choice > cineplex.getListOfCinema().size() || choice <= 0) {
				if(choice != Integer.MAX_VALUE) {
					System.out.println("Invalid choice, please try again.");
				}
				choice = sc.nextInt();
			}
			Cinema cinema = GoerOptions(cineplex.getCinema(choice-1));
			cineplex.setCinema(cinema.getCinemaID(), cinema);
			return cineplex;
	}

	private static Cinema GoerOptions(Cinema cinema){
		Scanner sc = new Scanner(System.in);
		int choice;
		do{
			printUIChoice();
			choice = sc.nextInt();
			switch(choice){
				case 1:// Search/List Movies
					for(int i=0; i<cinema.getListOfMovie().size();i++){
						System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
					}
					System.out.println();
					break;
				case 2://View Movie Details
					System.out.println("Select to see the details: ");
					//printListOfMovies();
					for(int i=0; i<cinema.getListOfMovie().size();i++){
						System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
					}
					System.out.println();
					int movieChoice = sc.nextInt();
					System.out.println("*************************");
					System.out.println(cinema.getMovie(movieChoice-1).getMovieTitle() + ": ");
					System.out.println(cinema.getMovie(movieChoice-1).getDirector() + ": ");
					System.out.println(cinema.getMovie(movieChoice-1).getSynopsis() + ": ");
					if(cinema.getMovie(movieChoice-1).getBlockBuster() == true) {
						System.out.println("BlockBuster Movie" + ": ");
					}
					if(cinema.getMovie(movieChoice-1).getSneakpreview() == true) {
						System.out.println("BlockBuster Movie" + ": ");
					}
					break;
				case 3://Check Seat Availabliltiy
					break;
				case 4://Book and purchase tickets   //still have to do booking for this case
					Payment Price = new Payment();
					double total = 0;
					for(int i=0; i<cinema.getListOfMovie().size();i++){// search/list movies
						System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
					}
					int mc = sc.nextInt();
					System.out.println();
					Price.PriceList(); //listing out pricing list
					MovieType MT = Price.chooseMovieType();//choose MovieType
					System.out.println("Select Number of Tickets:");
					int noTick = sc.nextInt();
					Tickets[] Ticketarray = new Tickets[noTick];
					for(int a = 0; a<noTick;a++) {
						/*Select & Book Seats Code here*/
						//======================
						TicketType TT = Price.chooseTicketType();
						Ticketarray[a] = new Tickets(MT, TT, cinema.getMovie(mc-1).getBlockBuster(), cinema.getMovie(mc-1).getSneakpreview());
					}
					System.out.println("Total Price = " + Price.totalPrice(Ticketarray, noTick));
					double payment = 0;
					while(!Price.checkPaid()) {
						System.out.println("Payment Received: ");
						payment = sc.nextDouble();
						total += payment;
						Price.checkPayment(total);
					}
					System.out.println("Change Given: " + Price.getchange());
					break;
				case 5://View Booking History 
					
					break;
				case 6://List top 5
					break;
				case 7://Exit 
					break;
				default:
					break;
			}
			 
	
		}while(choice<7);
		return cinema;
	}

	/*public static Cinema printListOfMovies(){
		Cinema cinema = new Cinema();
		
	}*/
	
	public static void printUIChoice(){
		System.out.println("==============================");
		System.out.println("1. Search/List Movies");
		System.out.println("2. View Movie Details");
		System.out.println("3. Check Seat Availabliltiy");
		System.out.println("4. Book and purchase tickets");
		System.out.println("5. View Boooking History");
		System.out.println("6. List top 5");
		System.out.println("7. Exit");
		System.out.println("==============================");
		
		
	}
	
	
}
