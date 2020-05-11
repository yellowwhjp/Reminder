import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Database database = new Database("root", "a9988765");
		database.showConnect();
//		database.update("test1");
		ArrayList<Userdata> userlist = database.getAllUser();
		for (Userdata each : userlist) {
			System.out.println(each.getUserName() + " " + each.getDate());
		}
		
		System.out.println("-----------------------------");
		
		ArrayList<Userdata> userlist1 = database.getAllUserwhoneedmeet();
		for (Userdata each : userlist1) {
			System.out.println(each.getUserName() + " " + each.getDate());
		}
		
		database.close();
	}

}
