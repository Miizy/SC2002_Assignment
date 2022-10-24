import java.io.Serializable;

public class Seats implements Serializable{
	private char [][] seats;
	
	Seats(int row, int col){
		seats = new char [row][col];
	}
}
