package Junits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import model.users.Manager;

public class ReportTest {
	Manager m = new Manager();
	@Test
	public void test1() throws SQLException {
		ResultSet rs = m.getNumberOfNewAppointments();
		while (rs.next()) {
			assertEquals(1, rs.getInt(1));
		}
	}
	@Test
	public void test2() throws SQLException {
		ResultSet rs = m.getNumberOfNewPatients();
		while (rs.next()) {
			assertEquals(2, rs.getInt(1));
		}
	}
	@Test
	public void test3() throws SQLException {
		ResultSet rs = m.getNumberOfNewStaff();
		int[] x = {1,1,0,0};
		int i = 0;
		while (rs.next()) {
			assertEquals(x[i], rs.getInt(1));
			i++;
		}
	}
}
