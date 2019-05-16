package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import model.functionalities.DoctorAvailablities;
import model.records.AvailablityRecord;

public class AvailabilityTestDelete {

	static DoctorAvailablities av = new DoctorAvailablities();

	@BeforeClass
	public static void before() {

		av.add(new AvailablityRecord(4,new Date(2019 - 06 - 1), "12:30"));
		
	}

	@Test
	public void validDelete() {
		int result = av.deleteComposite("DATE", new Date(2019 - 06 - 1).toString(), "HOUR", "12:30");
		assertEquals(1, result);
	}

	@Test
	public void invalidDelete() {
		int res = av.deleteComposite("DTE", new Date(2019 - 06 - 1).toString(), "HOUR", "12:30");
		assertEquals(-1, res);
	}

}
