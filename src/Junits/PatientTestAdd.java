package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.functionalities.PatientRegistry;
import model.records.PatientRecord;

public class PatientTestAdd {
	PatientRegistry pr = new PatientRegistry();
	//int id, String name, String telephone, String gender, Date regDate
	@Test
	public void Addtest1() {
		int output = pr.add(new PatientRecord (550, "elewa", "01228200862", "male", new Date(5-5-1995) ));
		assertEquals(1, output);
	}
	@Test
	public void Addtest2() {
		int output = pr.add(new PatientRecord (550, "elewa", "01228200862", "male", new Date(5-5-1995) ));
		assertEquals(-1, output);
	}
	
	@Test
	public void Addtest3() {
		int output = pr.add(new PatientRecord (550, "elewa2", "01228200862", "male", new Date(5-5-1995) ));
		assertEquals(-1, output);
	}

}
