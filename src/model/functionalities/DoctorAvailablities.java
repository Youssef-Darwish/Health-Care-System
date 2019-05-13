package model.functionalities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.AvailablityRecord;
import model.records.Record;

public class DoctorAvailablities extends Functionality {

	@Override
	public int add(Record record) {
		AvailablityRecord r = (AvailablityRecord) record;
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO AVAILABILITY (DOCTORID,DATE,HOUR) Values(?,?,?);");

			stmt.setInt(1, r.getDoctorId());
			stmt.setDate(2, (Date) r.getDate());
			stmt.setString(3, r.getTime());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	@Override
	public int edit(String key, String value, Record record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getAll() {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "SELECT * from Availablities;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ResultSet search(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM AVAILABILITY " + " WHERE DOCTORID  = ? ;");
			stmt.setString(1, value);
			return (ResultSet) stmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public int deleteComposite(String key1, String value1,String key2, String value2) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM AVAILABILITY " + " WHERE " + key1 + " = ?"
					+ " AND " + key2 +"= ? ;");
			stmt.setString(1, value1);
			stmt.setString(2, value2);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}


}
