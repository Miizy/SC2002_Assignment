import java.io.Serializable;

public class PricingList implements Serializable{
	private double Senior;
	private double Student;
	private double Thur;
	private double MonWed;
	private double FriB;
	private double FriA;
	private double SS;
	private double Student3D;
	private double Thur3D;
	private double MonWed3D;
	private double FriB3D;
	private double FriA3D;
	private double SS3D;
	private double Sneakpreview;
	private double BlockBuster;
	private double EliteTheatre;
	private double CouplePrice;
	private double EliteSeat;
	private double EliteTheatrePrice;
	public PricingList(){
		setSenior(4);
		setStudent(7);
		setThur(8.50);
		setMonWed(9.50);
		setFriB(9.50);
		setFriA(11);
		setSS(11);
		setStudent3D(9);
		setThur3D(11);
		setMonWed3D(11);
		setFriB3D(15);
		setFriA3D(15);
		setSS3D(15);
		setSneakpreview(9.50);
		setBlockBusterPrice(1);
		setEliteTheatrePrice(2);
		setCouplePrice(3);
		setEliteSeat(3);
		setEliteTheatrePrice(1.50);
	}
	
	public double getSenior() {
		return Senior;
	}
	public double getStudent() {
		return Student;
	}
	public double getStudent3D() {
		return Student3D;
	}
	public double getMonWed() {
		return MonWed;
	}
	public double getMonWed3D() {
		return MonWed3D;
	}
	public double getThur() {
		return Thur;
	}
	public double getThur3D() {
		return Thur3D;
	}
	public double getFriB() {
		return FriB;
	}
	public double getFriB3D() {
		return FriB3D;
	}
	public double getFriA() {
		return FriA;
	}
	public double getFriA3D() {
		return FriA3D;
	}
	public double getSS() {
		return SS;
	}
	public double getSS3D() {
		return SS3D;
	}
	public double getSneakpreview() {
		return Sneakpreview;
	}
	public double getBlockBusterPrice() {
		return BlockBuster;
	}
	public double getElitePrice() {
		return EliteTheatre;
	}
	public double getCouplePrice() {
		return CouplePrice;
	}
	public double getEliteSeat() {
		return EliteSeat;
	}
	public double getEliteTheatrePrice() {
		return EliteTheatrePrice;
	}
	public void setSenior(double price) {
		Senior = price;
	}
	public void setStudent(double price) {
		Student = price;
	}
	public void setStudent3D(double price) {
		Student3D = price;
	}
	public void setMonWed(double price) {
		MonWed = price;
	}
	public void setMonWed3D(double price) {
		MonWed3D = price;
	}
	public void setThur(double price) {
		Thur = price;
	}
	public void setThur3D(double price) {
		Thur3D = price;
	}
	public void setFriB(double price) {
		FriB = price;
	}
	public void setFriB3D(double price) {
		FriB3D = price;
	}
	public void setFriA(double price) {
		FriA = price;
	}
	public void setFriA3D(double price) {
		FriA3D = price;
	}
	public void setSS(double price) {
		SS = price;
	}
	public void setSS3D(double price) {
		SS3D = price;
	}
	public void setSneakpreview(double price) {
		Sneakpreview = price;
	}
	public void setBlockBusterPrice(double price) {
		BlockBuster = price;
	}
	public void setElitePrice(double price) {
		EliteTheatre = price;
	}
	public void setCouplePrice(double price) {
		CouplePrice = price;
	}
	public void setEliteSeat(double price) {
		EliteSeat = price;
	}
	public void setEliteTheatrePrice(double price) {
		EliteTheatrePrice = price;
	}
	public void getPriceList() {
		
	}
}
