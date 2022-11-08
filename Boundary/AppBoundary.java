import java.util.Scanner;
import java.io.IOException;
import java.io.InvalidClassException;
import java.text.ParseException;

public class AppBoundary {
	public static void main(String args[]) throws IOException, ClassNotFoundException, InvalidClassException, ParseException {
		Scanner sc = new Scanner(System.in);
		Cineplex cineplex = FileReader.readFile();
        
		int choice;
		do {
			System.out.println("Type of User: \n1. Staff\n2. Movie Goer\n3. Exit");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cineplex = StaffUse.StaffChoice(cineplex);
				break;
			case 2:
				cineplex = MovieGoerUse.MovieGoerChoice(cineplex);
				break;
			case 3:
				System.out.println("Exiting program...");
				break;
			default:
				System.out.println("Invalid input. Please Try Again");
			}
		} while(choice != 3);
		sc.close();
		
		//save the cineplex object as a serialized file for future use
		FileReader.writeFile(cineplex);
	}
	
}
