import java.util.Scanner;
import java.util.ArrayList;

public class StaffUse {
	public static Cineplex StaffChoice(Cineplex cineplex) {
		StaffLogin();
		cineplex = StaffCinema(cineplex);
		return cineplex;
	}
	
	private static void StaffLogin(){
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
	
	private static Cineplex StaffCinema(Cineplex cineplex){
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
		Cinema cinema = StaffOptions(cineplex.getCinema(choice-1));
		cineplex.setCinema(cinema.getCinemaID(), cinema);
		return cineplex;
	}
	
	private static Cinema StaffOptions(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Movie Listing\n2. Cinema Showtimes\n3. Current Top 5 Ranking Movies\n4. Configure System Settings");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cinema = MovieListing(cinema);
				break;
			case 2:
				CinemaShowtimes(cinema);
				break;
			case 3:
				//list top 5 ranking movies
				break;
			case 4:
				//Stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				break;
			}
		}while(choice>4);
		return cinema;
	}
	
	private static Cinema MovieListing(Cinema cinema){ //Movie listing is a list of movies showing now and coming soon
		Scanner sc = new Scanner(System.in);
		boolean BlockBuster, Sneakpreview;
		int choice;
		do {
			System.out.println("1. Create Movie Listing\n2. Display Movie Listing\n3. Edit Movie Listing\n4. Delete Movie Listing");
			choice = sc.nextInt();
			sc.nextLine();	//Scanner buffer not cleared i dk how to get ard it except reading it agn
			switch(choice) {
			case 1:
				System.out.println("Enter movie title: ");
				String movieTitle = sc.nextLine();
				System.out.println("Enter show status:\n1. Coming Soon\n2. Preview\n3. Now Showing\n4. Not Available");
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
				break;
			case 2:
				displayMovie(cinema);
				break;
			case 3:
				editMovie(cinema);
				break;
			case 4:
				deleteMovie(cinema);
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
	
	private static void CinemaShowtimes(Cinema cinema) {
		
	}
	
	private static void displayMovie(Cinema cinema) {
		System.out.println("Lists of Movies:");
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
			int ind = i+1;
			System.out.println(ind + ". " + cinema.getListOfMovie().get(i));
		}
	}
	
	private static Movie selectMovie(Cinema cinema) {
		int movieSelect = -1;
		Scanner sc = new Scanner(System.in);
		displayMovie(cinema);
		
		System.out.println("Select a movie to edit");
		movieSelect = sc.nextInt() - 1;
		while(movieSelect < 0 || movieSelect >= cinema.getListOfMovie().size()) {
			System.out.println("Please select an available movie");
			movieSelect = sc.nextInt() - 1;
		}
		Movie movieChange = cinema.getMovie(movieSelect);
		return movieChange;
	}
	
	private static void editMovie(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		Movie movieChange = selectMovie(cinema);
		
		int choice = 1;
		do {
			System.out.println("Select movie details to adjust");
			System.out.println("1. Movie Title\n2. Movie Status\n3. Movie Rating\n4. Synopsis\n5. Director\n6. Blockbuster Status\n7. Sneak Preview Status\n 8. Exit");
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
				System.out.println("Enter new movie status:\n1. Coming Soon\n2. Preview\n3. Now Showing\n4. Not Available");
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
				choice = -1;
				break;
			default:
				System.out.println("Invalid input. Please try again");
				choice = 1;
				break;
			}
		}while(choice > 0 && choice < 9 );
	}
	
	private static void deleteMovie(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		Movie movieChange = selectMovie(cinema);
		
		movieChange.setShowStatus(4);
	}
}
