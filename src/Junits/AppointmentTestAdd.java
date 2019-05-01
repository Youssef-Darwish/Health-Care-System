package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.functionalities.Appointment;
import model.records.AppointmentRecord;

public class AppointmentTestAdd {
	Appointment a = new Appointment();
	@Test
	public void test() {
		int output = a.add(new AppointmentRecord(1111130, 4,"14:30", new Date(2018-12-20)));
		assertEquals(1, output);
	}
	@Test
	public void test3() {
		int output = a.add(new AppointmentRecord(1111130, 400,"14:30", new Date(2018-12-20)));
		assertEquals(-1, output);
	}
	@Test
	public void test4() {
		int output = a.add(new AppointmentRecord(11111130, 4,"14:30", new Date(2018-12-20)));
		assertEquals(-1, output);
	}

}
