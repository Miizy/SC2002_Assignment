import java.util.Scanner;

public class StaffUse {
	public static void StaffChoice(Cineplex cineplex) {
		StaffLogin();
		StaffCinema(cineplex);
		StaffOptions();
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
	
	private static void StaffCinema(Cineplex cineplex){
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			for(int i=0; i<cineplex.getListOfCinema().size(); i++) {
				
			}
			choice = sc.nextInt();
			
		}while(choice >3);
	}
	
	private static void StaffOptions() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Movie Listing\n2. Cinema Showtimes\n3. Configure System Settings");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				MovieListing();
				break;
			case 2:
				CinemaShowtimes();
				break;
			case 3:
				break;
			case 4:
				//Stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				break;
			}
		}while(choice>4);
	}
	
	private static void MovieListing(){ //Movie listing is a list of movies showing now and coming soon
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Create Movie Listing\n2. Edit Movie Listing\n3. Delete Movie Listing");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				MovieListing();
				break;
			case 2:
				CinemaShowtimes();
				break;
			case 3:
				break;
			case 4:
				//Stop the program
				break;
			default:
				System.out.println("Invalid input. Please try again");
				break;
			}
		}while(choice>4);
	}
	
	private static void CinemaShowtimes() {
		
	}
}
