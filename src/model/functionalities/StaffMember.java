package model.functionalities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Database;
import model.records.StaffRecord;
import model.records.Record;


public class StaffMember extends Functionality {
	@Override
	public  int add(Record record) {

		return 0;
	}

	@Override
	public int edit(String key, String value, Record record) {
		StaffRecord r = (StaffRecord) record;
		try {
			PreparedStatement stmt = con.prepareStatement("Update STAFF SET ID = ? , NAME = ? , ROLE = ?, "
					+ "TELEPHONE = ? , SALARY = ? "
					+ " WHERE "+key+" = ? ;" );
			stmt.setInt(1, r.getId());
			stmt.setString(2, r.getName());
			stmt.setInt(3, r.getRole());
			stmt.setString(4, r.getTelephone());
			stmt.setDouble(5, r.getSalary());
			stmt.setString(6, value);
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public int delete(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Record get(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
