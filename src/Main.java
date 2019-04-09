import java.sql.SQLException;

import model.functionalities.Functionality;
import model.functionalities.StaffMember;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StaffMember m = new StaffMember();
		System.out.println(m.edit("ID","1","NAME","AA"));
	}
	
}
