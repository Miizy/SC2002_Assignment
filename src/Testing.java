import java.io.IOException;

public class Testing {
	public static void main(String args[]) throws IOException {
		Theatre t1 = new Theatre(1,1);
		t1.initializeSeats();
		t1.getSeatAt(1, 1).bookseat();
		t1.showSeats();

	}
}
