package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import model.records.StaffRecord;
import model.users.Admin;

public class ControllerTest {

//	Controller ctrler = new Controller();
//	@Test
//	public void addStaffMemberTest1 () {
//		
//		int output = ctrler.addStaffMember(new StaffRecord(100,"Ahmed",2,"2",250));
//		assertEquals(1, output);
//	}
//	@Test
//	public void addStaffMemberTest2 () {
//		int output = ctrler.addStaffMember(new StaffRecord(100,"Ahmed",2,"2",250));
//		//duplicate entry
//		assertEquals(-1, output);
//	}
//	
//	@Test
//	public void editStaffMemberTest1 () {
//		int output = ctrler.editStaffMember("ID", "11", new StaffRecord(11,"Ahmed",2,"2",250));
//		// no rows affected
//		assertEquals(1, output);
//	}
//	@Test
//	public void editStaffMemberTest2 () {
//		int output = ctrler.editStaffMember("ID", "100", new StaffRecord(100,"Ahmed",2,"2",250));
//		// one row affected
//		assertEquals(1, output);
//	}
//	@Test
//	public void editStaffMemberTest3 () {
//		int output = ctrler.editStaffMember("mm", "100", new StaffRecord(100,"Ahmed",2,"2",250));
//		// one row affected
//		assertEquals(-1, output);
//	}
//	@Test
//	public void deleteStaffMemberTest1 () {
//		int output = ctrler.deleteStaffMember("ID", "100");
//		// one row affected
//		assertEquals(1, output);
//	}
//	@Test
//	public void deleteStaffMemberTest2 () {
//		int output = ctrler.deleteStaffMember("ID", "1000");
//		// no rows affected
//		assertEquals(1, output);
//	}
//	@Test
//	public void deleteStaffMemberTest3 () {
//		int output = ctrler.deleteStaffMember("nn", "1000");
//		// no column nn
//		assertEquals(-1, output);
//	}

}
