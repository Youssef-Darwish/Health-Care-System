import java.sql.SQLException;

import controller.Controller;
import model.functionalities.Functionality;
import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		StaffMember m = new StaffMember();
		StaffRecord r = new StaffRecord(1,"a",1,"01555",200);
		System.out.println(c.editStaffMember("ID","1",r));
	}
	
}
