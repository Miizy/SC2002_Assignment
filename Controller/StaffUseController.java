import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;
/**
 * A controller class used by Staff to coordinate
 * with the Staff boundary class and the entity classes
 *
 */
public class StaffUseController {
	
	/**
	 * Gets the all movie show times for the specified cinema
	 * @param cinema The specified cinema to retrive all the movie time slots from
	 * @return A string of movie show times
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
	
	/**
	 * Checks the if the input date is of the format DD-MM-YYYY HHmm
	 * @param dateStr A date formatted as a string to be checked
	 * @return True if date is formatted properly, False if date is of the wrong format
	 */
	public static boolean isValidTime(String dateStr) {
		try {
			new SimpleDateFormat("dd-MM-yyyy HHmm").parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
    }
	
	/**
	 * Get the list of movies in a specific cinema
	 * @param cinema The specified cinema to retrieve movies from
	 * @return A list of movies
	 */
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
	
	/**
	 * Changes a movie's show status to End Of Showing
	 * Removes the movie from all the cinema's schedule
	 * @param cinema The specified cinema to retrieve the movie and time slots from
	 * @param movieIndex Index of the movie in the movie list
	 * @return An updated cinema containing the updated movie and schedules
	 */
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
	
	/**
	 * Displays only the current top 5 movies sorted by sales
	 * @param cinema The specified cinema to retrieve movies from
	 * @return A string of the top 5 movies sorted by sales
	 */
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
		int ind = 0;
		for(int j=0;j<cinema.getListOfMovie().size();j++) {
			int movieIndex = (int)movieSales[0][j];
			double sales = movieSales[1][j];
			if(!cinema.getMovie(movieIndex).getShowStatus().getStatus().equals("End Of Showing")) {
				ind++;
				top5 += ind + ". "+cinema.getMovie(movieIndex).getMovieTitle()+"  $"+sales+"\n";
				if(j==5) {
					break;
				}
			}
		}
		return top5;
	}
	
	/**
	 * Displays only the current top 5 movies sorted by ratings
	 * @param cinema The specified cinema to retrieve movies from
	 * @return A string of the top 5 movies sorted by sales
	 */
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
		int ind = 0;
		for(int j=0;j<cinema.getListOfMovie().size();j++) {
			int movieIndex = (int)movieRatings[0][j];
			float rating = movieRatings[1][j];
			if(!cinema.getMovie(movieIndex).getShowStatus().getStatus().equals("End Of Showing")) {
				ind++;
				top5 += ind + ". "+cinema.getMovie(movieIndex).getMovieTitle()+"  "+rating+"\n";
				if(ind==5) {
					break;
				}
			}
		}
		return top5;
	}
	
	/**
	 * Gets a list of theaters from a specific cinema
	 * @param cinema The specified cinema to retrieve theatres from
	 * @return A list of theater indexes
	 */
	public static String getTheatreIndex(Cinema cinema) {
		String listOfTheatre = "Select Theatre index:\n";
		for(int i=0;i<cinema.getListOfTheatre().size();i++) {
			int ind = i+1;
			listOfTheatre += ind + ". Theatre " + cinema.getListOfTheatre().get(i).getTheatreID() + "\n";
		}
		return listOfTheatre;
	}
	
	/**
	 * Gets a list of time slots from a specific cinema
	 * @param theatre The specified theatre to retrieve time slots from
	 * @return A list of time slots
	 */
	public static String getTimeslotIndex(Theatre theatre) {
		String timeslots = "";
		for(int i=0; i<theatre.getTimeslot().size(); i++) {
			timeslots += (i+1) + ". " + theatre.getTimeslot().get(i).getMovie().getMovieTitle() + " " + 
					theatre.getTimeslot().get(i).getStartTime().getTime() + " - " + theatre.getTimeslot().get(i).getEndTime().getTime() + "\n";
		}
		return timeslots; 
	}
	
	/**
	 * Displays the list of holidays
	 * @param cineplex The current cineplex
	 * @return A list of holidays
	 */
	public static String displayHolidayList(Cineplex cineplex) {
		String month[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
						  "Aug", "Sep", "Oct", "Nov", "Dec"};
		String holidaylist = "Current holiday list:\n";
		for(int i=0;i<cineplex.getHolidayList().size();i++) {
			holidaylist += (i+1) + ". "+cineplex.getHolidayList().get(i).get(Calendar.DATE)+" "+month[cineplex.getHolidayList().get(i).get(Calendar.MONTH)]+" "+cineplex.getHolidayList().get(i).get(Calendar.YEAR)+"\n";
		}
		return holidaylist;
	}
	
	/**
	 * Checks if the input string date can be converted to an integer array
	 * @param date A string containing date in integer dd-MM-YYYY format
	 * @return True if conversion to integer is possible, False if conversion to integer is not possible
	 */
	public static boolean isValidDate(String holidayDate) {
		String[] date = holidayDate.split("-");
		try {
			int day = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	/**
	 * Creates a new GregorianCalendar object to be added to the holiday list
	 * @param cineplex The current cineplex
	 * @param holidayDate A string containing date in integer dd-MM-YYYY format
	 * @return An updated cineplex containing the new list of holidays
	 * @throws ParseException If inputed holiday date is not of the correct format
	 */
	public static Cineplex addHoliday(Cineplex cineplex, String holidayDate) throws ParseException {
		String[] holidayArr = holidayDate.split("-");
		int day = Integer.parseInt(holidayArr[0]);
		int month = Integer.parseInt(holidayArr[1]);
		int year = Integer.parseInt(holidayArr[2]);
		GregorianCalendar holiday = new GregorianCalendar(year, month-1, day);
		cineplex.getHolidayList().add(holiday);
		return cineplex;
	}
	
	/**
	 * Removes a specific holiday from the list of holidays
	 * @param cineplex The current cineplex
	 * @return An updated cineplex containing the new list of holidays
	 */
	public static Cineplex delHoliday(Cineplex cineplex, int choice) {
		cineplex.getHolidayList().remove(choice-1);
		return cineplex;
	}
	
}
