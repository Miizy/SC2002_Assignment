import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private String theatreID;
	private TheatreClass theatreClass;
	private Schedule showTime= new Schedule();



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


	public boolean addTimeslot(TimeSlot timeslot) {
		timeslot.initializeSeatTings(theatreClass);
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