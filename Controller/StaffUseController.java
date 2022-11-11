import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class StaffUseController {
	/**
	 * Gets the various show times for the specified cinema
	 * @param cinema Specific cinema to retrieve its show times
	 * @return A string of show times
	 */
	public static String getShowtimes(Cinema cinema) {
		ArrayList<String> order = new ArrayList<String>();
		ArrayList<String> movie = new ArrayList<String>();
		ArrayList<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
		for(int i=0; i<cinema.getListOfTheatre().size(); i++) {
			timeSlot.addAll(cinema.getTheatre(i).getTimeslot());
		}
		for(int i=0; i<timeSlot.size(); i++) {
			int movieIndex = order.indexOf(timeSlot.get(i).getMovie().getMovieTitle());
			if(movieIndex == -1) { 
				String movieTitle = timeSlot.get(i).getMovie().getMovieTitle();
				order.add(movieTitle);
				movie.add("\n" + movieTitle + ": \n" + timeSlot.get(i).getStartTime().getTime() + " - " + timeSlot.get(i).getEndTime().getTime());
			}
			else {
				String temp = movie.get(movieIndex);
				temp = temp + "\n" + timeSlot.get(i).getStartTime().getTime() + " - " + timeSlot.get(i).getEndTime().getTime();
				movie.set(movieIndex, temp);
			}
		}
		return String.join("\n", movie);
	}
	
	public static boolean isValidTime(String dateStr) {
		try {
			new SimpleDateFormat("dd-MM-yyyy HHmm").parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
    }
	
	public static String getMovies(Cinema cinema) {
		String listOfMovies = "Lists of Movies:\n";
		int ind = 0;
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
			ind+=1;
			if(cinema.getListOfMovie().get(i).getShowStatus().getStatus().equals("End Of Showing")) {
				listOfMovies += (ind + ". " + cinema.getListOfMovie().get(i).getMovieTitle() + " (Fin)\n");
			} else {
				listOfMovies += (ind + ". " + cinema.getListOfMovie().get(i).getMovieTitle() + "\n");
			}
		}
		return listOfMovies;
	}
	
	public static Cinema deleteMovie(Cinema cinema, int movieIndex) {
		Movie movieChange = cinema.getMovie(movieIndex);

		movieChange.setShowStatus(4);
		String movieTitle = movieChange.getMovieTitle();
		int finIndex = cinema.getListOfMovie().size()-1;
		while(cinema.getListOfMovie().get(finIndex).getShowStatus().getStatus().equals("End Of Showing")){
			finIndex--;
			System.out.println("finIndex is "+finIndex);
		};
		Movie temp = cinema.getMovie(finIndex);
		cinema.replaceMovie(movieIndex, temp);
		cinema.replaceMovie(finIndex, movieChange);
		for(int i=0;i<cinema.getListOfTheatre().size();i++) {
			Theatre theatre = cinema.getListOfTheatre().get(i);
			Iterator<TimeSlot> movieSlots = theatre.getTimeslot().iterator();
			while(movieSlots.hasNext()) {
				TimeSlot movieSegment = movieSlots.next();
				if(movieSegment.getMovie().getMovieTitle().equals(movieTitle)) {
					movieSlots.remove();
				}
			}
		}
		return cinema;
	}
	
	public static String displayTop5BySale(Cinema cinema) {
		double[][] movieSales = new double[2][cinema.getListOfMovie().size()];
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
			movieSales[0][i] = (double)i;
			movieSales[1][i] = cinema.getListOfMovie().get(i).getSales();
		}
		for(int i=1;i<cinema.getListOfMovie().size();i++) {
			for(int k=i;k>0;k--) {
				if(movieSales[1][k] > movieSales[1][k-1]) {
					double tempMov, tempSale;
					tempMov = movieSales[0][k];
					tempSale = movieSales[1][k];
					movieSales[0][k] = movieSales[0][k-1];
					movieSales[1][k] = movieSales[1][k-1];
					movieSales[0][k-1] = tempMov;
					movieSales[1][k-1] = tempSale;
				} else {
					break;
				}
			}
		}
		String top5 = "Top 5 Movies by Sales: \n";
		for(int j=0;j<cinema.getListOfMovie().size();j++) {
			int movieIndex = (int)movieSales[0][j];
			double sales = movieSales[1][j];
			int ind = 0;
			if(!cinema.getMovie(movieIndex).getShowStatus().getStatus().equals("End Of Showing")) {
				ind+=1;
				top5 += ind + ". "+cinema.getMovie(movieIndex).getMovieTitle()+"  $"+sales+"\n";
				if(j==5) {
					break;
				}
			}
		}
		return top5;
	}
	
	public static String displayTop5ByRating(Cinema cinema) {
		float[][] movieRatings = new float[2][cinema.getListOfMovie().size()];
		for(int i=0;i<cinema.getListOfMovie().size();i++) {
		movieRatings[0][i] = (float)i;
		movieRatings[1][i] = cinema.getListOfMovie().get(i).getOverallRating();
		}
		for(int i=1;i<cinema.getListOfMovie().size();i++) {
			for(int k=i;k>0;k--) {
				if(movieRatings[1][k] > movieRatings[1][k-1]) {
					float tempMov, tempRate;
					tempMov = movieRatings[0][k];
					tempRate = movieRatings[1][k];
					movieRatings[0][k] = movieRatings[0][k-1];
					movieRatings[1][k] = movieRatings[1][k-1];
					movieRatings[0][k-1] = tempMov;
					movieRatings[1][k-1] = tempRate;
				} else {
					break;
				}
			}
		}
		String top5 = "Top 5 Movies by Ratings: \n";
		for(int j=0;j<cinema.getListOfMovie().size();j++) {
			int movieIndex = (int)movieRatings[0][j];
			float rating = movieRatings[1][j];
			int ind = 0;
			if(!cinema.getMovie(movieIndex).getShowStatus().getStatus().equals("End Of Showing")) {
				ind+=1;
				top5 += ind + ". "+cinema.getMovie(movieIndex).getMovieTitle()+"  "+rating+"\n";
				if(ind==5) {
					break;
				}
			}
		}
		return top5;
	}
	
	public static String getTheatreIndex(Cinema cinema) {
		String listOfTheatre = "Select Theatre index:\n";
		for(int i=0;i<cinema.getListOfTheatre().size();i++) {
			int ind = i+1;
			listOfTheatre += ind + ". Theatre " + cinema.getListOfTheatre().get(i).getTheatreID() + "\n";
		}
		return listOfTheatre;
	}
	
	public static String getTimeslotIndex(Theatre theatre) {
		String timeslots = "";
		for(int i=0; i<theatre.getTimeslot().size(); i++) {
			timeslots += (i+1) + ". " + theatre.getTimeslot().get(i).getMovie().getMovieTitle() + " " + 
					theatre.getTimeslot().get(i).getStartTime().getTime() + " - " + theatre.getTimeslot().get(i).getEndTime().getTime();
		}
		return timeslots; 
	}
	
	public static String displayHolidayList(Cinema cinema) {
		String month[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
						  "Aug", "Sep", "Oct", "Nov", "Dec"};
		String holidaylist = "Current holiday list:\n";
		for(int i=0;i<cinema.getHolidayList().size();i++) {
			holidaylist += (i+1) + ". "+cinema.getHolidayList().get(i).get(Calendar.DATE)+" "+month[cinema.getHolidayList().get(i).get(Calendar.MONTH)]+" "+cinema.getHolidayList().get(i).get(Calendar.YEAR)+"\n";
		}
		return holidaylist;
	}
	
	public static boolean isValidDate(String[] date) {
		try {
			int day = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public static Cinema addHoliday(Cinema cinema) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter holiday date in dd-MM-yyyy");
		String holidayStr = sc.next();
		String[] holidayArr = holidayStr.split("-");
		if(StaffUseController.isValidDate(holidayArr)) {
			int day = Integer.parseInt(holidayArr[0]);
			int month = Integer.parseInt(holidayArr[1]);
			int year = Integer.parseInt(holidayArr[2]);
			GregorianCalendar holiday = new GregorianCalendar(year, month-1, day);
			cinema.getHolidayList().add(holiday);
			System.out.println("Holiday date added.");
		} else {
			System.out.println("Invalid date");
		}
		return cinema;
	}
	
	public static Cinema delHoliday(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select holiday to delete:");
		int choice = sc.nextInt()-1;
		if(choice<0 || choice > cinema.getHolidayList().size()) {
			System.out.println("Invalid input. Please try again");
		} else {
			cinema.getHolidayList().remove(choice);
			System.out.println("Successfully removed");
		}
		return cinema;
	}
	
}
