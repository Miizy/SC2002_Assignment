import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class AppInterface {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		Cineplex cineplex = null;
		File file = new File ("cineplex");
		if(file.exists()) {
			Scanner fileReader = new Scanner(file);
		}
		else {
			FileWriter writer = new FileWriter(file + ".txt");
			int [] numOfTheatre = {5,5,5};
			cineplex = new Cineplex(numOfTheatre, writer);
			writer.close();
		}
		int choice;
		do {
			System.out.println("Type of User: \n1. Staff\n2. Movie Goer");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				StaffUse.StaffChoice(cineplex);
				break;
			case 2:
				
				break;
			default:
				System.out.println("Invalid input. Please Try Again");
			}
		} while(choice >2);
		sc.close();
	}
	
	public static void MovieGoerOptions() {
		
	}
}
