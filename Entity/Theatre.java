import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class that represents a particular theatre or cinema that holds multiple movie time slots.
 */
public class Theatre implements Serializable{
	/**
     * Theatre's unique ID
     */
    private String theatreID;
    /**
     * The Theatre type/class- Platinum or Elite
     */
    private TheatreClass theatreClass;

    /**
     * A schedule class that will hold the show time of that theatre.
     */
    private Schedule showTime= new Schedule();

	/**
     * Creates the Theatre object
     * @param theatreID The Number ID of the theatre
     * @param theatreClass The type of theatre
     * @param cinemaID The Number ID of the Cinema
     * @throws IOException If there is input error
     */
	Theatre(int theatreID, int theatreClass, int cinemaID) throws IOException{
		this.setTheatreID(theatreID, cinemaID);
		this.setTheatreClass(theatreClass);
	}
	/**
     * Gets the unique Theatre ID
     * @return unique Theatre ID string
     */

	public String getTheatreID() {
		return theatreID;
	}
	/**
     * Creates and sets the unique Theatre ID 
     * @param theatreID The Number ID of the theatre
     * @param cinemaID The Number ID of the Cinema
     */

	public void setTheatreID(int theatreID, int cinemaID) {
		this.theatreID = convertIntToCode(theatreID, cinemaID);
	}
	/**
     * Gets the Theatre's scheduled show time list of TimeSlot
     * @return Array list of TimeSlot
     */
	public ArrayList<TimeSlot> getTimeslot() {
		return showTime.getList();
	}

	/**
     * Adds the given time slot to the Theatre's schedule
     * @param timeslot time slot that needs to be added
     * @return True or False based on the sucess of the adding to the schedule
     */
	public boolean addTimeslot(TimeSlot timeslot) {
		timeslot.initializeSeatings(theatreClass);
		return showTime.add(timeslot);
	}

	/**
     * Sets the theatre's type/class 
     * @param theatreClass 1 means Plat class while 0 is Elite
     */
	public void setTheatreClass(int theatreClass) {
		if(theatreClass == 1)
			this.theatreClass = TheatreClass.plat;
		else
			this.theatreClass = TheatreClass.elit;
	}
	/**
     * Gets the Theatre type/class
     * @return Theatre class/type
     */
	public TheatreClass getTheatreClass() {
		return this.theatreClass;
	}

	/**
     * Creates a unique string ID using two integer values for the Theatre ID
     * @param i integer number
     * @param j integer number
     * @return String that is unique based on i and j
     */
	private String convertIntToCode(int i, int j) {
		char letter2 = (char)((int)'A' + i%26);
		char letter = (char)((int)'A' + j%26);
		return letter + "0" + letter2;
	}
}