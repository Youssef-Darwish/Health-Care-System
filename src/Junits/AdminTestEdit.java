package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.records.StaffRecord;
import model.users.Admin;

public class AdminTestEdit {
	Admin admin = new Admin();
	@Test
	public void editStaffMemberTest1 () {
		int output = admin.editStaffMember("TELEPHONE", "5", new StaffRecord("Ahmed","doctor","2",250,"Amira"));
		// no rows affected
		assertEquals(0, output);
	}

	@Test
	public void editStaffMemberTest3 () {
		int output = admin.editStaffMember("mm", "100", new StaffRecord("Ahmed","doctor","2",250,"Amira"));
		// Error
		assertEquals(-1, output);
	}
	
	@Test
	public void editStaffMemberTest2 () {
		int output = admin.editStaffMember("TELEPHONE", "3", new StaffRecord("Ahmed","doctor","3",240,"Amira"));
		// one row affected
		assertEquals(1, output);
	}
	
	



}
