import java.util.Scanner;
import java.util.ArrayList;

public class StaffUse {
	public static Cineplex StaffChoice(Cineplex cineplex) {
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
	
	private static Cineplex chooseCinema(Cineplex cineplex){
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
		Cinema cinema = staffOptions(cineplex.getCinema(choice-1));
		cineplex.setCinema(cinema.getCinemaID(), cinema);
		return cineplex;
	}
	
	private static Cinema staffOptions(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Movie Listing\n2. Cinema Showtimes\n3. Current Top 5 Ranking Movies\n4. Configure System Settings");
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
				//Stop the program
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
			System.out.println("1. Create Movie Listing\n2. Display Movie Listing\n3. Edit Movie Listing\n4. Delete Movie Listing");
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
		int showStatus = sc.nextInt();
		sc.nextLine(); //Scanner buffer not cleared i dk how to get ard it except reading it agn
		System.out.println("Enter movie rating:\n1. PG\n2. PG-13\n3. R\n4. NC-17\n5. G");
		int movieRating = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Synopsis: ");
		String synopsis = sc.nextLine();
		System.out.println("Enter name of director: ");
		String director = sc.nextLine();
		System.out.println("BlockBuster movie(Y/N)?");
		String ans = sc.next();
		if(ans.toLowerCase() == "y") {
			BlockBuster = true;
		}
		else {
			BlockBuster = false;
		}
		System.out.println("Sneak Preview (Y/N)?");
		ans = sc.next();
		if(ans.toLowerCase() == "y") {
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
		System.out.println("Select Theatre index:");
		for(int i=0;i<cinema.getListOfTheatre().size();i++) {
			int ind = i+1;
			System.out.println(ind + ". " + cinema.getListOfTheatre().get(i));
		}
	}
	
	private static Cinema cinemaShowtimes(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		System.out.println("1. Display Showtimes\n2. Add Showtimes\n3. Edit Showtimes\n4. Remove Showtime");
		option = sc.nextInt();
		while(option < 1 || option > 4) {
			System.out.println("Invalid input. Please try again");
			option = sc.nextInt();
		}
		displayShowtimes(cinema);
		int choiceTheatre = sc.nextInt() - 1;
		Theatre theatreSelect = cinema.getListOfTheatre().get(choiceTheatre);
		System.out.println("Movie Showtimes:");
		for(int i=0;i<theatreSelect.getTimeslot().size();i++) {
			int ind = i+1;
			System.out.print(ind+".  "+theatreSelect.getTimeslot().get(i).getmovieTitle()+"  Start:"+theatreSelect.getTimeslot().get(i).getStartTime()+"  End:"+theatreSelect.getTimeslot().get(i).getEndTime());
		}
		if(option == 2) {
			System.out.println("Key in Movie Title:");
			String title = sc.nextLine();
			System.out.println("Start time (24Hrs):");
			int start = sc.nextInt();
			System.out.println("End time (24Hrs):");
			int end = sc.nextInt();
			TimeSlot timeslot = new TimeSlot(start, end, title);
			boolean result = false;
			result = theatreSelect.addTimeslot(timeslot);
			if(result == true) {
				System.out.println("Movie slot added successfully");
			} else {
				System.out.println("Error, movie slot conflicts with existing movie slots");
			}
		} else if (option == 3) {
			System.out.println("Select movie slot index:");
			int choiceMovieslot = sc.nextInt() - 1;
			theatreSelect.getTimeslot().remove(choiceMovieslot);
			System.out.println("Movie slot successfully removed");
		}
		return cinema;
	}
	
	private static void displayMovie(Cinema cinema) {
		System.out.println("Lists of Movies:");
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
			int ind = i+1;
			System.out.println(ind + ". " + cinema.getListOfMovie().get(i));
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
			System.out.println("1. Movie Title\n2. Movie Status\n3. Movie Rating\n4. Synopsis\n5. Director\n6. Blockbuster Status\n7. Sneak Preview Status\n 8. Cast\n9. Exit");
			choice = sc.nextInt();
			switch(choice) {
			case(1):
				System.out.println("Current Movie Title: "+movieChange.getMovieTitle());
				System.out.println("Enter new movie title:");
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
	
	private static Cinema deleteMovie(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int movieIndex = selectMovie(cinema);
		Movie movieChange = cinema.getMovie(movieIndex);

		movieChange.setShowStatus(4);
		cinema.replaceMovie(movieIndex, movieChange);
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
