package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.PatientCase;
import model.records.PatientCaseRecord;

public class PatientCaseRecordTestEdit {
	
	PatientCase pc = new PatientCase();
	@Test
	public void editPatientCaseTest2 () {
		
		int output = pc.edit ("Medication", "med doesn't exist",new PatientCaseRecord(1111137,"flu","meda",250));
		//medication doesn't exist
		assertEquals(0, output);
	}
	@Test
	public void editPatientCaseTest1 () {
		int output = pc.edit ("Medication", "meda", new PatientCaseRecord(1111137,"flu","medc",250));
		assertEquals(1, output);
	}
	

}
