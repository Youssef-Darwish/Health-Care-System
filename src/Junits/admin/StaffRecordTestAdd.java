package Junits.admin;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class StaffRecordTestAdd {
	StaffMember sm = new StaffMember();
	@Test
	public void addStaffMemberTest1 () {
		
		int output = sm.add(new StaffRecord("Ahmed","doctor","2",250,"Amira"));
		assertEquals(1, output);
	}
	@Test
	public void addStaffMemberTest2 () {
		int output = sm.add(new StaffRecord("Ahmed","doctor","2",250,"Amira"));
		//duplicate entry
		assertEquals(-1, output);
	}
	@Test
	public void addStaffMemberTest3 () {
		
		int output = sm.add(new StaffRecord("Ahmed","doctor","3",250,"Amira"));
		assertEquals(1, output);
	}

}
