package controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

import model.records.Record;
import model.users.User;
import model.users.Admin;
import model.users.Doctor;
import model.users.Manager;
import model.users.Receptionist;


public class Controller {
	private static Database db = new Database();
	private Connection con = db.getCon();
	// change it in login
	private User loggedIn = new Admin();
	
	
	@SuppressWarnings("Untested")
	public int login (String userName, String password) {
		try {
			Statement stat = con.createStatement();
			String query = "";
			query += "select Role from STAFF where Name = '" + userName + "' and pass = MD5('" + password + "');";

			ResultSet result = (ResultSet) stat.executeQuery(query);
			if (!result.next()) { // empty set
				return -1;
			}
			
			int Role = result.getInt("Role");
			if (Role == 1){
				loggedIn = new Admin();
			}
			else if (Role == 2) {
				loggedIn = new Manager();
			}
			else if (Role == 3) {
				loggedIn = new Doctor();
			}
			else if (Role == 4) {
				loggedIn = new Receptionist();
			}
			else
				return -1;
			return 1;

		} catch (SQLException e) {

			return -2;
		}
	}
	public int editStaffMember (String key, String value, Record r) {
		return ((Admin) loggedIn).editStaffMember(key, value, r);
	}
	
	public int deleteStaffMember (String key, String value) {
		System.out.println();
		return ((Admin) loggedIn).deleteStaffMember(key, value);
	}

	public int addStaffMember (Record record) {
		System.out.println("in controller");
		return ((Admin) loggedIn).addStaffMember(record);
	}

}
