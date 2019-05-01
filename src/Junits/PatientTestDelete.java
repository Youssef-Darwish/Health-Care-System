package Junits;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.functionalities.PatientRegistry;
import model.records.PatientRecord;

public class PatientTestDelete {	
		PatientRegistry pr = new PatientRegistry();
		@Test
		public void deleteTest1() {
			int output = pr.delete("NAME", "elewa");
			assertEquals(1, output);
		}
		@Test
		public void deleteTest2() {
			int output = pr.delete("Name", "anoon");
			assertEquals(0, output);
		}

}
