package Junits;

import static org.junit.Assert.*;

import org.junit.Test;

import model.functionalities.PatientCase;
import model.records.PatientCaseRecord;

public class PatientCaseRecordTestEdit {
	
	PatientCase pc = new PatientCase();
	@Test
	public void editPatientCaseTest2 () {
		
		int output = pc.edit ("Id", "100",new PatientCaseRecord(111111,"flu","meda",250));
		//medication doesn't exist
		assertEquals(0, output);
	}
	@Test
	public void editPatientCaseTest1 () {
		int output = pc.edit ("Id", "3", new PatientCaseRecord(1111111,"flu","medc",250));
		assertEquals(1, output);
	}
	

}
