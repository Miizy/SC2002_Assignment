import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private Seats [][] allseats;
	private String theatreID;
	private TheatreClass theatreClass;
	private Schedule showTime= new Schedule();
	private final static int NCOLS = 13;
	private final static int NROWS = 10;
	private final static int SCOLS = 7;
	private final static int SROWS = 4;

	
	
	Theatre(int theatreID, int theatreClass) throws IOException{
		this.setTheatreID(theatreID);
		this.setTheatreClass(theatreClass);
	}

	public String getTheatreID() {
		return theatreID;
	}

	public void setTheatreID(int theatreID) {
		this.theatreID = convertIntToCode(theatreID);
	}

	public ArrayList<TimeSlot> getTimeslot() {
		return showTime.getList();
	}

	public Seats[][] getAllSeats(){
		return this.allseats;
	}
	
	public boolean addTimeslot(TimeSlot timeslot) {
		return showTime.add(timeslot);
	}
	
	public void setTheatreClass(int theatreClass) {
		if(theatreClass == 1)
			this.theatreClass = TheatreClass.plat;
		else
			this.theatreClass = TheatreClass.elit;
	}
	
	public TheatreClass getTheatreClass() {
		return this.theatreClass;
	}

	public Seats getSeatAt(int col, int row){
		return allseats[row-1][col-1];
	}

	public void initializeSeats(){

		//more normal theater 
		//making the couple seats
		for(int col=0; col<NCOLS; col++){
			//gap for mid entry
			if(col==6){
				continue;
			}
			for(int row=0; row<2; row++){
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ac);
			}
		}


		//making the normal seats full length
		for(int col=0; col<NCOLS; col++){
			//gap for mid entry
			if(col==6){
				continue;
			}
			for(int row=3; row<NROWS-2; row++){
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.an);
			}
		}

		//making the normal seats but reduce cols
		for(int col=2; col<NCOLS-2; col++){
			//gap for mid entry
			if(col==6){
				continue;
			}
			
			for(int row=8; row<NROWS; row++){
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.an);
			}
		}
	}

	public void showSeats(){
		for(int col=0; col<NCOLS; col++){
			for(int row=0; row<2; row++){
				//not initialized therefore creates a space for passage
				if(allseats[row][col]==null){
					System.out.print("   ");
				}

				else{
					allseats[row][col].display();
				}
			}
		System.out.print("\n");
		}
	}
		
	private String convertIntToCode(int i) {
		int quot = i/26;
	    int rem = i%26;
	    char letter = (char)((int)'A' + rem);
	    if( quot == 0 ) {
	        return ""+letter;
	    } else {
	        return convertIntToCode(quot-1) + letter;
	    }
	}
}
