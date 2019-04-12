package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class StaffRecordTestAdd {
	StaffMember sm = new StaffMember();
	@Test
	public void addStaffMemberTest1 () {
		
		int output = sm.add(new StaffRecord(1,"Ahmed",2,"2",250));
		assertEquals(1, output);
	}
	@Test
	public void addStaffMemberTest2 () {
		int output = sm.add(new StaffRecord(1,"Ahmed",2,"2",250));
		//duplicate entry
		assertEquals(-1, output);
	}
	@Test
	public void addStaffMemberTest3 () {
		
		int output = sm.add(new StaffRecord(1,"Ahmed",2,"3",250));
		assertEquals(1, output);
	}

}
