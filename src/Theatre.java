import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private Seats [][] allseats;
	private int theatreID;
	private TheatreClass theatreClass;
	private ArrayList<TimeSlot> timeslotarr = new ArrayList<TimeSlot>();
	private final static int NCOLS = 13;
	private final static int NROWS = 10;
	private final static int SCOLS = 7;
	private final static int SROWS = 4;

	
	
	Theatre(int theatreID, int theatreClass) throws IOException{
		this.setTheatreID(theatreID);
		this.setTheatreClass(theatreClass);
	}

	public int getTheatreID() {
		return theatreID;
	}

	public void setTheatreID(int theatreID) {
		this.theatreID = theatreID;
	}

	public ArrayList<TimeSlot> getTimeslot() {
		return timeslotarr;
	}

	public Seats[][] getAllSeats(){
		return this.allseats;
	}
	
	public boolean addTimeslot(TimeSlot timeslot) {
		//iterate through each timeslot for the movies in the arr
		int slotSize= this.timeslotarr.size();
		for(int i =0; i<slotSize; i++){
			//if the current added slot's start time between some other movie's slot then dont allow
			if(this.timeslotarr.get(i).getStartTime().getTime()< timeslot.getStartTime().getTime() && this.timeslotarr.get(i).getEndTime().getTime()> timeslot.getStartTime().getTime()){
				return false;
			}
			//if the current added slot's end time between some other movie's slot then dont allow
			else if(this.timeslotarr.get(i).getStartTime().getTime()< timeslot.getEndTime().getTime() && this.timeslotarr.get(i).getEndTime().getTime()> timeslot.getEndTime().getTime()){
				return false;
			}
		}

		//if no crash
		this.timeslotarr.add(timeslot);
		return true;
	}
	
	public void setTheatreClass(int theatreClass) {
		if(theatreClass == 1)
			this.theatreClass = TheatreClass.plat;
		else if(theatreClass == 2)
			this.theatreClass = TheatreClass.ulti;
		else if(theatreClass == 3)
			this.theatreClass = TheatreClass.dolb;
		else if(theatreClass == 4)
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
		
}
