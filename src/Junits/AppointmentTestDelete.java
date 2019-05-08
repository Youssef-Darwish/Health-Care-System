package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Test;

import model.functionalities.Appointment;
import model.records.AppointmentRecord;

public class AppointmentTestDelete {


	Appointment a = new Appointment();
	@Test
	public void test() {
		int output = a.delete("id", "2");
		assertEquals(1, output);
	}
	
	@Test
	public void test4() {
		int output = a.delete("id", "1111130");
		assertEquals(0, output);
	}
}
