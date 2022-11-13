import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * A boundary class used by Staff as an interface
 * between the User and controller class
 *
 */
public class StaffUseBoundary {
	
	/**
	 * Calls the staff login for authentication
	 * and choose cinema function to update the current cineplex
	 * @param cineplex The specified cineplex to be updated
	 * @return An updated cineplex containing any updates done during the choose cinema function
	 * @throws ParseException If the format used in cinema show time and staff config function within staff options, within choose cinema was correct
	 */
	public static Cineplex StaffChoice(Cineplex cineplex) throws ParseException {
		staffLogin();
		cineplex = chooseCinema(cineplex);
		System.out.println("=================");
		return cineplex;
	}
	
	/**
	 * Authenticates the staff by checking the username and password of user
	 */
	private static void staffLogin(){
		Scanner sc = new Scanner(System.in);
		String username = null;
		do {
			if(username != null)
				System.out.println("Username does not exist");
			System.out.print("Username: ");
			username = sc.next();
			if(username.toLowerCase().contains("staff")) {
				String password = "password";
				do {
					if(password != "password")
						System.out.println("Incorrect password");
					System.out.print("Password: ");
					password = sc.next();
				}while(!password.equals("password"));
			}
		} while(!username.toLowerCase().contains("staff"));
	}
	
	/**
	 * Specifies the cinema to be used and calls the staff options functions to edit the cinema
	 * @param cineplex The cineplex to retrieve cinema from
	 * @return An updated cineplex containing updates from the staff options function
	 * @throws ParseException If the format used in cinema show time and staff config function within the staff options was correct
	 */
	private static Cineplex chooseCinema(Cineplex cineplex) throws ParseException{
		Scanner sc = new Scanner(System.in);
		while(true) {
			int choice = Integer.MAX_VALUE;
			System.out.println("=================");
			System.out.println("Select Cinema/option");
			for(int i=0; i<cineplex.getListOfCinema().size(); i++) {
				System.out.print((i+1) + ". Cinema ID " + cineplex.getCinema(i).getCinemaID());
				if(cineplex.getCinema(i).getCinemaName() != "") {
					System.out.print(", " + cineplex.getCinema(i).getCinemaName());
				}
				System.out.println();
			}
			System.out.println((cineplex.getListOfCinema().size()+1) + ". System Configuration ");
			System.out.println((cineplex.getListOfCinema().size()+2) + ". Logout");
			while(choice > (cineplex.getListOfCinema().size()+2) || choice <= 0) {
				if(choice != Integer.MAX_VALUE) {
					System.out.println("Invalid choice, please try again.");
				}
				choice = sc.nextInt();
			}
			if(choice == cineplex.getListOfCinema().size()+1) {
				cineplex = staffConfigCineplex(cineplex);
				return cineplex;
			}
			if(choice == cineplex.getListOfCinema().size()+2) {
				System.out.println("Logging out...");
				return cineplex;
			}
			Cinema cinema = staffOptions(cineplex.getCinema(choice-1), cineplex);
			cineplex.setCinema(cinema.getCinemaID(), cinema);
		}
	}
	
	/**
	 * Gives the staff the option to display and adjust movies, showtimes, rankings and system settings
	 * @param cinema The specified cinema to adjust
	 * @param cineplex The current cineplex
	 * @return An updated cinema containing updated movies, showtimes, rankings and system settings
	 * @throws ParseException If the format used in the cinema showtimes and staff confic function was correct
	 */
	private static Cinema staffOptions(Cinema cinema, Cineplex cineplex) throws ParseException {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("=================");
			System.out.println("1. Movie Listing\n2. Cinema Showtimes\n3. Current Top 5 Ranking Movies\n4. Edit Theatre Type\n5. Return");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cinema = movieListing(cinema);
				break;
			case 2:
				cinema = cinemaShowtimes(cinema);
				break;
			case 3:
				displayTopGrossing(cinema);
				break;
			case 4:
				cinema = EditTheatreType(cinema);
				break;
			case 5:
				//stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				break;
			}
		}while(choice>5);
		return cinema;
	}

	/**
	 * Gives the staff the option to create, display, edit and delete movies for a specific cinema
	 * @param cinema The specified cinema to retrieve movies from
	 * @return An updated cinema containing updated movies
	 */
	private static Cinema movieListing(Cinema cinema){
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("=================");
			System.out.println("1. Create Movie Listing\n2. Display Movie Listing\n3. Edit Movie Listing\n4. Delete Movie Listing\n5. Return");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				cinema = createMovieListing(cinema);
				break;
			case 2:
				System.out.println("=================");
				System.out.println(StaffUseController.getMovies(cinema));
				break;
			case 3:
				cinema = editMovie(cinema);
				break;
			case 4:
				cinema = deleteMovie(cinema);
				break;
			case 5:
				//Stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				break;
			}
		}while(choice>5);
		return cinema;
	}
	
	/**
	 * Creates a new movie to be added to a cinema
	 * @param cinema The specified cinema to add a new movie
	 * @return An updated cinema containing the new movie
	 */
	private static Cinema createMovieListing(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		boolean BlockBuster, Sneakpreview;
		MovieType Movietype;
		System.out.println("=================");
		System.out.println("Enter movie title: ");
		String movieTitle = sc.nextLine();
		System.out.println("Enter show status:\n1. Coming Soon\n2. Preview\n3. Now Showing");
		int showStatus = sc.nextInt();
		sc.nextLine();
		while(showStatus > 3 || showStatus <1) {
			System.out.println("Error Invalid input!\nPlease select the following options only: ");
			System.out.println("Enter show status:\n1. Coming Soon\n2. Preview\n3. Now Showing");
			showStatus = sc.nextInt(); 
			sc.nextLine();
		}
		System.out.println("Enter movie rating:\n1. PG\n2. PG13\n3. R21\n4. NC16\n5. G");
		int movieRating = sc.nextInt();
		sc.nextLine();
		while(movieRating>5 || movieRating<1) {
			System.out.println("Error Invalid input!\nPlease select the following options only: ");
			System.out.println("Enter movie rating:\n1. PG\n2. PG-13\n3. R\n4. NC-17\n5. G");
			movieRating = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Enter Synopsis: ");
		String synopsis = sc.nextLine();
		System.out.println("Enter name of director: ");
		String director = sc.nextLine();
		System.out.println("BlockBuster movie(Y/N)?");
		String ans = sc.next();
		if(ans.toLowerCase().matches("y")) {
			BlockBuster = true;
		}
		else {
			BlockBuster = false;
		}
		System.out.println("MovieType (R/3D)?");
		ans = sc.next();
		if(ans.toLowerCase().matches("r")) {
			Movietype = MovieType.RD;
		}
		else {
			Movietype = MovieType.TD;
		}
		System.out.println("Enter number of cast: ");
		int numOfCast = sc.nextInt();
		sc.nextLine();
		Movie movie = new Movie(movieTitle, showStatus, movieRating,synopsis, director, BlockBuster, Movietype);
		for(int i=0; i<numOfCast; i++) {
			System.out.println("Enter name of cast " + (i+1) + ": ");
			String castname = sc.nextLine();
			movie.addCast(castname);
		}
		cinema.addMovie(movie);
		return cinema;
	}

	/**
	 * Inputs and returns a date object after checking the dates time format
	 * @return A date object
	 * @throws ParseException If the input date is of the wrong format
	 */
	private static GregorianCalendar enterTime() throws ParseException{
		Scanner sc = new Scanner(System.in).useDelimiter("\\n");
		boolean isValid = false;
		String timeString = "";
		while(!isValid) {
			timeString = sc.next();
			isValid = StaffUseController.isValidTime(timeString);
		}
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		Date date = new SimpleDateFormat("dd-MM-yyyy HHmm").parse(timeString);
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * Create a new show time for the current cinema
	 * @param cinema The specified cinema to add the new show time
	 * @return An updated cinema containing the new show time
	 * @throws ParseException If the start and end time are of the correct format
	 */
	private static Cinema addShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println(StaffUseController.getTheatreIndex(cinema));
		int choiceTheatre = sc.nextInt() - 1;
		Theatre theatreSelect = cinema.getTheatre(choiceTheatre);
		int counter = 0;
		System.out.println("Choose Movie Title:");
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		for(int i=0; i<cinema.getListOfMovie().size(); i++) {
			if(cinema.getMovie(i).getShowStatus() == ShowStatus.ns || cinema.getMovie(i).getShowStatus() == ShowStatus.pr) {
				System.out.println(++counter + ". " + cinema.getMovie(i).getMovieTitle());
				tempMovieList.add(cinema.getMovie(i));
			}
		}
		Movie movie = tempMovieList.get(sc.nextInt() - 1);
		
		System.out.println("Start time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar start = enterTime();
		System.out.println("End time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar end = enterTime();
		
		TimeSlot timeslot = new TimeSlot(start, end, movie);
		boolean result = false;
		result = theatreSelect.addTimeslot(timeslot);
		if(result == true) {
			System.out.println("Movie slot added successfully");
		} else {
			System.out.println("Error, movie slot conflicts with existing movie slots");
		}
		return cinema;
	}
	
	/**
	 * Edit the show time by changing the start, end times or movies shown during the show time
	 * @param cinema The specified cinema to retrieve show time from
	 * @return An updated cinema containing the the updated show time
	 * @throws ParseException If the start and end time is of the correct format
	 */
	private static Cinema editShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println(StaffUseController.getTheatreIndex(cinema));
		int theatreIndex = 0;
		while(theatreIndex <= 0 || theatreIndex > cinema.getListOfTheatre().size()){
			theatreIndex = sc.nextInt();
			if(theatreIndex > 0 && theatreIndex <= cinema.getListOfTheatre().size()) break;
			System.out.println("Invalid input please try again");
		} 
		Theatre theatreSelected = cinema.getTheatre(theatreIndex - 1);
		System.out.print(StaffUseController.getTimeslotIndex(theatreSelected));   
		int showtimesSelected = 0;
		while(showtimesSelected <= 0 || showtimesSelected > theatreSelected.getTimeslot().size()) {
			showtimesSelected = sc.nextInt();
			if(showtimesSelected > 0 && showtimesSelected <= theatreSelected.getTimeslot().size()) break;
			System.out.println("Invalid input please try again");
		}
		TimeSlot timeslotSelected = theatreSelected.getTimeslot().get(showtimesSelected - 1);
		theatreSelected.getTimeslot().remove(timeslotSelected);
		int choice = 0;
		System.out.println("1. Change start/ end time");
		System.out.println("2. Change movie");
		while(choice <= 0 || choice > 2) {
			choice = sc.nextInt();
			if(choice > 0 && choice <= 2) break;
			System.out.println("Invalid input please try again");
		}
		if(choice == 1)
			timeslotSelected = changeTime(timeslotSelected);
		else if(choice == 2)
			timeslotSelected = changeMovie(cinema, timeslotSelected);
		theatreSelected.getTimeslot().add(timeslotSelected);
		return cinema;
	}
	
	/**
	 * Adjust the start and end time of a specific timeslot
	 * @param timeslot The specified timeslot to change
	 * @return A new timeslot containing the new start and end times
	 * @throws ParseException If the start and end times are of the correct format
	 */
	private static TimeSlot changeTime(TimeSlot timeslot) throws ParseException {
		System.out.println("Start time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar start = enterTime();
		System.out.println("End time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar end = enterTime();
		TimeSlot returnTimeslot = new TimeSlot(start, end, timeslot.getMovie());
		return returnTimeslot;
	}
	
	/**
	 * Change the movie in a specific timeslot
	 * @param cinema The specified cinema to retrieve movies from
	 * @param timeslot The timeslot to adjust
	 * @return The updated timeslot containing the new movie
	 */
	private static TimeSlot changeMovie(Cinema cinema, TimeSlot timeslot) {
		Scanner sc = new Scanner(System.in);
		int counter = 0;
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		for(int i=0; i<cinema.getListOfMovie().size(); i++) {
			if(cinema.getMovie(i).getShowStatus() == ShowStatus.ns) {
				System.out.println(++counter + ". " + cinema.getMovie(i).getMovieTitle());
				tempMovieList.add(cinema.getMovie(i));
			}
		}
		Movie movie = tempMovieList.get(sc.nextInt() - 1);
		TimeSlot returnTimeslot = new TimeSlot(timeslot.getStartTime(), timeslot.getEndTime(), movie);
		return returnTimeslot;
	}
	
	/**
	 * Remove a specific show time from cinema
	 * @param cinema The specified cinema to retrieve a show time frome
	 * @return An updated cinema without the specific show time which was removed
	 */
	private static Cinema removeShowtimes(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println(StaffUseController.getTheatreIndex(cinema));
		Theatre theatreSelect = cinema.getTheatre(sc.nextInt()-1);
		System.out.println("Select movie slot index:");
		System.out.print(StaffUseController.getTimeslotIndex(theatreSelect));   
		int choiceMovieslot = sc.nextInt() - 1;
		theatreSelect.getTimeslot().remove(choiceMovieslot);
		System.out.println("Movie slot successfully removed");
		return cinema;
	}
	
	/**
	 * Gives the staff the option to display, add, edit or remove show times from a specific cinema
	 * @param cinema The specifiec cinema to retrieve, add or delete show time
	 * @return An updated cinema containing the updated list of show times
	 * @throws ParseException If the start and end time within addshowtime and editshowtime function are of the correct format
	 */
	private static Cinema cinemaShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		System.out.println("=================");
		System.out.println("1. Display Showtimes\n2. Add Showtimes\n3. Edit Showtimes\n4. Remove Showtime\n5. Return");
		option = sc.nextInt();
		while(option < 1 || option > 5) {
			System.out.println("Invalid input. Please try again");
			option = sc.nextInt();
		}
		switch(option) {
		case 1:
			System.out.println(StaffUseController.getShowtimes(cinema));
			break;
		case 2:
			System.out.println("=================");
			cinema = addShowtimes(cinema);
			break;
		case 3:
			System.out.println("=================");
			cinema = editShowtimes(cinema);
			break;
		case 4:
			System.out.println("=================");
			cinema = removeShowtimes(cinema);
			break;
		case 5:
			break;
		default:
			break;
		}
		return cinema;
	}
	
	/**
	 * Selects a movie from a specific cinema
	 * @param cinema The specified cinema to retrieve movies from
	 * @return The unique movie index
	 */
	private static int selectMovie(Cinema cinema) {
		int movieSelect = -1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a movie to edit");
		System.out.println(StaffUseController.getMovies(cinema));
		movieSelect = sc.nextInt() - 1;
		while(movieSelect < 0 || movieSelect >= cinema.getListOfMovie().size()) {
			System.out.println("Please select an available movie");
			movieSelect = sc.nextInt() - 1;
		}
		return movieSelect;
	}
	
	/**
	 * Edit a movies details
	 * @param cinema The specified cinema to retrieve the movie from
	 * @return An updated cinema containing the updated movie
	 */
	private static Cinema editMovie(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int movieIndex = selectMovie(cinema);
		Movie movieChange = cinema.getMovie(movieIndex);
		
		int choice = 1;
		do {
			System.out.println("=================");
			System.out.println("Select movie details to adjust");
			System.out.println("1. Movie Title\n2. Movie Status\n3. Movie Rating\n4. Synopsis\n5. Director\n6. Blockbuster Status\n7. Sneak Preview Status\n8. Cast\n9. Movie Type\n10. Exit");
			choice = sc.nextInt();
			switch(choice) {
			case(1):
				System.out.println("Current Movie Title: "+movieChange.getMovieTitle());
				System.out.println("Enter new movie title:");
				sc.nextLine();
				String movieTitle = sc.nextLine();
				movieChange.setMovieTitle(movieTitle);
				break;
			case(2):
				System.out.println("Current Movie Status:");
				System.out.println(movieChange.getShowStatus().getStatus());
				System.out.println("Enter new movie status:\n1. Coming Soon\n2. Preview\n3. Now Showing\n4. End Of Showing\n5. Return");
				int showStatus = sc.nextInt();
				while(showStatus<1||showStatus>5) {
					System.out.println("Invalid input:\nEnter new movie status:\n1. Coming Soon\n2. Preview\n3. Now Showing\n4. End Of Showing\n5. Return");
					showStatus = sc.nextInt();
				}
				if(showStatus == 5) {
					break;
				}
				movieChange.setShowStatus(showStatus);
				break;
			case(3):
				System.out.println("Current Movie Rating:");
				movieChange.getMovieRating();
				System.out.println("Enter movie rating:\n1. PG\n2. PG13\n3. R21\n4. NC16\n5. G\n6. Return");
				int movieRating = sc.nextInt();
				while( movieRating<1|| movieRating>6) {
					System.out.println("Invalid input:\nEnter movie rating:\n1. PG\n2. PG-13\n3. R\n4. NC-17\n5. G\n6. Return");
					movieRating = sc.nextInt();
				}
				if(movieRating == 6) {
					break;
				}
				movieChange.setMovieRating(movieRating);
				break;
			case(4):
				System.out.println("Current Synopsis: "+movieChange.getSynopsis());
				System.out.println("Enter new synopsis:");
				String synopsis = sc.nextLine();
				movieChange.setSynopsis(synopsis);
				break;
			case(5):
				System.out.println("Current Director: "+movieChange.getDirector());
				System.out.println("Enter new director:");
				String director = sc.nextLine();
				movieChange.setDirector(director);
				break;
			case(6):
				String block;
				boolean buster;
				if(movieChange.getBlockBuster()==true) {
					block = "Yes";
				} else {
					block = "No";
				}
				System.out.println("Current Blockbuster Status: "+block);
				System.out.println("Change blockbuster status (Y/N)?");
				String ans = sc.next();
				if(ans.toLowerCase().matches("y")) {
					buster = true;
				}
				else {
					buster = false;
				}
				movieChange.setBlockBuster(buster);
				break;
			case(7):
				String sneak;
				boolean previewStatus;
				if(movieChange.getSneakpreview()==true) {
					sneak = "Yes";
				} else {
					sneak = "No";
				}
				System.out.println("Current Sneak Preview Status: "+sneak);
				System.out.println("Change sneak preview status (Y/N)?");
				String sneakStatus = sc.next();
				if(sneakStatus.toLowerCase().matches("y")) {
					previewStatus = true;
				}
				else {
					previewStatus = false;
				}
				movieChange.setSneakPreview(previewStatus);
				break;
			case(8):
				System.out.println("Current Cast:");
				for(int i=0;i<movieChange.getCast().size();i++) {
					int ind = i+1;
					System.out.println(ind + ". "+movieChange.getCast().get(i));
				}
				System.out.println("Select an Option:");
				System.out.println("1. Delete Cast Member\n2. Add Cast Member\n3. Return");
				int castChoice = sc.nextInt();
				while (castChoice<1 || castChoice>3) {
					System.out.println("Invalid Option\n Please Select an Option:\n1. Delete Cast Member\\n2. Add Cast Member\\n 3.Return");
					castChoice = sc.nextInt();
				}
				if(castChoice == 1) {
					System.out.println("Enter cast member to delete:");
					String castDel = sc.nextLine();
					movieChange.removeCast(castDel);
				} else if(castChoice == 2){
					System.out.println("Enter new cast member name:");
					String castAdd = sc.nextLine();
					movieChange.addCast(castAdd);
				}
				break;
			case(9):
				System.out.println("Current Movie Type: " + movieChange.getMovieType());
				System.out.println("New Movie Type:\n1. Regular/Digital Movie\n2. 3D Movie\n3. Return");
				int MTchoice = sc.nextInt();
				while (MTchoice<1 || MTchoice>3) {
					System.out.println("Invalid Option\n Please Select an Option:\n1. Regular/Digital Movie\n2. 3D Movie\n3. Return");
					castChoice = sc.nextInt();
				}
				if(MTchoice == 1) {
					movieChange.setMovieType(MovieType.RD);
				}
				else if(MTchoice == 2) {
					movieChange.setMovieType(MovieType.TD);
				}
				break;
			case(10)://return
				break;
			default:
				System.out.println("Invalid input. Please try again");
				choice = 1;
				break;
			}
		}while(choice != 10);
		cinema.replaceMovie(movieIndex, movieChange);
		return cinema;
	}
	
	/**
	 * Delete a movie by calling the delete movie function from the staff controller class 
	 * @param cinema The specified cinema to retrieve movies from
	 * @return An updated cinema containing the updated timeslots and movie details
	 */
	private static Cinema deleteMovie(Cinema cinema) {
		System.out.println("=================");
		Scanner sc = new Scanner(System.in);
		int movieIndex = selectMovie(cinema);
		cinema = StaffUseController.deleteMovie(cinema, movieIndex);
		return cinema;
	}
	
	/**
	 * Gives the staff the option to edit theatre, price or list of holidays
	 * @param cinema The specified cinema to retrieve theatre from
	 * @param cineplex The current cineplex
	 * @return An updated cinema containing the updated theatre, price and holidays
	 * @throws ParseException If the holiday date within the add holiday function is of the correct format
	 */
	private static Cineplex staffConfigCineplex(Cineplex cineplex)throws ParseException{
		Scanner sc = new Scanner(System.in);
		PricingList priceList = cineplex.getPriceList();
		int choice;
		do {
			System.out.println("=================");
			System.out.println("Select an Option:\n1. Edit Price List\n2. Holiday List\n3. Return");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				int tixchoice, ifelse;
				do {
					System.out.println("=================");
					System.out.println("Choose Ticket Type: ");
					System.out.println("1. Senior\n2. Student\n3. Mon - Wed\n4. Thurs\n5. Friday(before 6pm)\n6. Friday(After 6pm)\n7. Sat & Sun\n8. Sneakpreview\n9. Blockbuster");
					System.out.println("10. Couple Seats\n11. Elite Seats\n12. Elite Theatre\n13. Return");
					tixchoice = sc.nextInt();
					switch (tixchoice) {
					case 1:
						System.out.println("Current Price: " + priceList.getSenior());
						System.out.println("New Price:");
						priceList.setSenior(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 2:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getStudent());
							System.out.println("New Price:");
							priceList.setStudent(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getStudent3D());
							System.out.println("New Price:");
							priceList.setStudent3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;
					case 3:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getMonWed());
							System.out.println("New Price:");
							priceList.setMonWed(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getMonWed3D());
							System.out.println("New Price:");
							priceList.setMonWed3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;
					case 4:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getThur());
							System.out.println("New Price:");
							priceList.setThur(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getThur3D());
							System.out.println("New Price:");
							priceList.setThur3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;	
					case 5:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getFriB());
							System.out.println("New Price:");
							priceList.setFriB(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getFriB3D());
							System.out.println("New Price:");
							priceList.setFriB3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;
					case 6:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getFriA());
							System.out.println("New Price:");
							priceList.setFriA(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getFriA3D());
							System.out.println("New Price:");
							priceList.setFriA3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;	
					case 7:
						System.out.println("1. Regular and Digital Movies\n2. 3D Movies");
						ifelse = sc.nextInt();
						if(ifelse == 1){
							System.out.println("Current Price: " + priceList.getSS());
							System.out.println("New Price:");
							priceList.setSS(sc.nextDouble());
						}
						else {
							System.out.println("Current Price: " + priceList.getSS3D());
							System.out.println("New Price:");
							priceList.setSS3D(sc.nextDouble());
						}
						System.out.println("Price Changed Successfully");
						break;
					case 8:
						System.out.println("Current Price: " + priceList.getSneakpreview());
						System.out.println("New Price:");
						priceList.setSneakpreview(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 9: 
						System.out.println("Current Additional Price: " + priceList.getBlockBusterPrice());
						System.out.println("New Price:");
						priceList.setBlockBusterPrice(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 10: //couple
						System.out.println("Current Additional Price: " + priceList.getCouplePrice());
						System.out.println("New Price:");
						priceList.setCouplePrice(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 11: //Elite Seats
						System.out.println("Current Additional Price: " + priceList.getEliteSeat());
						System.out.println("New Price:");
						priceList.setEliteSeat(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 12: //Elite Theatre
						System.out.println("Current Additional Price: " + priceList.getEliteTheatrePrice());
						System.out.println("New Price:");
						priceList.setEliteTheatrePrice(sc.nextDouble());
						System.out.println("Price Changed Successfully");
						break;
					case 13:
						break;
					default:
						System.out.println("Invalid input. Please try again.");
					}
				} while(tixchoice != 13);
				break;
			case 2:
				int holChoice=0;
				System.out.println("=================");
				System.out.println("1. Display Holiday List\n2. Add Holiday\n3. Delete Holiday\n4. Return");
				holChoice = sc.nextInt();
				while(holChoice>4 || holChoice<1) {
					System.out.println("Invalid input. Please try again");
					System.out.println("=================");
					System.out.println("1. Display Holiday List\n2. Add Holiday\n3. Delete Holiday\n4. Return");
					holChoice = sc.nextInt();
				}
				switch(holChoice) {
					case 1:
						System.out.println(StaffUseController.displayHolidayList(cineplex));
						break;
					case 2:
						System.out.println("Enter holiday date in dd-MM-yyyy");
						String holidayStr = sc.next();
						if(StaffUseController.isValidDate(holidayStr)) {
							StaffUseController.addHoliday(cineplex, holidayStr);
							System.out.println("Holiday date added.");
						}
						else
							System.out.println("Invalid date");
						break;
					case 3:
						if(cineplex.getHolidayList().size()==0) {
							System.out.println("No holidays to delete");
						} else {
							System.out.println(StaffUseController.displayHolidayList(cineplex));
							System.out.println("Select holiday to delete:");
							int holidayChoice = sc.nextInt();
							if(holidayChoice<0 || holidayChoice > cineplex.getHolidayList().size()) {
								System.out.println("Invalid input. Please try again");
							} else {
								cineplex = StaffUseController.delHoliday(cineplex, holidayChoice);
								System.out.println("Successfully removed");
							}
							
						}
						break;
					case 4:
						break;
					default:
						System.out.println("Invalid input. Please try again.");
					}
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid input. Please try again.");
			}
		} while(choice!=3);
		return cineplex;
	}
	
	
	private static Cinema EditTheatreType(Cinema cinema)throws ParseException{
		Scanner sc = new Scanner(System.in);
		System.out.println("=================");
		System.out.println(StaffUseController.getTheatreIndex(cinema));
		int theatre = sc.nextInt()-1;
		System.out.println("Current Theatre Type:");
		System.out.println(cinema.getListOfTheatre().get(theatre).getTheatreClass().getTheatreClass());
		System.out.println("Choose a Theatre Type:\n1. Platinum Movie Suites\n2. Elite Club");
		int suite = sc.nextInt();
		cinema.getListOfTheatre().get(theatre).setTheatreClass(suite);
		System.out.println("Theatre Type successfully updated");
		return cinema;
	}
	
	/**
	 * Displays the top 5 current movies by sale or overall ratings
	 * @param cinema The specified cinema to retrieve sales or ratings from
	 */
	private static void displayTopGrossing(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("=================");
		System.out.println("1. Display Top 5 by Ticket Sales\n2. Display Top 5 by Ratings");
		int choice = sc.nextInt();
		do {
			switch(choice) {
			case(1):
				System.out.println("=================");
				System.out.println(StaffUseController.displayTop5BySale(cinema));
				break;
			case(2):
				System.out.println("=================");
				System.out.println(StaffUseController.displayTop5ByRating(cinema));
				break;
			default:
				System.out.println("Invalid input. Please try again");
				choice = sc.nextInt();
			}
		} while(choice < 1 || choice > 2);
	}
	
}
