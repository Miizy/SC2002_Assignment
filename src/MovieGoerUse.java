import java.util.Scanner;
import java.util.Objects;

public class MovieGoerUse {

	public static int GoerID;
	public static String GoerName;

	public static Cineplex MovieGoerChoice(Cineplex cineplex) {
		Scanner sc = new Scanner (System.in);
		boolean loginSuccess = false;
		System.out.println("Select 1 if you are a new User.");
		System.out.println("Select 2 if you are a current User.");
		do{
			int userChoice = sc.nextInt();
			if(userChoice==1){
				cineplex = MovieGoerSignup(cineplex);
				loginSuccess = true;
			}else if(userChoice==2){
				cineplex = MovieGoerLogin(cineplex);
				loginSuccess = true;
			}else{
				System.out.println("Invalid Entry. Try Again!");
				System.out.println("Select 1 if you are a new User.");
				System.out.println("Select 2 if you are a current User.");
			}
		}while(loginSuccess == false);
		cineplex = GoerCinema(cineplex);
		return cineplex;
	}

	public static Cineplex MovieGoerSignup(Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		System.out.println("---------Sign Up-------------");
		System.out.println("Enter Your name: ");
		String name = enterName(cineplex);
		System.out.println("Enter Your Phone number: ");
		int hpNumber = enterNumber();
		System.out.println("Enter Your Email Address: ");
		String emailAddr = enterEmail();
		if(!cineplex.getListofGoers().isEmpty()){
			GoerID = cineplex.getListofGoers().size() + 1;
		}else{
			GoerID = 0;
		}
		System.out.println();
		cineplex.addGoer(GoerID ,name, hpNumber, emailAddr);
		System.out.println("Welcome, "+ name + "!");
		GoerName = name;
		return cineplex;
	}


	public static Cineplex MovieGoerLogin(Cineplex cineplex) {
		if(cineplex.getListofGoers().isEmpty()){
			System.out.println("No users registered");
			cineplex = MovieGoerSignup(cineplex);
			return cineplex;
		}
		boolean loginSuccess = false;
		boolean goerExists = false;
		System.out.println("---------Log in-------------");
		System.out.println("Enter Your Email Address: ");
		
		do{
			String email = enterEmail();
			for(int i=0;i<cineplex.getListofGoers().size();i++){
				if(Objects.equals(cineplex.getGoer(i).getEmail(), email)){
					goerExists = true;
					GoerID = i;
				}
			}
			if(goerExists==true){
				System.out.println("Enter your phone Number: ");
				int number = enterNumber();
				if(number==cineplex.getGoer(GoerID).getMobileNumber()){
					loginSuccess = true;
					System.out.println("Welcome, " + cineplex.getGoer(GoerID).getName());
					GoerName = cineplex.getGoer(GoerID).getName();
				}else{
					System.out.println("Phone number does not correspond to your email. Try Again!");
				}
				
			}else{
				System.out.println("User does not exist. Try Again!");
			}
		}while(loginSuccess==false);


		return cineplex;
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
					if(!cinema.getListOfMovie().isEmpty()){
						printListofMovies(cinema);
					}
					else {
						System.out.println("No Movies Available. Sorry.");
					}
					break;
				case 2://View Movie Details
					if(!cinema.getListOfMovie().isEmpty()){
						System.out.println("Select to see the details: ");
						printListofMovies(cinema);
					}else{
						System.out.println("No Movies Available. Sorry.");
					}
					int movieChoice = sc.nextInt();
					if(movieChoice<=cinema.getListOfMovie().size()){
						printDetailsofMovie(cinema, movieChoice);
					}else{
						System.out.println("Invalid Input!");
					}	
					break;
				case 3://Check Seat Availabliltiy
					if(!cinema.getListOfMovie().isEmpty()){
						System.out.println("Select Movie: ");
						printListofMovies(cinema);
					}else{
						System.out.println("No Movies Available. Sorry.");
					}
					break;
				case 4://Book and purchase tickets
					Payment Price = new Payment();
					double total = 0;
					if(!cinema.getListOfMovie().isEmpty()){
						System.out.println("Select Movie: ");
						printListofMovies(cinema);
					}else{
						System.out.println("No Movies Available. Sorry.");
						break;
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
				case 5://View Boooking History 
					break;
				case 6://List top 5
					break;
				case 7://Add or View reviews
					if(!cinema.getListOfMovie().isEmpty()){
						System.out.println("Select Movie to review: ");
						printListofMovies(cinema);
					}else{
						System.out.println("No Movies to review. Sorry.");
						break;
					}
					int mvChoice = sc.nextInt();
					if(mvChoice<=cinema.getListOfMovie().size()){
						writeReview(cinema, mvChoice, GoerName);
					}else{
						System.out.println("Invalid Input!");
					}
					break;
				case 8://Exit 
					break;
				default:
					break;
			}
			 
	
		}while(choice<8);
		return cinema;
	}

	public static String enterName(Cineplex cineplex){
		boolean nameExists = false;
		String name;
		Scanner sc = new Scanner(System.in);
		while(true){
			name = sc.nextLine();
			if(!cineplex.getListofGoers().isEmpty()){
				for(int i=0;i<cineplex.getListofGoers().size();i++){
					if(Objects.equals(cineplex.getGoer(i).getName(), name)){
						nameExists = true;
						break;
					}
				}
				if(!nameExists){
					break;
				}else{
					System.out.println("Name already exists, try another name!");
				}
			}else{
				break;
			}
			
		}
		return name;
	}

	public static int enterNumber(){
		Scanner sc = new Scanner(System.in);
		int number;
		while(true){
			number = sc.nextInt();
			if(String.valueOf(number).length() == 8 && //check if number has 8 digits and starts with 8/9
			(Objects.equals(String.valueOf(number).substring(0,1), String.valueOf(8)) || Objects.equals(String.valueOf(number).substring(0,1), String.valueOf(9)))) {
				break;
			}else{
				System.out.println("Invalid input. Please enter a vaild number. Make sure number starts with 8 or 9 and has 8 digits."); 
			}
		}
		return number;
	}

	public static String enterEmail(){
		Scanner sc = new Scanner(System.in);
		String email;
		while(true){
			email = sc.nextLine();
			if(email.contains("@") && email.contains(".com")) {
				break;
			}else{
				System.out.println("Invalid input. Please enter a vaild Email address. Make sure it contains @ and .com.");
			}
		}
		return email;
	}

	private static Cinema printListofMovies(Cinema cinema){
		for(int i=0; i<cinema.getListOfMovie().size();i++){
			System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
		}
		System.out.println();
		return cinema;
	}
	
	private static Cinema writeReview(Cinema cinema, int movieChoice, String name){
		Scanner sc = new Scanner(System.in);
		int rating;
		String review;
		if(!cinema.getMovie(movieChoice-1).getPastReview().isEmpty()){
			for(int i=0;i<cinema.getMovie(movieChoice-1).getNamesOfPastReviewers().size();i++){
				if(cinema.getMovie(movieChoice-1).getNameofReviewer(i)==name){
					System.out.println("Only one review per user allowed per movie!");
					return cinema;
				}
			}
			printPastReviews(cinema, movieChoice);
			System.out.println();
		}
		System.out.println("Enter your ratings for " + cinema.getMovie(movieChoice-1).getMovieTitle()+ " (0 to 5):  ");
		while(true){
			rating = sc.nextInt();
			if(rating>=0&&rating<=5){
				break;
			}else{
				System.out.println("Invalid Entry. Try Again. Only ratings from 0 to 5 are allowed!");
			}
		}
		sc.nextLine();
		System.out.println("Enter your review for " + cinema.getMovie(movieChoice-1).getMovieTitle()+ ":  ");
		review = sc.nextLine();
		cinema.getMovie(movieChoice-1).addReview(review, rating, name);	
		return cinema;
	}

	private static void printPastReviews(Cinema cinema, int movieChoice){
		System.out.println("Rating: " + cinema.getMovie(movieChoice-1).getOverallRating() + "* ");
		System.out.println("-------------------------------------------------------------");
		for(int i = 0; i<cinema.getMovie(movieChoice-1).getNamesOfPastReviewers().size();i++){
			System.out.println(cinema.getMovie(movieChoice-1).getNameofReviewer(i));
			System.out.println(cinema.getMovie(movieChoice-1).getReview(i));
			System.out.println("-------------------------------------------------------------");
		}
	}

	private static Cinema printDetailsofMovie(Cinema cinema, int movieChoice){
		System.out.println("*************************");
		System.out.println(cinema.getMovie(movieChoice-1).getMovieTitle() + ": ");
		System.out.println("  Director: " + cinema.getMovie(movieChoice-1).getDirector());
		System.out.println("  Synopsis: " + cinema.getMovie(movieChoice-1).getSynopsis());
		System.out.println("  BlockBuster: "+ cinema.getMovie(movieChoice-1).getBlockBuster());
		if(cinema.getMovie(movieChoice-1).getBlockBuster() == true) {
			System.out.println("BlockBuster Movie" + ": ");
		}
		if(cinema.getMovie(movieChoice-1).getSneakpreview() == true) {
			System.out.println("Sneak Preview Movie" + ": ");
		}
		printPastReviews(cinema, movieChoice);
		System.out.println("*************************");
		return cinema;
	}
	
	public static void printUIChoice(){
		System.out.println("==============================");
		System.out.println("1. Search/List Movies");
		System.out.println("2. View Movie Details");
		System.out.println("3. Check Seat Availabliltiy");
		System.out.println("4. Book and purchase tickets");
		System.out.println("5. View Boooking History");
		System.out.println("6. List top 5");
		System.out.println("7. Review Movie");
		System.out.println("8. Exit");
		System.out.println("==============================");
	}
	
	
}
