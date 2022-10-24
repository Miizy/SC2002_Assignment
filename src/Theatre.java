import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private Seats [] seats;
	private int theatreID;
	private TheatreClass theatreClass;
	private ArrayList<TimeSlot> timeslot = new ArrayList<TimeSlot>();
	
	Theatre(int theatreID, TheatreClass theatreClass){
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
		return timeslot;
	}
	
	public boolean addTimeslot(TimeSlot timeslot) {
		//TODO: check if timeslot clash 

		//if no crash
		this.timeslot.add(timeslot);
		return true;
	}

	public TheatreClass getTheatreClass() {
		return theatreClass;
	}

	public void setTheatreClass(TheatreClass theatreClass) {
		this.theatreClass = theatreClass;
	}
}
