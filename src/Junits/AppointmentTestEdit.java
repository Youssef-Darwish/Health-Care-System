package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Test;

import model.functionalities.Appointment;
import model.records.AppointmentRecord;

public class AppointmentTestEdit {

	Appointment a = new Appointment();
	@Test
	public void test() {
		int output = a.edit("id", "1",new AppointmentRecord(1111130, 4,"12:30", new Date(2018-12-20)));
		assertEquals(1, output);
	}
	
	@Test
	public void test4() {
		int output = a.edit("id", "1111130", new AppointmentRecord(11111130, 4,"13:30", new Date(2018-12-20)));
		assertEquals(0, output);
	}

}
