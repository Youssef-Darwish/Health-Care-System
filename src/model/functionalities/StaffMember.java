package model.functionalities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Database;
import model.records.StaffRecord;
import model.records.Record;

public class StaffMember extends Functionality {
	
	@Override
	public int add(Record record) {
		StaffRecord r = (StaffRecord) record;
		System.out.println("entered");

		try {
//		    String sql = "INSERT INTO Users  VALUES (MD5(CONCAT(?,CURRENT_TIMESTAMP), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement("INSERT INTO STAFF Values(?,?,?,?,?,MD5(?));");
			stmt.setInt(1, r.getId());
			stmt.setString(2, r.getName());
			stmt.setInt(3, r.getRole());
			stmt.setString(4, r.getTelephone());
			stmt.setDouble(5, r.getSalary());
			stmt.setString(6, r.getPassword());
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}

	}

	@Override
	public int edit(String key, String value, Record record) {
		StaffRecord r = (StaffRecord) record;
		try {
			PreparedStatement stmt = con.prepareStatement("Update STAFF SET  NAME = ? , ROLE = ?, "
					+ "TELEPHONE = ? , SALARY = ? " + " WHERE " + key + " = ? ;");
	
			stmt.setString(1, r.getName());
			stmt.setInt(2, r.getRole());
			stmt.setString(3, r.getTelephone());
			stmt.setDouble(4, r.getSalary());
			stmt.setString(5, value);
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public int delete(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM STAFF " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public Record get(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
