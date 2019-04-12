package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.StaffMember;

public class StaffRecordTestDelete {
	StaffMember sm = new StaffMember();

	@Test
	public void deleteStaffMemberTest4 () {
		int output = sm.delete("TELEPHONE", "2");
		// one row affected
		assertEquals(1, output);
	}
	@Test
	public void deleteStaffMemberTest1 () {
		int output = sm.delete("TELEPHONE", "3");
		// one row affected
		assertEquals(1, output);
	}
	@Test
	public void deleteStaffMemberTest2 () {
		int output = sm.delete("ID", "1000");
		// no rows affected
		assertEquals(0, output);
	}
	@Test
	public void deleteStaffMemberTest3 () {
		int output = sm.delete("nn", "1000");
		// no column nn
		assertEquals(-1, output);
	}
	

}
