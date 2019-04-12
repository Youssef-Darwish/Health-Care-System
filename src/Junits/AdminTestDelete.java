package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.users.Admin;

public class AdminTestDelete {
	Admin admin = new Admin();

	@Test
	public void deleteStaffMemberTest4 () {
		int output = admin.deleteStaffMember("TELEPHONE", "2");
		// one row affected
		assertEquals(1, output);
	}
	@Test
	public void deleteStaffMemberTest1 () {
		int output = admin.deleteStaffMember("TELEPHONE", "3");
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
		int output = admin.deleteStaffMember("nn", "1000");
		// no column nn
		assertEquals(-1, output);
	}

}
