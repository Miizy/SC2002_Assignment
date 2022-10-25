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
		if(this.type==SeatStatus.an){
			this.type= SeatStatus.un;
			this.booked=true;
		}
		else if(this.type==SeatStatus.ac){
			this.type= SeatStatus.uc;
			this.booked=true;
		}
		else if(this.type==SeatStatus.ae){
			this.type= SeatStatus.ue;
			this.booked=true;
		}
		else if(this.type==SeatStatus.au){
			this.type= SeatStatus.uu;
			this.booked=true;
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
		if(this.type==SeatStatus.an){
			System.out.print("[ ]");
		}
		else if(this.type==SeatStatus.ac){
			System.out.print("[    ]");
		}
		else if(this.type==SeatStatus.uc){
			System.out.print("[*  *]");
		}
		else if(this.type==SeatStatus.ae || this.type==SeatStatus.au){
			System.out.print("{ }");
		}
		else if(this.type==SeatStatus.ue || this.type==SeatStatus.uu){
			System.out.print("{*}");
		}
		else if(this.type==SeatStatus.un){
			System.out.print("[*]");
		}

	}

}
