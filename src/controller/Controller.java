package controller;
import java.sql.Connection;

import model.records.Record;
import model.users.User;
import model.users.Admin;


public class Controller {
	private static Database db = new Database();
	private Connection con = db.getCon();
	// just for now
	User loggedIn = new Admin();
	
	public int editStaffMember (String key, String value, Record r) {
		return ((Admin) loggedIn).editStaffMember(key, value, r);
	}
}
