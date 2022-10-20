import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Theatre {
	private Seats [] seats;
	private int theatreID;
	private ArrayList<TimeSlot> timeslot = new ArrayList<TimeSlot>();
	
	Theatre(int theatreID, FileWriter writer) throws IOException{
		writer.write("Theatre " + theatreID + "\n");
		this.setTheatreID(theatreID);
		writer.write("End Theatre " + theatreID + "\n");
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
}
