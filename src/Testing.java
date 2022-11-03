import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Date;
public class Testing {
	public static void main(String args[]) throws IOException {
		Theatre t1 = new Theatre(1,0);
		GregorianCalendar gc = new GregorianCalendar(2022, 11, 2, 20, 30);
		Date dstart= new Date(gc.getTimeInMillis());
		GregorianCalendar gc1= new GregorianCalendar(2022, 11, 2, 23, 20);
		Date dend= new Date(gc1.getTimeInMillis());
		Movie m1= new Movie("Shinging", 0, 2, "SO much", "Niko", true, false);
		TimeSlot ts1= new TimeSlot(dstart, dend, m1);
		t1.initializeEliteSeats();
		t1.getSeatAt(5, 1).bookseat();
		t1.showSeats();
		gc= new GregorianCalendar(2022, 11, 1, 20, 30);
		gc1= new GregorianCalendar(2022, 11, 1, 23, 20);
		dstart= new Date(gc.getTimeInMillis());
		dend= new Date(gc1.getTimeInMillis());
		Movie m2= new Movie("doning", 0, 2, "SO much", "Niko", true, false);
		TimeSlot ts2= new TimeSlot(dstart, dend, m2);

		Schedule sc= new Schedule();

		sc.add(ts1);
		sc.add(ts2);

		sc.update();

		for(int j=0; j<sc.getList().size(); j++){
			System.out.println(j+" : "+ sc.getList().get(j).getMovie().getMovieTitle());
		}
	}
}
