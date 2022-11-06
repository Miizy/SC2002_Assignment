/*import java.io.IOException;
import java.util.GregorianCalendar;
public class Testing {
	public static void main(String args[]) throws IOException {
		Theatre t1 = new Theatre(1,1,1);
		GregorianCalendar dstart = new GregorianCalendar(2022, 11, 2, 20, 30);
		GregorianCalendar dend= new GregorianCalendar(2022, 11, 2, 23, 20);
		Movie m1= new Movie("Shinging", 0, 2, "SO much", "Niko", true, false);
		TimeSlot ts1= new TimeSlot(dstart, dend, m1);
		t1.addTimeslot(ts1);
		ts1.getSeatTing().showSeats();
		ts1.getSeatTing().getSeatAt(4, 2).bookseat();
		ts1.getSeatTing().getSeatAt(3, 4).bookseat();
		ts1.getSeatTing().showSeats();
		dstart= new GregorianCalendar(2022, 11, 1, 20, 30);
		dend= new GregorianCalendar(2022, 11, 1, 23, 20);

		Movie m2= new Movie("doning", 0, 2, "SO much", "Niko", true, false);
		TimeSlot ts2= new TimeSlot(dstart, dend, m2);

		
		t1.addTimeslot(ts2);
		ts2.getSeatTing().showSeats();
		ts2.getSeatTing().getSeatAt(6, 2).bookseat();
		ts2.getSeatTing().getSeatAt(3, 5).bookseat();
		ts2.getSeatTing().showSeats();


		for(int j=0; j<t1.getTimeslot().size(); j++){
			System.out.println(j+" : "+ t1.getTimeslot().get(j).getMovie().getMovieTitle());
		}
	}
}*/
