package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.functionalities.PatientRegistry;
import model.records.PatientRecord;

public class PatientTestEdit {

	PatientRegistry pr = new PatientRegistry();
	//int id, String name, String telephone, String gender, Date regDate
	@Test
	public void editTest1() {
		int output = pr.edit("TELEPHONE", "01228200862",new PatientRecord (550, "elewa", "01328200862", "male", new Date(5-5-1995)));
		assertEquals(1, output);
	}
	@Test
	public void editTest2() {
		int output = pr.edit("Name", "anoon",new PatientRecord (550, "elewa", "01228200862", "male", new Date(5-5-1995) ));
		assertEquals(0, output);
	}
	
	

}
