package Junits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import model.users.Admin;

public class AdminTestSearch {

	Admin admin = new Admin();
	@Test
	public void searchStaffMemberTest1 () {
		int counter = 0;
		try {
			ResultSet rs = admin.searchStaffMember("Role","doctor");
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
			ResultSet rs = admin.searchStaffMember("TELEPHONE","-1");
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
