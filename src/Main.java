import java.sql.SQLException;

import controller.Controller;
import model.functionalities.Functionality;
import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
//
		StaffRecord r = new StaffRecord(2,"a",1,"01555",200,"pass");
//		System.out.println(r.getPassword());
//		System.out.println(c.editStaffMember("ID","1",r));
		
		
//		c.deleteStaffMember("NAME", "youssef");
		
		c.addStaffMember(r);
	}
	
}
