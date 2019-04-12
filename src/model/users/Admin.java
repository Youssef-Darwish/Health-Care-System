package model.users;

import com.mysql.jdbc.ResultSet;

import model.functionalities.Functionality;
import model.functionalities.StaffMember;
import model.records.Record;

public class Admin implements User {
	Functionality f = new StaffMember();

	public int editStaffMember(String key, String value, Record record) {
		return f.edit(key, value, record);
	}

	public int deleteStaffMember(String key, String value) {
		System.out.println("in admin");
		return f.delete(key, value);
	}
	
	public int addStaffMember(Record record){
		System.out.println("in admin");
		return f.add(record);
	}
	
	public ResultSet getStaffMember(String key, String value){
		
		return f.get(key, value);
	}
	

}
