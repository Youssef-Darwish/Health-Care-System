package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.records.StaffRecord;
import model.users.Admin;

public class AdminTestDelete {
	Admin admin = new Admin();

	@Test
	public void deleteStaffMemberTest4 () {
		int output = admin.deleteStaffMember("NAME", "Ahmed");
		// one row affected
		assertEquals(2, output);
	}
	@Test
	public void deleteStaffMemberTest2 () {
		int output = admin.deleteStaffMember("ID", "1000");
		// no rows affected
		assertEquals(0, output);
	}
	
}
