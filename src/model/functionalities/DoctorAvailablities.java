package model.functionalities;

import java.sql.PreparedStatement;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.Record;

public class DoctorAvailablities extends Functionality {

	@Override
	public int add(Record record) {
		// TODO Auto-generated method stub
		return 0;
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

}
