package model.users;

import model.functionalities.Functionality;
import model.functionalities.StaffMember;
import model.records.Record;

public class Admin implements User {
	Functionality f = new StaffMember();
	
	public int editStaffMember(String key, String value, Record record) {
		return f.edit(key, value, record);
	}
	

}
