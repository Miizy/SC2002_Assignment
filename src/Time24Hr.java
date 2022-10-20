public class Time24Hr {
	private int hour;
	private int min;
	public Time24Hr(int time) {
		int min = time % 10;
		time = time / 10;
		min += (time % 10) * 10;
		time = time / 10;
		int hr = time % 10;
		time = time / 10;
		hr += (time % 10) * 10;
		setMin(min);
		setHour(hr);
	}
	private void setMin(int min) {
		this.min = min;
	}
	private void setHour(int hour) {
		this.hour = hour;
	}
	public int getTime() {
		return (this.min + this.hour * 100);
	}
	
}
