package Junits;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;

import org.junit.Test;

import model.functionalities.PatientCase;
import model.functionalities.StaffMember;
import model.records.PatientCaseRecord;
import model.records.StaffRecord;

public class PatientCaseRecordTestAdd {

	PatientCase pc = new PatientCase();
	@Test
	public void addPatientCaseTest1 () {
		
		int output = pc.add(new PatientCaseRecord(1111137,"flu","med doesn't exist",250));
		//medication doesn't exist
		assertEquals(-1, output);
	}
	@Test
	public void addPatientCaseTest2 () {
		
		int output = pc.add(new PatientCaseRecord(1111137,"flu","meda",1000));
		assertEquals(1, output);
	}
	
//	@Test
//	public void addPatientCaseTest3 () {
//		
//		int output = pc.add(new PatientCaseRecord(1,"flu","med doesn't suite",250));
//		//alergies
//		assertEquals(-3, output);
//	}
//	@Test
//	public void addPatientCaseTest4 () {
//		
//		int output = pc.add(new PatientCaseRecord(1,"flu","flu med",250));
//		//alergies
//		assertEquals(1, output);
//	}

}
