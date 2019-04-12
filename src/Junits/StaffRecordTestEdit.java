package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class StaffRecordTestEdit {
	StaffMember sm = new StaffMember();
	@Test
	public void editStaffMemberTest1 () {
		int output = sm.edit("TELEPHONE", "5", new StaffRecord(11,"Ahmed",2,"2",250));
		// no rows affected
		assertEquals(0, output);
	}

	@Test
	public void editStaffMemberTest3 () {
		int output = sm.edit("mm", "100", new StaffRecord(100,"Ahmed",2,"2",250));
		// Error
		assertEquals(-1, output);
	}
	
	@Test
	public void editStaffMemberTest2 () {
		int output = sm.edit("TELEPHONE", "3", new StaffRecord(1,"Ahmed",2,"3",240));
		// one row affected
		assertEquals(1, output);
	}

}
