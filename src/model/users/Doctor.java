package model.users;

import com.mysql.jdbc.ResultSet;

import model.functionalities.Functionality;
import model.functionalities.PatientCase;
import model.records.Record;

public class Doctor implements User {
	Functionality f = new PatientCase();
	
	public int editPatientCase (String key, String value, Record record) {
		return f.edit(key, value, record);
	}

	public int deletePatientCase(String key, String value) {
		System.out.println("in doctor");
		return f.delete(key, value);
	}
	
	public int addPatientCase(Record record){
		System.out.println("in doctor");
		return f.add(record);
	}
	
	
	public ResultSet searchPatientCase (String key,String value){
		
		return f.search(key,value);
	}
}
