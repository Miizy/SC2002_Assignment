import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
	private String theatreID;
	private TheatreClass theatreClass;
	private Schedule showTime= new Schedule();

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


	public boolean addTimeslot(TimeSlot timeslot) {
		timeslot.initializeSeatings(theatreClass);
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


	private String convertIntToCode(int i, int j) {
		char letter2 = (char)((int)'A' + i%26);
		char letter = (char)((int)'A' + j%26);
		return letter + "0" + letter2;
	}
}