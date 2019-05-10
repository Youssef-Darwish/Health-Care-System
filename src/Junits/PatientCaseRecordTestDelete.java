package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.PatientCase;
import model.records.PatientCaseRecord;

public class PatientCaseRecordTestDelete {

	PatientCase pc = new PatientCase();
	@Test
	public void addPatientCaseTest2 () {
		
		int output = pc.delete ("PatientId", "1");
		assertEquals(0, output);
	}
	@Test
	public void editPatientCaseTest1 () {
		int output = pc.delete ("PatientId", "1111137");
		assertEquals(1, output);
	}

}
