package Junits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import model.functionalities.StaffMember;

public class StaffRecordTestSearch {
	StaffMember sm = new StaffMember();

	public void searchStaffMemberTest1 () {
		int counter = 0;
		try {
			ResultSet rs = sm.search("Role","doctor");
			while (!rs.isLast()) {
				rs.next();
				counter++;
			}
			assertEquals(2, counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getStaffMemberTest1 () {
		int counter = 0;
		try {
			ResultSet rs = sm.search("TELEPHONE","-1");
			while (!rs.isLast()) {
				rs.next();
				counter++;
			}
			assertEquals(0, counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
