import java.io.Serializable;

/**
 * This class represents a seat in a theatre that can be booked.
 * @author Viral Mehta
 */
public class Seats implements Serializable{
	/**
     * The type of seats- Couple, Normal, Elite, Passage
     */
    private SeatStatus type;
    /**
     * The row number of the seat as an id
     */
    private int row;
    /**
     * The column number of the seat as an id
     */
    private int col;
    /**
     * Variable to know if a seat is booked or free
     */
    private boolean booked;
	/**
     * Constructs the Seats using the row, col as id and also the Seat Type. 
     * Initially it is free.
     * @param row row number
     * @param col column number
     * @param seatType Seat Type
     */
	public Seats(int row, int col, SeatStatus seatType){
		this.row= row;
		this.col= col;
		this.booked= false;
		this.type= seatType;
	}
	/**
     * Allows the user to book a seat. 
     * If booked or a booking a passage will give error message.
     */
	public void bookseat(){
		if(this.type==SeatStatus.ap){
			System.out.println("It's a passage.");
			return;
		}

		if(this.getbook()==true){
			System.out.println("Not available! Already booked.");
			return;
		}
		else{
			this.booked=true;
		}
	}
	/**
     * Gets if a seat is booked or not
     * @return boolean value based on the seat being booked
     */
	public boolean getbook(){
		return this.booked;
	}
	/**
     * Gets the seat's row number
     * @return row number
     */
	public int getrow(){
		return this.row;
	}
	/**
     * Gets the seat's column number
     * @return column number
     */
	public int getcol(){
		return this.col;
	}
	/**
     * Prints the seat depending on the type and the book status.
     * Allows for visualization.
     */
	//used to display each seat based on their seattype and their status
	public String display(){
		if(this.type==SeatStatus.an && this.getbook()==false){
			return "[ ]";
		}
		else if(this.type==SeatStatus.ac && this.getbook()==false){
			return "[    ]";
		}
		else if(this.type==SeatStatus.ac && this.getbook()==true){
			return "[*  *]";
		}
		else if(this.type==SeatStatus.ae && this.getbook()==false){
			return "{ }";
		}
		else if(this.type==SeatStatus.ae && this.getbook()==true){
			return "{*}";
		}
		else if(this.type==SeatStatus.an && this.getbook()==true){
			return "[*]";
		}
		else if(this.type==SeatStatus.ap){
			return "   ";
		}
		return "";
	}
	/**
     * Gets the seat's type
     * @return Seat Type
     */
	public SeatStatus getSeatType() {
		return type;
	}
}
