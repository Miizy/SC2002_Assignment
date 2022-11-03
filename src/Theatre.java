import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private String theatreID;
	private TheatreClass theatreClass;
	private Schedule showTime= new Schedule();
	private final static int NCOLS = 13;
	private final static int NROWS = 10;
	private final static int SCOLS = 9;
	private final static int SROWS = 6;
	private Seats [][] allseats= new Seats[NROWS][NCOLS];

	Theatre(int theatreID, int theatreClass, int cinemaID) throws IOException{
		this.setTheatreID(theatreID, cinemaID);
		this.setTheatreClass(theatreClass);
	}

	public String getTheatreID() {
		return theatreID;
	}

	public void setTheatreID(int theatreID, int cinemaID) {
		this.theatreID = convertIntToCode(theatreID, cinemaID);
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

		int midcol=6;
		if(this.theatreClass==TheatreClass.elit){
			midcol=4;
		}
		if(row <= 2){
			if(col%2==0){
				if(col <= midcol ){
					return allseats[row-1][col-2];
				}
				else{
					return allseats[row-1][col-1];
				}

			}
			else{
				if(col <= midcol ){
					return allseats[row-1][col-1];
				}
				else{
					return allseats[row-1][col];
				}
			}

		}

		if(col<=midcol){
			return allseats[row-1][col-1];
		}
		return allseats[row-1][col];
	}

	public void initializeSeats(){

		//more normal theater
		//making the couple seats
		for(int col = 0; col < NCOLS; col++){
			//as couple takes 2 matrix space
			if(col < 6){
				if(col % 2 == 1){
					continue;
				}
			}
			else if(col > 6){
				if(col % 2 == 0){
					continue;
				}
			}
			for(int row = 0; row < 2; row++){
				//gap for mid entry for passage
				if(col == 6 ){
					allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ap);
					continue;
				}
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ac);
			}
		}


		//making the normal seats full length
		for(int col=0; col<NCOLS; col++){
			//gap for mid entry

			for(int row=2; row<=NROWS-2; row++){
				if(col == 6 || row == 2 || row == NROWS-2){
					allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ap);
					continue;
				}
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.an);
			}
		}

		//making the normal seats but reduce cols
		for(int col=0; col<NCOLS; col++){

			for(int row=8; row<NROWS; row++){
				if(col == 6 || col < 2 || col >= NCOLS-2){
					allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ap);
					continue;
				}
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.an);
			}
		}
	}

	public void initializeEliteSeats(){
		//making the couple seats

		for(int col = 0; col < SCOLS; col++){
			//as couple takes 2 matrix space
			if(col < 4){
				if(col % 2 == 1){
					continue;
				}
			}
			else if(col > 4){
				if(col % 2 == 0){
					continue;
				}
			}
			for(int row = 0; row < 2; row++){
				//gap for mid entry for passage
				if(col == 4 ){
					allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ap);
					continue;
				}
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ac);
			}
		}

		//making the special seats full length
		for(int col=0; col<SCOLS; col++){
			//gap for mid entry

			for(int row=2; row<SROWS; row++){
				if(col == 4 || row == 2 || row == 4){
					allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ap);
					continue;
				}
				allseats[row][col]= new Seats(row+1, col+1, SeatStatus.ae);
			}
		}


	}

	public void showSeats(){
		int fcol= NCOLS;
		int frow= NROWS;

		if(this.theatreClass== TheatreClass.elit){
			fcol= SCOLS;
			frow= SROWS;
		}
		for(int rows=0; rows<frow; rows++){
			for(int col=0; col<fcol; col++){
				//not initialized therefore creates a space for passage
				if(allseats[rows][col]==null){
					continue;
				}

				else{
					allseats[rows][col].display();
				}
			}
			System.out.print("\n");
		}
	}

	private String convertIntToCode(int i, int j) {
		char letter2 = (char)((int)'A' + i%26);
		char letter = (char)((int)'A' + j%26);
		return letter + "0" + letter2;
	}
}