package Junits.admin;

import static org.junit.Assert.*;

import org.junit.Test;

import model.records.StaffRecord;
import model.users.Admin;

public class AdminTestAdd {
	Admin admin = new Admin();
	@Test
	public void addStaffMemberTest1 () {
		
		int output = admin.addStaffMember(new StaffRecord("Ahmed","Admin","012012",250,"pass"));
		assertEquals(1, output);
	}
	@Test
	public void addStaffMemberTest2 () {
		int output = admin.addStaffMember(new StaffRecord("Ahmed","Admin","012012",250,"pass2"));
		//duplicate entry
		assertEquals(-1, output);
	}
	@Test
	public void addStaffMemberTest3 () {
		
		int output = admin.addStaffMember(new StaffRecord("Ahmed","Doctor","3",250,"pass3"));
		assertEquals(1, output);
	}
	
	
	
}
