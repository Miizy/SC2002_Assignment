import java.io.Serializable;

public class Seats implements Serializable{
	private SeatStatus type;
	private int row;
	private int col;
	private boolean booked;

	public Seats(int row, int col, SeatStatus seatType){
		this.row= row;
		this.col= col;
		this.booked= false;
		this.type= seatType;
	}

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

	public boolean getbook(){
		return this.booked;
	}

	public int getrow(){
		return this.row;
	}

	public int getcol(){
		return this.col;
	}

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
	
	public SeatStatus getSeatType() {
		return type;
	}
}
