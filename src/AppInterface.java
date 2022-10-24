import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppInterface {
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		Cineplex cineplex = null;
		String fileName = "Cineplex.ser";
        File temp = new File(fileName);
        if(temp.exists()) { //If Serialized Cineplex exist, read the file and copy to cineplex object
        	FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            cineplex = (Cineplex)in.readObject();
            in.close();
            file.close();
        }
        else { //else create a new cineplex object with default values
    		cineplex = new Cineplex();
    		for(int i=0; i<3; i++) {
    			cineplex.addCinema(i, 5);
    		}
    	}
        
		int choice;
		do {
			System.out.println("Type of User: \n1. Staff\n2. Movie Goer");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cineplex = StaffUse.StaffChoice(cineplex);
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid input. Please Try Again");
			}
		} while(choice >2);
		sc.close();
		
		//save the cineplex object as a serialized file for future use
		FileOutputStream file = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(cineplex);
		out.close();
		file.close();
	}
	
}
