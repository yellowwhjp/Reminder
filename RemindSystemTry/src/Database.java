import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Database {
	Connection connection = null;
	
	public Database(String name, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost/logintest?serverTimezone=JST", name,
				password);
	}
	
	public void showConnect() {
		System.out.println(this.connection);
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public void update(String name) throws SQLException {
		String strtime = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		PreparedStatement prep = connection.prepareStatement("UPDATE reminder SET lastmeetdate=? WHERE name=?");
		prep.setString(1, strtime);
		prep.setString(2, name);
		System.out.println(strtime);
		prep.executeUpdate();
//		prep.executeQuery();
//		return null;
	}
	
	public Userdata getUser() {
		return null;
	}
	
	public ArrayList<Userdata> getAllUserwhoneedmeet() throws SQLException, ParseException {
		ArrayList<Userdata> userlist = new ArrayList<Userdata>();
		PreparedStatement prep = connection.prepareStatement("SELECT * FROM reminder order by lastmeetdate");
		prep.executeQuery();
		ResultSet resultSet = prep.getResultSet();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance(); 
		String strtime = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		Date datenow = sdFormat.parse(strtime);
		rightNow.setTime(datenow);
		rightNow.add(Calendar.MONTH, -1);
		Date datenowminusonemonth = rightNow.getTime();
		while (resultSet.next()) {
			Date datefromdatabase = sdFormat.parse(resultSet.getString("lastmeetdate"));
			if (datenowminusonemonth.after(datefromdatabase)) {
				String name = resultSet.getString("name");
				String date2 = resultSet.getString("lastmeetdate");
				userlist.add(new Userdata(name, date2));
			}
		}
		return userlist;
	}
	
	public ArrayList<Userdata> getAllUser() throws SQLException{
		ArrayList<Userdata> userlist = new ArrayList<Userdata>();
		PreparedStatement prep = connection.prepareStatement("SELECT * FROM reminder order by lastmeetdate");
		prep.executeQuery();
		ResultSet resultSet = prep.getResultSet();
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			String pwd = resultSet.getString("lastmeetdate");
			userlist.add(new Userdata(name, pwd));
		}
		return userlist;
	}
}
