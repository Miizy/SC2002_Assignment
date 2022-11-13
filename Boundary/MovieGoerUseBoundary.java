import java.util.Scanner;
import java.util.Calendar;
import java.util.Objects;
/**
 * A boundary class used by Movie Goers as an interface
 */

public class MovieGoerUseBoundary {
	/**
 	* user id of moviegoer
 	*/
	public static int GoerID;
	/**
 	* user name of moviegoer
 	*/
	public static String GoerName;

	/**
	 * Calls the User login if existing user else call user sign up if new user for user authentication
	 * User to log in using Email and Phone number used to sign up
	 * Thereafter choose cinema function to update the current cineplex
	 * @param cineplex to be updated
	 * @return An updated cineplex containing any updates done during the choose cinema function
	 */
	public static Cineplex MovieGoerChoice(Cineplex cineplex) {

		Scanner sc = new Scanner (System.in);
		boolean loginSuccess = false;
		System.out.println("Select 1 if you are a new User.");
		System.out.println("Select 2 if you are a current User.");
		System.out.println("Select 3 to return.");
		do{
			int userChoice = sc.nextInt();
			if(userChoice==1){
				cineplex = MovieGoerSignup(cineplex);
				loginSuccess = true;
			}else if(userChoice==2){
				cineplex = MovieGoerLogin(cineplex);
				loginSuccess = true;
			}else if(userChoice == 3){
				return cineplex;
			}else{
				System.out.println("Invalid Entry. Try Again!");
				System.out.println("Select 1 if you are a new User.");
				System.out.println("Select 2 if you are a current User.");
			}
		}while(loginSuccess == false);
		cineplex = GoerCinema(cineplex);
		return cineplex;
	}

	/**
	 * Creates and authenticates the new user by checking user details
	 * User details contains of unique username, email and phone number
	 * Calls enter name, enter number and enter email
	 * @param cineplex to be updated with user details
	 * @return An updadated cineplex with user details
	 */
	public static Cineplex MovieGoerSignup(Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		System.out.println("---------Sign Up-------------");
		System.out.println("Enter Your name: ");
		String name = enterName(cineplex);
		System.out.println("Enter Your Phone number: ");
		String hpNumber = enterNumber();
		System.out.println("Enter Your Email Address: ");
		String emailAddr = enterEmail();
		if(!cineplex.getListofGoers().isEmpty()){
			GoerID = cineplex.getListofGoers().size();
		}else{
			GoerID = 0;
		}
		System.out.println();
		cineplex.addGoer(GoerID ,name, hpNumber, emailAddr);
		System.out.println("Welcome, "+ name + "!");
		GoerName = name;
		return cineplex;
	}

	/**
	 * Authenticates existing users by checking user details
	 * Checks if there are exisiting users else call user signup
	 * User details contains of unique username, email and phone number
	 * Calls enter name, enter number and enter email
	 * @param cineplex to be updated with user details
	 * @return An updadated cineplex with user details
	 */
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
				String number = enterNumber();
				if(number.equals(cineplex.getGoer(GoerID).getMobileNumber())){
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

	/**
	 * Specifies the cinema to be used and calls the user options functions to edit the cinema
	 * @param cineplex to retrieve cinema from
	 * @return An updated cineplex containing updates from the user option functions
	 */
	private static Cineplex GoerCinema(Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		while(true) {
			int choice = Integer.MAX_VALUE;
			System.out.println("Select Cinema");
			for(int i=0; i<cineplex.getListOfCinema().size(); i++) {
				System.out.print((i+1) + ". Cinema ID " + cineplex.getCinema(i).getCinemaID());
				if(cineplex.getCinema(i).getCinemaName() != "") {
					System.out.print(", " + cineplex.getCinema(i).getCinemaName());
				}
				System.out.println();
			}
			System.out.println((cineplex.getListOfCinema().size() + 1) + ". Logout");
			while(choice > cineplex.getListOfCinema().size()+1 || choice <= 0) {
				if(choice != Integer.MAX_VALUE) {
					System.out.println("Invalid choice, please try again.");
				}
				choice = sc.nextInt();
			}
			if(choice == cineplex.getListOfCinema().size()+1) {
				System.out.println("Logging out...");
				return cineplex;
			}
			Cinema cinema = GoerOptions(cineplex.getCinema(choice-1), cineplex);
			cineplex.setCinema(cinema.getCinemaID(), cinema);
		}
	}
	
	/**
	 * Gives the MovieGoer the option to list movies, movie details, check seat availability, book/purchase tickets, 
	 * view booking history, list top 5 movies based on ratings and add/view reviews
	 * @param cinema The specified cinema to adjust
	 * @param cineplex The current cineplex
	 * @return An updated cinema containing updated movie reviews, ratings and users' booking history
	 */
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
						paymentSeats(cinema,cineplex.getGoer(GoerID), cineplex);
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

	/**
	 * Prints User's booking history, including their name, email, phone no. and transactionID.
	 * @param cineplex to get the specfic moviegoer from
	 */
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
				System.out.println(i+1 + ".   " + cineplex.getGoer(GoerID).getpastBooking().get(i));
			}
		}
		System.out.println("---------------------------");
	}

	/**
	 * Prints seat layout including seat availablity of a specific cinema and a speciic theatre
	 * @param cinema to get the specific theatre and timeslot of a movie 
	 */
	public static void getSeatAvailability(Cinema cinema){
		Scanner sc = new Scanner(System.in);
		int selection[] = selectMovieAndTheatre(cinema);
		if(selection[0]==-1&&selection[1]==-1){
			System.out.println("No Movies or theatre slots available. Try again next Time!");
			return;
		}
		int id = selection[0];
		Theatre theatre = cinema.getTheatre(id - 1);
		System.out.println("Select Timeslot: ");
		int TS = (sc.nextInt() - 1);	
		System.out.println(theatre.getTimeslot().get(TS).getSeating().showSeats());
	}

	/**
	 * Method for purchasing of tickets
	 * Allows for selecting of movies, theatre showtimes, seats and puchasing of tickets
	 * @param cinema to get the specific theatre and timeslot of a movie
	 * @param goer to save the transaction ID generated to the user
	 * @param cineplex to choose ticket types available
	 */
	public static void paymentSeats(Cinema cinema, MovieGoer goer, Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		PaymentController Price = new PaymentController();
		double total = 0;
		SeatStatus SS = SeatStatus.ap;
		int selection[] = selectMovieAndTheatre(cinema);
		if(selection[0]==-1&&selection[1]==-1){
			System.out.println("No Movies or theatre slots available. Try again next Time!");
			return;
		}
		int id = selection[0];
		int mc = selection[1];
		Theatre theatre = cinema.getTheatre(id - 1);
		String ID = theatre.getTheatreID();
		TheatreClass TC = theatre.getTheatreClass(); //theatreclass
		System.out.println("Select Timeslot: ");
		int TS = sc.nextInt() - 1;					//TS index
		Price.PriceList(cineplex.getPriceList()); //listing out pricing list
		MovieType MT = theatre.getTimeslot().get(TS).getMovie().getMovieType();//choose MovieType
		if(MT == MovieType.RD) {
			System.out.println("Movie Type: Regular & Digital Movies");
		}
		else {
			System.out.println("Movie Type: 3D Movies");
		}
		System.out.print("TheatreType: "+ theatre.getTheatreClass());
		if(theatre.getTheatreClass() == TheatreClass.elit) {
			System.out.printf(", Additional Cost: $%.2f", cineplex.getPriceList().getEliteTheatrePrice());		
		}
		System.out.println();
		System.out.print("BlockBuster: "+ theatre.getTimeslot().get(TS).getMovie().getBlockBuster());
		if(theatre.getTimeslot().get(TS).getMovie().getBlockBuster() == true) {
			System.out.printf(", Addtional Cost: $%.2f", cineplex.getPriceList().getBlockBusterPrice());
		}
		System.out.println();
		System.out.println("SneakPreview: "+ theatre.getTimeslot().get(TS).getMovie().getSneakpreview());
		System.out.println("Select Number of Tickets:");
		int noTick = sc.nextInt();
		Tickets[] Ticketarray = new Tickets[noTick];
		for(int a = 0; a<noTick;a++) {
			/*Select & Book Seats Code here*/
			//show seat
			System.out.println(theatre.getTimeslot().get(TS).getSeating().showSeats());
			TicketType TT = TicketType.EM;
			while(true) {
				TT = Price.chooseTicketType(cineplex, theatre, TS);//choose student etc.
				if(TT == TicketType.SC && MT == MovieType.TD) {
					System.out.println("Senior Tickets are unavailable for 3D Movies");
				}
				else {
					break;
				}
			}
			while(true) {
				System.out.print("Select Row: ");
				int Row = sc.nextInt();
				while(Row>6||Row<1) {				//i hardcoded coz idk the size for theatre
					System.out.println("Invalid Input, please select Row(1-6): ");
					Row = sc.nextInt();
				}
				System.out.print("Select Column: "); //i hardcoded coz i dk the size for theatre
				int Col = sc.nextInt();
				while(Col>8||Col<1) {
					System.out.println("Invalid Input, please select Column(1-8): ");
					Col = sc.nextInt();
				}
				SS = theatre.getTimeslot().get(TS).getSeating().getSeatAt(Col, Row).getSeatType();

				if (SS != SeatStatus.ap) {
					if(theatre.getTimeslot().get(TS).getSeating().getSeatAt(Col, Row).getbook() == false) { //empty seat
						theatre.getTimeslot().get(TS).getSeating().getSeatAt(Col, Row).bookseat(); //book seat}
						if(SS == SeatStatus.ac) {
							if(noTick - a > 1) {
								noTick--;
							}
							System.out.printf("Seat Type: Couple Seat, Addtional Cost: $%.2f\n", cineplex.getPriceList().getCouplePrice());
						}
						else if(SS==SeatStatus.ae) {
							System.out.printf("Seat Type: Elite Seat, Addtional Cost: $%.2f\n", cineplex.getPriceList().getEliteSeat());
						}
						else if(SS == SeatStatus.an) {
							System.out.println("Seat Type: Normal Seat");
						}
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
			Ticketarray[a] = new Tickets(MT, TT, cinema.getMovie(mc).getBlockBuster(), cinema.getMovie(mc).getSneakpreview(), TC, SS);
		}
		double sum = Price.totalPrice(Ticketarray, noTick, cineplex.getPriceList());
		System.out.printf("Total Price = $%.2f\n", sum);
		cinema.getListOfMovie().get(mc).addSales(sum);
		double payment = 0;
			while(!Price.checkPaid()) {
				System.out.printf("Total paid: $%.2f\n", payment);
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

	/**
	 * Method to setect a specific movie and theatre and showtimes
	 * @param cinema to get the specific theatre and timeslot of a movie 
	 * @return movie choice and theatre id in an array
	 */
	private static int []selectMovieAndTheatre(Cinema cinema){
		int numOfMovies = cinema.getListOfMovie().size();
		int numOfTheaters = cinema.getListOfTheatre().size();
		int[] movieAvail = new int[numOfMovies];
		int[] theatreAvail = new int[numOfTheaters];
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Movie: ");
	    int [] showAvail = listofMoviesNowShowingandPreview(cinema);
		movieAvail = listofMoviesWithShowtimes(cinema, showAvail);
		int mc;
		int availcount = 0;
		for(int i=0; i<movieAvail.length; i++){
			if(movieAvail[i]==1){
				availcount++;
			}
		}
		if(availcount==0){
			return new int[] {-1,-1};
		}
		while(true){
			mc = sc.nextInt(); //selected the movie
			if(movieAvail[mc-1]==1){
				break;
			}else{
				System.out.println("Movie has no TimeSlots. Enter another movie.");
			}

		}
		mc = showAvail[mc-1];
		System.out.println();
		//check which theatre has the movie //
		System.out.println("Select Theatre ID: ");
		theatreAvail = listofAvaliableTheater(cinema, mc);
		int id;
		availcount = 0;
		for(int i=0; i<theatreAvail.length; i++){
			if(theatreAvail[i]>0){
				availcount++;
			}
		}
		if(availcount==0){
			return new int[] {-1,-1};
		}
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

	/**
	 * Creates an array that indicates if theatre has specific movieshowtimes is available or not
	 * If an array element is 0, indicates that the theater does not show the specfic movie
	 * @param cinema to get the specific theatre and timeslot of a movie 
	 * @param movieChoice to get the specific movie
	 * @return an array indicating if the theatre is showing the movie or not
	 */
	private static int[] listofAvaliableTheater(Cinema cinema, int movieChoice){
		int numOfTheaters = cinema.getListOfTheatre().size();
		int[] availArr = new int[numOfTheaters];
		
		for(int t =0; t<numOfTheaters; t++) {
			int timeslotCount = cinema.getListOfTheatre().get(t).getTimeslot().size();
			if(timeslotCount>0){
				System.out.println((t+1)+". Theatre:"+ cinema.getListOfTheatre().get(t).getTheatreID());
				for(int tslot=0; tslot<timeslotCount; tslot++) {
					if(Objects.equals(cinema.getListOfTheatre().get(t).getTimeslot().get(tslot).getMovie().getMovieTitle(), cinema.getMovie(movieChoice).getMovieTitle())) {
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

	/**
	 * Method to check username entered during user signup is unique
	 * Searches through the entire list of exisitng username and compares
	 * Promts user to enter vaid username
	 * @param cineplex to get usernames of other users for comaparison
	 * @return a valid username string
	 */
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

	/**
	 * Method to check if entered phone number is valid 
	 * Checks if number has 8 digits and starts with 8 or a 9
	 * Prompts user to enter vaid phone number
	 * @return a valid phone number string
	 */
	public static String enterNumber(){
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
		return String.valueOf(number);
	}

	/**
	 * Method to check if entered user email is valid 
	 * Checks if email has characters '@' and '.com'
	 * Promts uset to enter valid email address
	 * @return a valid email address string
	 */
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

	/**
	 * Creates an array that indicates if a movie is now showing or in preview
	 * If an array element contains the actual movie ID
	 * @param cinema to get the movies
	 * @return an array indicating if a movie is showing or in preview
	 */
	private static int[] listofMoviesNowShowingandPreview(Cinema cinema){
		int countofNowShowingorPreviewMovies = 0;
		for(int i=0; i<cinema.getListOfMovie().size(); i++){
			if(("Now Showing".equals(cinema.getMovie(i).getShowStatus().getStatus())) || ("Preview".equals(cinema.getMovie(i).getShowStatus().getStatus()))){
				countofNowShowingorPreviewMovies++;
			}
		}
		int[] showArr = new int[countofNowShowingorPreviewMovies];
		int i = 0;
		for(int j=0; j<cinema.getListOfMovie().size();j++){
			if(("Now Showing".equals(cinema.getMovie(j).getShowStatus().getStatus())) || ("Preview".equals(cinema.getMovie(i).getShowStatus().getStatus()))){
				showArr[i] = j;
				i++;
			}
		}

		return showArr;
	}

	/**
	 * Creates an array that indicates if a movie is showing or not
	 * If an array element is 0, indicates that the movie has no showtimes
	 * @param cinema to get the movies
	 * @return an array indicating if a movie is showing or not
	 */
	private static int[] listofMoviesWithShowtimes(Cinema cinema, int[] showArr){
		int[] availArr = new int[showArr.length];
		for(int i= 0; i<showArr.length; i++){
			int timeslotCount = 0;
			for(int t=0; t<cinema.getListOfTheatre().size(); t++){
				for(int tslot=0; tslot<cinema.getListOfTheatre().get(t).getTimeslot().size(); tslot++) {
					if(Objects.equals(cinema.getListOfTheatre().get(t).getTimeslot().get(tslot).getMovie().getMovieTitle(), cinema.getMovie(showArr[i]).getMovieTitle())) {
						timeslotCount++;					
					}
				}
			}
			if (timeslotCount>0){
				System.out.println(" " + (i+1)+ ". " + cinema.getMovie(showArr[i]).getMovieTitle());
				availArr[i] = 1;
			}else{
				System.out.println(" " + (i+1)+ ". " + cinema.getMovie(showArr[i]).getMovieTitle() + "   <Time Slots unavailable for this movie>");
				availArr[i] = 0;
			}
		}
		
		return availArr;
	}
	
	/**
	 * Prints the list of movies in a cinema
	 * @param cinema to get the movies
	 */
	private static void printListofMovies(Cinema cinema){
		for(int i=0; i<cinema.getListOfMovie().size();i++){
			System.out.println(" " + (i+1)+ ". " + cinema.getMovie(i).getMovieTitle());
		}
		System.out.println();
	}
	
	/**
	 * Prints the list of top 5 movies in a cinema based on user ratings 
	 * @param cinema to get the movies
	 */
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
	
	/**
	 * Method for moviegoer to write review on a specific movie
	 * One review per user 
	 * @param cinema to get the movies
	 * @param movieChoice to select a specific movie
	 * @param name of the user to write the review
	 * @returns cinema holding movie review and ratings by a user
	 */
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

	/**
	 * Prints past review of a specific movie
	 * Printing will include overall ratings and individal ratings and review by users
	 * @param cinema to get the movies
	 * @param movieChoice to select a specific movie
	 */
	private static void printPastReviews(Cinema cinema, int movieChoice){
		if(cinema.getMovie(movieChoice-1).getNamesOfPastReviewers().size()<2){
			System.out.println("Overall Rating: NA");
		}else{
			System.out.println("Overall Rating: " + cinema.getMovie(movieChoice-1).getOverallRating() + "* ");
		}
		System.out.println("-------------------------------------------------------------");
		for(int i = 0; i<cinema.getMovie(movieChoice-1).getNamesOfPastReviewers().size();i++){
			System.out.println(cinema.getMovie(movieChoice-1).getNameofReviewer(i));
			System.out.println(cinema.getMovie(movieChoice-1).getReview(i).getRating() + "*");
			System.out.println(cinema.getMovie(movieChoice-1).getReview(i).getReview());
			System.out.println("-------------------------------------------------------------");
		}
	}

	/**
	 * Prints details of a specific movie
	 * Printing will include secified movie's title, status, synopsis, director, casts, blockbuster, sneak preivew
	 * @param cinema to get the movies
	 * @param movieChoice to select a specific movie
	 */
	private static void printDetailsofMovie(Cinema cinema, int movieChoice){
		System.out.println("*************************");
		System.out.println(cinema.getMovie(movieChoice-1).getMovieTitle() + ": ");
		System.out.println("  Show Status: " + cinema.getMovie(movieChoice-1).getShowStatus().getStatus());
		System.out.println("  Synopsis: " + cinema.getMovie(movieChoice-1).getSynopsis());
		System.out.println("  Director: " + cinema.getMovie(movieChoice-1).getDirector());
		System.out.println("  Cast: ");
		for(int i=0; i<cinema.getMovie(movieChoice-1).getCast().size();i++){
			System.out.println("    "+ cinema.getMovie(movieChoice-1).getCast().get(i));
		}
		if(cinema.getMovie(movieChoice-1).getBlockBuster()){
			System.out.println("  BlockBuster: Yes" );
		}else{ System.out.println("  BlockBuster: No" ); }
		if(cinema.getMovie(movieChoice-1).getSneakpreview()){
			System.out.println("  Sneak Preview: Yes");
		}else{System.out.println("  Sneak Preview: No" );}
		printPastReviews(cinema, movieChoice);
		System.out.println("*************************");
	}
	
	/**
	 * Prints user interface used when users are selecting their choice
	 */
	public static void printUIChoice(){
		System.out.println("==============================");
		System.out.println("1. Search/List Movies");
		System.out.println("2. View Movie Details");
		System.out.println("3. Check Seat Availabliltiy");
		System.out.println("4. Book and purchase tickets");
		System.out.println("5. View Booking History");
		System.out.println("6. List top 5");
		System.out.println("7. Review Movie");
		System.out.println("8. Exit");
		System.out.println("==============================");
	}
	
	
}
