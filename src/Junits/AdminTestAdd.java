package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.records.StaffRecord;
import model.users.Admin;

public class AdminTestAdd {
	Admin admin = new Admin();
	@Test
	public void addStaffMemberTest1 () {
		
		int output = admin.addStaffMember(new StaffRecord(1,"Ahmed",2,"012012",250));
		assertEquals(1, output);
	}
	@Test
	public void addStaffMemberTest2 () {
		int output = admin.addStaffMember(new StaffRecord(1,"Ahmed",2,"2",250));
		//duplicate entry
		assertEquals(-1, output);
	}
	@Test
	public void addStaffMemberTest3 () {
		
		int output = admin.addStaffMember(new StaffRecord(1,"Ahmed",2,"3",250));
		assertEquals(1, output);
	}
	
	
	
}
