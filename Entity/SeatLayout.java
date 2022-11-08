import java.io.Serializable;
/**
 * Represents the Seat Layout of the Seats.
 * @author Viral Mehta
 */
public class SeatLayout implements Serializable{
    
    /**
     * 13 columns in a 2D matrix of seats for the normal theatre layout
     */
    private final static int NCOLS = 13;
    /**
     * 10 rows in a 2D matrix of seats for the normal theatre layout
     */
    private final static int NROWS = 10;
    /**
     * 9 columns in a 2D matrix of seats for the elite theatre layout
     */
    private final static int SCOLS = 9;
    /**
     * 6 rows in a 2D matrix of seats for the elite theatre layout
     */
    private final static int SROWS = 6;
	/**
     * A 2D Matrix of seats with default being normal size
     */
    private Seats [][] allseats= new Seats[NROWS][NCOLS];
    /**
     * The Theater type 
     */
    private TheatreClass theatreClass;
	/**
     * Creates the SeatLayout with the given Theatre Type through initialization of the seats.
     * @param type Theatre Type could be Platinum which is normal or Elite.
     */
    public SeatLayout(TheatreClass type){
        if(type==TheatreClass.plat){
            this.theatreClass= TheatreClass.plat;
            initializeSeats();
        }
        else{
            this.theatreClass= TheatreClass.elit;
            initializeEliteSeats();
        }

    }
	/**
     * Gets the Theatre Type
     * @return SeatLayout's theatre type.
     */
    public TheatreClass getTheatreClass() {
		return this.theatreClass;
	}
	/**
     * Allows the user to access a particular seat from the all seats available.
     * @param col Column number of the seat to access
     * @param row Row number of the seat to access
     * @return Seat class in the all seats.
     */
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
	/**
     * Initializes the 2D array by filling it up with Normal and Couple types of seats 
     * and have a Platinum Theatre class layout.
     */
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
	/**
     * Initializes the 2D array by filling it up with Elite and Couple types of seats 
     * and have an Elite Theatre class layout.
     */
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
	/**
	 * The Seat Layout showing booked and free seats in string format to output in Command Line
	 * @return	string which has the seat layout format
	 */
	public String showSeats(){
		int fcol= NCOLS;
		int frow= NROWS;

		String seatString = "";
		if(this.theatreClass== TheatreClass.elit){
			fcol= SCOLS;
			frow= SROWS;
			seatString += "      1  2  3  4     5  6  7  8 \n";
		}
		else{
			seatString += "      1  2  3  4  5  6     7  8  9 10 11 12 \n";
		}

		//print the header display for columns
		for(int rows=0; rows<frow; rows++){
			String sf=String.format("%4d ",rows+1);
			seatString += sf;
			for(int col=0; col<fcol; col++){
				//not initialized therefore creates a space for passage
				if(allseats[rows][col]==null){
					continue;
				}
				else{
					seatString += allseats[rows][col].display();
				}
			}
			seatString += "\n";
		}
		return seatString;
	}
}
