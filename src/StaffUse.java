import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class StaffUse {
	public static Cineplex StaffChoice(Cineplex cineplex) throws ParseException {
		staffLogin();
		cineplex = chooseCinema(cineplex);
		return cineplex;
	}
	
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
	
	private static Cineplex chooseCinema(Cineplex cineplex) throws ParseException{
		Scanner sc = new Scanner(System.in);
		while(true) {
		int choice = Integer.MAX_VALUE;
		System.out.println("Select Cinema");
		System.out.println("=================");
		for(int i=0; i<cineplex.getListOfCinema().size(); i++) {
			System.out.print((i+1) + ". Cinema ID " + cineplex.getCinema(i).getCinemaID());
			if(cineplex.getCinema(i).getCinemaName() != "") {
				System.out.print(", " + cineplex.getCinema(i).getCinemaName());
			}
			System.out.println();
		}
		System.out.println((cineplex.getListOfCinema().size()+1) + ". Logout");
		while(choice > (cineplex.getListOfCinema().size()+1) || choice <= 0) {
			if(choice != Integer.MAX_VALUE) {
				System.out.println("Invalid choice, please try again.");
			}
			choice = sc.nextInt();
		}
		if(choice == cineplex.getListOfCinema().size()+1) {
			System.out.println("Logging out...");
			return cineplex;
		}
		Cinema cinema = staffOptions(cineplex.getCinema(choice-1));
		cineplex.setCinema(cinema.getCinemaID(), cinema);
		}
	}
	
	private static Cinema staffOptions(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Movie Listing\n2. Cinema Showtimes\n3. Current Top 5 Ranking Movies\n4. Configure System Settings\n5. Return");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cinema = movieListing(cinema);
				break;
			case 2:
				cinema = cinemaShowtimes(cinema);
				break;
			case 3:
				cinema = displayTopGrossing(cinema);
				break;
			case 4:
				cinema = staffConfig(cinema);
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

	private static Cinema movieListing(Cinema cinema){ //Movie listing is a list of movies showing now and coming soon
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Create Movie Listing\n2. Display Movie Listing\n3. Edit Movie Listing\n4. Delete Movie Listing\n5. Return");
			choice = sc.nextInt();
			sc.nextLine();	//Scanner buffer not cleared i dk how to get ard it except reading it agn
			switch(choice) {
			case 1:
				cinema = createMovieListing(cinema);
				break;
			case 2:
				displayMovie(cinema);
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
	
	private static Cinema createMovieListing(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		boolean BlockBuster, Sneakpreview;
		System.out.println("Enter movie title: ");
		String movieTitle = sc.nextLine();
		System.out.println("Enter show status:\n1. Coming Soon\n2. Preview\n3. Now Showing");
		int showStatus = sc.nextInt(); //Scanner buffer not cleared i dk how to get ard it except reading it agn
		sc.nextLine();
		while(showStatus > 3 || showStatus <1) {
			System.out.println("Error Invalid input!\nPlease select the following options only: ");
			System.out.println("Enter show status:\n1. Coming Soon\n2. Preview\n3. Now Showing");
			showStatus = sc.nextInt(); 
			sc.nextLine();
		}
		System.out.println("Enter movie rating:\n1. PG\n2. PG-13\n3. R\n4. NC-17\n5. G");
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
		System.out.println("Sneak Preview (Y/N)?");
		ans = sc.next();
		if(ans.toLowerCase().matches("y")) {
			Sneakpreview = true;
		}
		else {
			Sneakpreview = false;
		}
		Movie movie = new Movie(movieTitle, showStatus, movieRating,synopsis, director, BlockBuster, Sneakpreview);
		System.out.println("Enter number of cast: ");
		int numOfCast = sc.nextInt();
		sc.nextLine(); //Scanner buffer not cleared i dk how to get ard it except reading it agn
		for(int i=0; i<numOfCast; i++) {
			System.out.println("Enter name of cast " + (i+1) + ": ");
			String castname = sc.nextLine();
			movie.addCast(castname);
		}
		cinema.addMovie(movie);
		return cinema;
	}
	
	private static void displayShowtimes(Cinema cinema) {
		ArrayList<String> order = new ArrayList<String>();
		ArrayList<String> movie = new ArrayList<String>();
		ArrayList<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
		for(int i=0; i<cinema.getListOfTheatre().size(); i++) {
			timeSlot.addAll(cinema.getTheatre(i).getTimeslot());
		}
		for(int i=0; i<timeSlot.size(); i++) {
			int movieIndex = order.indexOf(timeSlot.get(i).getMovie().getMovieTitle());
			if(movieIndex == -1) { 
				String movieTitle = timeSlot.get(i).getMovie().getMovieTitle();
				order.add(movieTitle);
				movie.add(movieTitle + ": " + timeSlot.get(i).getStartTime().getTime() + " - " + timeSlot.get(i).getEndTime().getTime());
			}
			else {
				String temp = movie.get(movieIndex);
				temp = temp + ", " + timeSlot.get(i).getStartTime().getTime() + " - " + timeSlot.get(i).getEndTime().getTime();
				movie.set(movieIndex, temp);
			}
		}
		System.out.println(String.join("\n", movie));
	}
	
	private static void showTheatreIndex(Cinema cinema) {
		System.out.println("Select Theatre index:");
		for(int i=0;i<cinema.getListOfTheatre().size();i++) {
			int ind = i+1;
			System.out.println(ind + ". Theatre " + cinema.getListOfTheatre().get(i).getTheatreID());
		}
	}

	private static void showTimeslotIndex(Theatre theatre) {
		for(int i=0; i<theatre.getTimeslot().size(); i++) {
			System.out.println((i+1) + ". " + theatre.getTimeslot().get(i).getMovie().getMovieTitle() + " " + 
					theatre.getTimeslot().get(i).getStartTime().getTime() + " - " + theatre.getTimeslot().get(i).getEndTime().getTime());
		}
	}
	
	private static GregorianCalendar enterTime() throws ParseException{
		Scanner sc = new Scanner(System.in).useDelimiter("\\n");
		boolean isValid = false;
		String timeString = "";
		while(!isValid) {
			timeString = sc.next();
			isValid = isValidTime(timeString);
		}
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		Date date = new SimpleDateFormat("dd-MM-yyyy HHmm").parse(timeString);
		cal.setTime(date);
		return cal;
	}
	
	private static boolean isValidTime(String dateStr) {
		try {
			new SimpleDateFormat("dd-MM-yyyy HHmm").parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
    }
	
	private static Cinema addShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		showTheatreIndex(cinema);
		int choiceTheatre = sc.nextInt() - 1;
		Theatre theatreSelect = cinema.getTheatre(choiceTheatre);
		int counter = 0;
		System.out.println("Choose Movie Title:");
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		for(int i=0; i<cinema.getListOfMovie().size(); i++) {
			if(cinema.getMovie(i).getShowStatus() == ShowStatus.ns) {
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
	
	private static Cinema editShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		showTheatreIndex(cinema);
		int theatreIndex = 0;
		while(theatreIndex <= 0 || theatreIndex > cinema.getListOfTheatre().size()){
			theatreIndex = sc.nextInt();
			if(theatreIndex > 0 && theatreIndex <= cinema.getListOfTheatre().size()) break;
			System.out.println("Invalid input please try again");
		} 
		Theatre theatreSelected = cinema.getTheatre(theatreIndex - 1);
		showTimeslotIndex(theatreSelected);
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
			timeslotSelected = changeMovie(timeslotSelected);
		theatreSelected.getTimeslot().add(timeslotSelected);
		cinema.replaceTheatre(theatreIndex, theatreSelected);
		return cinema;
	}
	
	private static TimeSlot changeTime(TimeSlot timeslot) throws ParseException {
		System.out.println("Start time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar start = enterTime();
		System.out.println("End time in dd-MM-yyyy HHmm (24hr):");
		GregorianCalendar end = enterTime();
		TimeSlot returnTimeslot = new TimeSlot(start, end, timeslot.getMovie());
		return returnTimeslot;
	}
	
	private static TimeSlot changeMovie(TimeSlot timeslot) {//whats this part for????
		Scanner sc = new Scanner(System.in);
		System.out.println("New movie Name: ");
		String newMovie = sc.nextLine();
		timeslot.getMovie().setMovieTitle(newMovie);
		return timeslot;
	}
	
	private static Cinema removeShowtimes(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		showTheatreIndex(cinema);
		Theatre theatreSelect = cinema.getTheatre(sc.nextInt()-1);
		System.out.println("Select movie slot index:");
		showTimeslotIndex(theatreSelect);
		int choiceMovieslot = sc.nextInt() - 1;
		theatreSelect.getTimeslot().remove(choiceMovieslot);
		System.out.println("Movie slot successfully removed");
		return cinema;
	}
	
	private static Cinema cinemaShowtimes(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		System.out.println("1. Display Showtimes\n2. Add Showtimes\n3. Edit Showtimes\n4. Remove Showtime\n5. Return");
		option = sc.nextInt();
		while(option < 1 || option > 5) {
			System.out.println("Invalid input. Please try again");
			option = sc.nextInt();
		}
		switch(option) {
		case 1:
			displayShowtimes(cinema);
			break;
		case 2:
			cinema = addShowtimes(cinema);
			break;
		case 3:
			cinema = editShowtimes(cinema);
			break;
		case 4:
			cinema = removeShowtimes(cinema);
			break;
		case 5:
			break;
		default:
			break;
		}
		return cinema;
	}
	
	private static void displayMovie(Cinema cinema) {
		System.out.println("Lists of Movies:");
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
			int ind = i+1;
			System.out.println(ind + ". " + cinema.getListOfMovie().get(i).getMovieTitle());
		}
	}
	
	private static int selectMovie(Cinema cinema) {
		int movieSelect = -1;
		Scanner sc = new Scanner(System.in);
		displayMovie(cinema);
		
		System.out.println("Select a movie to edit");
		movieSelect = sc.nextInt() - 1;
		while(movieSelect < 0 || movieSelect >= cinema.getListOfMovie().size()) {
			System.out.println("Please select an available movie");
			movieSelect = sc.nextInt() - 1;
		}
		return movieSelect;
	}
	
	private static Cinema editMovie(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int movieIndex = selectMovie(cinema);
		Movie movieChange = cinema.getMovie(movieIndex);
		
		int choice = 1;
		do {
			System.out.println("Select movie details to adjust");
			System.out.println("1. Movie Title\n2. Movie Status\n3. Movie Rating\n4. Synopsis\n5. Director\n6. Blockbuster Status\n7. Sneak Preview Status\n8. Cast\n9. Exit");
			choice = sc.nextInt();
			switch(choice) {
			case(1):
				System.out.println("Current Movie Title: "+movieChange.getMovieTitle());
				System.out.println("Enter new movie title:");
				String mT = sc.nextLine();
				String movieTitle = sc.nextLine();
				movieChange.setMovieTitle(movieTitle);
				break;
			case(2):
				System.out.println("Current Movie Status:");
				movieChange.getShowStatus();
				System.out.println("Enter new movie status:\n1. Coming Soon\n2. Preview\n3. Now Showing\n4. End Of Showing");
				int showStatus = sc.nextInt();
				movieChange.setShowStatus(showStatus);
				break;
			case(3):
				System.out.println("Current Movie Rating:");
				movieChange.getMovieRating();
				System.out.println("Enter movie rating:\n1. PG\n2. PG-13\n3. R\n4. NC-17\n5. G");
				int movieRating = sc.nextInt();
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
				if(ans.toLowerCase() == "y") {
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
				if(sneakStatus.toLowerCase() == "y") {
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
				System.out.println("Select on Option:");
				System.out.println("1. Delete Cast Member\n2. Add Cast Member");
				int castChoice = sc.nextInt();
				if(castChoice == 1) {
					System.out.println("Enter cast member to delete:");
					String castDel = sc.nextLine();
					movieChange.removeCast(castDel);
				} else {
					System.out.println("Enter new cast member name:");
					String castAdd = sc.nextLine();
					movieChange.addCast(castAdd);
				}
				break;
			case(9):
				//stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				choice = 1;
				break;
			}
		}while(choice != 9);
		cinema.replaceMovie(movieIndex, movieChange);
		return cinema;
	}
	
	private static Cinema deleteMovie(Cinema cinema) { //not working
		Scanner sc = new Scanner(System.in);
		int movieIndex = selectMovie(cinema);
		Movie movieChange = cinema.getMovie(movieIndex);

		movieChange.setShowStatus(4);
		cinema.replaceMovie(movieIndex, movieChange);
		//TODO: check all theatre in cinema and remove from timeslotarr all timeslot with same movie title 
		return cinema;
	}
	
	private static Cinema staffConfig(Cinema cinema) {
		//adjust ticket prices
		//holidays?
		return cinema;
	}
	
	private static Cinema displayTopGrossing(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Display Top 5 by Ticket Sales\n2. Display Top 5 by Ratings");
		int choice = sc.nextInt();
		do {
			switch(choice) {
			case(1):
				double[][] movieSales = new double[2][cinema.getListOfMovie().size()];
				for(int i=0;i<cinema.getListOfMovie().size();i++) {
					movieSales[0][i] = (double)i;
					movieSales[1][i] = cinema.getListOfMovie().get(i).getSales();
				}
				for(int i=1;i<cinema.getListOfMovie().size();i++) {
					for(int k=i;k>0;k--) {
						if(movieSales[1][k] > movieSales[1][k-1]) {
							double tempMov, tempSale;
							tempMov = movieSales[0][k];
							tempSale = movieSales[1][k];
							movieSales[0][k] = movieSales[0][k-1];
							movieSales[1][k] = movieSales[1][k-1];
							movieSales[0][k-1] = tempMov;
							movieSales[1][k-1] = tempSale;
						} else {
							break;
						}
					}
				}
				System.out.println("Top 5 Movies by Sales:");
				for(int j=0;j<cinema.getListOfMovie().size();j++) {
					int movieIndex = (int)movieSales[0][j];
					double sales = movieSales[1][j];
					int ind = j+1;
					System.out.println(ind + ". "+cinema.getMovie(movieIndex)+"  $"+sales);
					if(j==4) {
						break;
					}
				}
				break;
			case(2):
				float[][] movieRatings = new float[2][cinema.getListOfMovie().size()];
				for(int i=0;i<cinema.getListOfMovie().size();i++) {
				movieRatings[0][i] = (float)i;
				movieRatings[1][i] = cinema.getListOfMovie().get(i).getOverallRating();
				}
				for(int i=1;i<cinema.getListOfMovie().size();i++) {
					for(int k=i;k>0;k--) {
						if(movieRatings[1][k] > movieRatings[1][k-1]) {
							float tempMov, tempRate;
							tempMov = movieRatings[0][k];
							tempRate = movieRatings[1][k];
							movieRatings[0][k] = movieRatings[0][k-1];
							movieRatings[1][k] = movieRatings[1][k-1];
							movieRatings[0][k-1] = tempMov;
							movieRatings[1][k-1] = tempRate;
						} else {
							break;
						}
					}
				}
				System.out.println("Top 5 Movies by Ratings:");
				for(int j=0;j<cinema.getListOfMovie().size();j++) {
					int movieIndex = (int)movieRatings[0][j];
					float rating = movieRatings[1][j];
					int ind = j+1;
					System.out.println(ind + ". "+cinema.getMovie(movieIndex)+"  "+rating);
					if(j==4) {
						break;
					}
				}
				break;
			default:
				System.out.println("Invalid input. Please try again");
				choice = sc.nextInt();
			}
		} while(choice < 1 || choice > 2);
		return cinema;
	}
}
