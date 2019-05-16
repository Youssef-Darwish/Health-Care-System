package Junits;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

import model.functionalities.DoctorAvailablities;
import model.records.AvailablityRecord;

public class AvailabilityTestAdd {

	static DoctorAvailablities av = new DoctorAvailablities();

	@BeforeClass
	public static void before() {

		av.deleteComposite("DATE", new Date(2019 - 05 - 30).toString(), "HOUR", "09:00");
	}

	@Test
	public void validAdd() {
		int output = av.add(new AvailablityRecord(4, new Date(2019 - 05 - 30), "09:00"));
		assertEquals(1, output);

	}

	@Test
	public void duplicateAdd() {
		int output = av.add(new AvailablityRecord(4, new Date(2019 - 05 - 30), "09:00"));
		assertEquals(-1, output);

	}

}
