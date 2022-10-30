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
		System.out.println("Made Seat: "+ row+ " "+ col);
	}

	public void bookseat(){
		if(this.type==SeatStatus.an){
			this.booked=true;
		}
		else if(this.type==SeatStatus.ac){
			this.booked=true;
		}
		else if(this.type==SeatStatus.ae){
			this.booked=true;
		}
		else if(this.type==SeatStatus.ap){
			System.out.println("It's a passage.");
		}

		else{
			System.out.println("Not available! Already booked.");
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
	public void display(){
		if(this.type==SeatStatus.an && this.getbook()==false){
			System.out.print("[ ]");
		}
		else if(this.type==SeatStatus.ac && this.getbook()==false){
			System.out.print("[    ]");
		}
		else if(this.type==SeatStatus.ac && this.getbook()==true){
			System.out.print("[*  *]");
		}
		else if(this.type==SeatStatus.ae && this.getbook()==false){
			System.out.print("{ }");
		}
		else if(this.type==SeatStatus.ae && this.getbook()==true){
			System.out.print("{*}");
		}
		else if(this.type==SeatStatus.an && this.getbook()==true){
			System.out.print("[*]");
		}

		else if(this.type==SeatStatus.ap){
			System.out.print("   ");
		}

	}

}
