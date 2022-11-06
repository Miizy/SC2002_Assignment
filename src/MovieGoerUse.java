import java.util.Scanner;
import java.util.Calendar;
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
			Cinema cinema = GoerOptions(cineplex.getCinema(choice-1), cineplex);
			cineplex.setCinema(cinema.getCinemaID(), cinema);
			return cineplex;
	}

	private static Cinema GoerOptions(Cinema cinema, Cineplex cineplex){
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
						break;
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
						getSeatAvailability(cinema);
					}else{
						System.out.println("No Movies Available. Sorry.");
					}
					break;
				case 4://Book and purchase tickets
					if(!cinema.getListOfMovie().isEmpty()){
						paymentSeats(cinema,cineplex.getGoer(GoerID));
					}else{
						System.out.println("No Movies Available. Sorry.");
					}
					break;
				case 5://View Boooking History
					getBookingHistory(cineplex);
					break;
				case 6://List top 5
					if(!cinema.getListOfMovie().isEmpty()){
						System.out.println("Top 5 Movies:  ");
						printListofTopMovies(cinema);
					}else{
						System.out.println("No Movies to View: ");
					}
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

	public static void getBookingHistory(Cineplex cineplex){
		System.out.println("===== Booking History =====");
		System.out.println("Name: " + cineplex.getGoer(GoerID).getName());
		System.out.println("Email: " + cineplex.getGoer(GoerID).getEmail());
		System.out.println("Phone No.: " + cineplex.getGoer(GoerID).getMobileNumber());
		System.out.println("---------------------------");
		if(cineplex.getGoer(GoerID).getpastBooking().isEmpty()){
			System.out.println("User has no booking history available.");
		}else{
			for(int i=0;i<cineplex.getGoer(GoerID).getpastBooking().size();i++){
				System.out.println(i+1 + ".   " + cineplex.getGoer(GoerID).getpastBooking().get(i).getTransactionID());
			}
		}
		System.out.println("---------------------------");
	}

	

	public static void getSeatAvailability(Cinema cinema){
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Movie: ");
		int selection[] = selectMovieAndTheatre(cinema);
		int id = selection[0];
		Theatre theatre = cinema.getTheatre(id - 1);
		System.out.println("Select Timeslot: ");
		int TS = (sc.nextInt() - 1);	
		theatre.getTimeslot().get(TS).getSeatTing().showSeats();
	}

	public static void paymentSeats(Cinema cinema, MovieGoer goer){
		Scanner sc = new Scanner(System.in);
		Payment Price = new Payment();
		double total = 0;
		SeatStatus SS = SeatStatus.ap;
		int selection[] = selectMovieAndTheatre(cinema);
		int id = selection[0];
		int mc = selection[1];
		Theatre theatre = cinema.getTheatre(id - 1);
		String ID = theatre.getTheatreID();
		TheatreClass TC = theatre.getTheatreClass(); //theatreclass
		System.out.println("Select Timeslot: ");
		int TS = sc.nextInt() - 1;					//TS index
		Price.PriceList(); //listing out pricing list
		MovieType MT = Price.chooseMovieType();//choose MovieType//replace in future
		System.out.println("Select Number of Tickets:");
		int noTick = sc.nextInt();
		Tickets[] Ticketarray = new Tickets[noTick];
		for(int a = 0; a<noTick;a++) {
			/*Select & Book Seats Code here*/
			//show seat
			theatre.getTimeslot().get(TS).getSeatTing().showSeats();
			TicketType TT = Price.chooseTicketType();//choose student etc.
			while(true) {
				System.out.print("Select Row: ");
				int Row = sc.nextInt();
				System.out.print("Select Col: ");
				int Col = sc.nextInt();

				SS = theatre.getTimeslot().get(TS).getSeatTing().getSeatAt(Col, Row).getSeatType();

				if (SS != SeatStatus.ap) {
					if(theatre.getTimeslot().get(TS).getSeatTing().getSeatAt(Col, Row).getbook() == false) { //empty seat
						theatre.getTimeslot().get(TS).getSeatTing().getSeatAt(Col, Row).bookseat(); //book seat
						break;
					}
					else {
						System.out.println("Seat is already Booked!!");
					}
				}
				else {
					System.out.println("You have selected a passage, Please select a seat");
				}
			}
			//type of seat //save under SS
			//======================
			Ticketarray[a] = new Tickets(MT, TT, cinema.getMovie(mc-1).getBlockBuster(), cinema.getMovie(mc-1).getSneakpreview(), TC, SS);
		}
		double sum = Price.totalPrice(Ticketarray, noTick);
		System.out.println("Total Price = " + sum);
		cinema.getListOfMovie().get(mc-1).addSales(sum);
		double payment = 0;
			while(!Price.checkPaid()) {
				System.out.println("Payment Received: ");
				payment = sc.nextDouble();
				total += payment;
				Price.checkPayment(total);
			}
		System.out.println("Change Given: " + Price.getchange());
		String tID = Price.GetTID(ID, Calendar.getInstance());
		System.out.println("Transaction ID: " + tID);
		goer.addBooking(tID);
	}

	private static int []selectMovieAndTheatre(Cinema cinema){
		int numOfMovies = cinema.getListOfMovie().size();
		int numOfTheaters = cinema.getListOfTheatre().size();
		int[] movieAvail = new int[numOfMovies];
		int[] theatreAvail = new int[numOfTheaters];
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Movie: ");
		movieAvail = listofMoviesWithShowtimes(cinema);
		int mc;
		while(true){
			mc = sc.nextInt(); //selected the movie
			if(movieAvail[mc-1]==1){
				break;
			}else{
				System.out.println("Movie has no TimeSlots. Enter another movie.");
			}

		}
		System.out.println();
		//check which theatre has the movie //
		System.out.println("Select Theatre ID: ");
		theatreAvail = listofAvaliableTheater(cinema, mc);
		int id;
		while(true){
			id = sc.nextInt(); //selected the movie
			if(theatreAvail[id-1]>0){
				break;
			}else{
				System.out.println("Theatre has no TimeSlots. Enter another Theatre.");
			}

		}
		return new int[] {id, mc};
	}

	private static int[] listofAvaliableTheater(Cinema cinema, int movieChoice){
		int numOfTheaters = cinema.getListOfTheatre().size();
		int[] availArr = new int[numOfTheaters];
		
		for(int t =0; t<numOfTheaters; t++) {
			int timeslotCount = cinema.getListOfTheatre().get(t).getTimeslot().size();
			if(timeslotCount>0){
				System.out.println((t+1)+". Theatre:"+ cinema.getListOfTheatre().get(t).getTheatreID());
				for(int tslot=0; tslot<timeslotCount; tslot++) {
					if(Objects.equals(cinema.getListOfTheatre().get(t).getTimeslot().get(tslot).getMovie().getMovieTitle(), cinema.getMovie(movieChoice-1).getMovieTitle())) {
						System.out.println("	"+(tslot+1)+". Timeslot: " + cinema.getListOfTheatre().get(t).getTimeslot().get(tslot).getStartTime().getTime());					
					}
				}
				availArr[t] = timeslotCount;
			}else{
				System.out.println((t+1)+". Theatre:"+ cinema.getListOfTheatre().get(t).getTheatreID() + "  <No Movies allocated here>");
				availArr[t] = 0;
			}
			
		}

		return availArr;
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

	private static int[] listofMoviesWithShowtimes(Cinema cinema){
		int numOfMovies = cinema.getListOfMovie().size();
		int[] availArr = new int[numOfMovies];
		for(int i=0; i<numOfMovies;i++){
			int timeslotCount = 0;
			for(int t =0; t<cinema.getListOfTheatre().size(); t++) {
				for(int tslot=0; tslot<cinema.getListOfTheatre().get(t).getTimeslot().size(); tslot++) {
					if(Objects.equals(cinema.getListOfTheatre().get(t).getTimeslot().get(tslot).getMovie().getMovieTitle(), cinema.getMovie(i).getMovieTitle())) {
						timeslotCount++;					
					}
				}
			}
			if (timeslotCount>0){
				System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
				availArr[i] = 1;
			}else{
				System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle() + "   <Time Slots unavailable for this movie>");
				availArr[i] = 0;
			}
		}
		return availArr;
	}
	

	private static void printListofMovies(Cinema cinema){
		for(int i=0; i<cinema.getListOfMovie().size();i++){
			System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
		}
		System.out.println();
	}
	
	private static void printListofTopMovies(Cinema cinema){
		int numOfMovies = cinema.getListOfMovie().size();
		int[] movieIndex = new int[numOfMovies];
		float[] movieRatings = new float[numOfMovies];
		for(int i=0; i<numOfMovies;i++){
			movieIndex[i] = i;
			movieRatings[i] = cinema.getMovie(i).getOverallRating();
		}

		for(int i=1; i<numOfMovies; i++){
			float k = movieRatings[i];
			int l = movieIndex[i];
			int j = i-1;
			while((j>-1)&&(movieRatings[j]>k)){
				movieRatings[j+1] = movieRatings[j];
				movieIndex[j+1] = movieIndex[j];
				j--;
			}
			movieRatings[j+1] = k;
			movieIndex[j+1] = l;
		}
		int j = 1;
		for(int i=numOfMovies-1; i>=0; i--){

			if(cinema.getMovie(movieIndex[i]).getOverallRating() == 0.0){
				System.out.println(j + ". " + cinema.getMovie(movieIndex[i]).getMovieTitle() +"   "+ "<<No ratings available>>");
			}else{
				System.out.println(j + ". " + cinema.getMovie(movieIndex[i]).getMovieTitle() +"   "+ cinema.getMovie(movieIndex[i]).getOverallRating() + "*");
			}
		
			j++;
			if(j>5) break;
		}
	}
	
	private static Cinema writeReview(Cinema cinema, int movieChoice, String name){
		Scanner sc = new Scanner(System.in);
		int rating;
		String review;
		if(!cinema.getMovie(movieChoice-1).getPastReview().isEmpty()){
			for(int i=0;i<cinema.getMovie(movieChoice-1).getNamesOfPastReviewers().size();i++){
				if(Objects.equals(cinema.getMovie(movieChoice-1).getNameofReviewer(i), name)){
					System.out.println("Only one review per user allowed per movie!");
					return cinema;
				}
			}
			printPastReviews(cinema, movieChoice);
			System.out.println();
		}
		System.out.println("Enter your ratings for " + cinema.getMovie(movieChoice-1).getMovieTitle()+ " (1 to 5):  ");
		while(true){
			rating = sc.nextInt();
			if(rating>=1&&rating<=5){
				break;
			}else{
				System.out.println("Invalid Entry. Try Again. Only ratings from 1 to 5 are allowed!");
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
			System.out.println(cinema.getMovie(movieChoice-1).getReview(i).getRating() + "*");
			System.out.println(cinema.getMovie(movieChoice-1).getReview(i).getReview());
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
