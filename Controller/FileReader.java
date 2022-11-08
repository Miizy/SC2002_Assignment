import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileReader {
	
	public static Cineplex readFile() throws IOException, ClassNotFoundException {
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
		return cineplex;
	}
	
	public static void writeFile(Cineplex cineplex) throws IOException {
		String fileName = "Cineplex.ser";
		FileOutputStream file = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(cineplex);
		out.close();
		file.close();
	}
}
