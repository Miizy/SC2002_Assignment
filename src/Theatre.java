import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Theatre {
	private Seats [] seats;
	private int theatreID;
	private ArrayList<TimeSlot> timeslotarr = new ArrayList<TimeSlot>();
	
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
		return timeslotarr;
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
}
