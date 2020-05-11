import java.text.SimpleDateFormat;

public class Userdata {
	private String userName;
	private String time;
	
	public Userdata(String name, String time) {
		this.userName=name;
		this.time=time;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getDate() {
		return time;
	}
}
