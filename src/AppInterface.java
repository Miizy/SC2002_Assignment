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
		File folder = new File("Cineplex");
		File [] allFiles = folder.listFiles();
		if(allFiles.length > 0) {
			//TODO: Read the cinema files and use it to create the cineplex object
		}
		else {
			cineplex = new Cineplex();
			for(int i=0; i<3; i++) {
				FileWriter writer = new FileWriter(new File(folder, "Cinema"+ i + ".txt"));
				cineplex.addCinema(i, 5, writer);
				writer.close();
			}
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
