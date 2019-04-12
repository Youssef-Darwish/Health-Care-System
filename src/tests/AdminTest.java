package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.records.StaffRecord;
import model.users.Admin;

public class AdminTest {
	Admin admin = new Admin();
	
	
	@Test
	public void addStaffMemberTest1 () {
		
		int output = admin.addStaffMember(new StaffRecord("Ahmed",2,"2",250,"pass"));
		assertEquals(1, output);
	}
	@Test
	public void addStaffMemberTest2 () {
		int output = admin.addStaffMember(new StaffRecord("Ahmed",2,"2",250,"pass"));
		//duplicate entry
		assertEquals(-1, output);
	}
	
	@Test
	public void editStaffMemberTest1 () {
		int output = admin.editStaffMember("NAME", "Rowan", new StaffRecord("new Ahmed",2,"2",250,"pass gdeda"));
		assertEquals(1, output);
	}
	@Test
	public void editStaffMemberTest2 () {
		int output = admin.editStaffMember("ID", "100", new StaffRecord(100,"Ahmed",2,"2",250));
		// one row affected
		assertEquals(0, output);
	}
	@Test
	public void editStaffMemberTest3 () {
		int output = admin.editStaffMember("SALARY", "0", new StaffRecord(100,"Ahmed",2,"2",250));
		// one row affected
		assertEquals(0, output);
	}
	@Test
	public void deleteStaffMemberTest1 () {
		int output = admin.deleteStaffMember("Name", "user");
		// one row affected
		assertEquals(1, output);
	}
	@Test
	public void deleteStaffMemberTest2 () {
		int output = admin.deleteStaffMember("ID", "1000");
		// no rows affected
		assertEquals(0, output);
	}
	@Test
	public void deleteStaffMemberTest3 () {
		int output = admin.deleteStaffMember("SALARY", "0");

		assertEquals(0, output);
	}

}