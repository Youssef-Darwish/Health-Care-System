package model.functionalities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import javafx.beans.property.SimpleStringProperty;
import model.records.Record;
import model.records.PatientRecord;

public class PatientRegistry extends Functionality {

	@Override
	public int add(Record record) {
		PatientRecord r = (PatientRecord) record;
		// ///////////////////////////////////////
		// ID of patient shoud be inserted manually?
		//////////////////////////////////////////
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO Patient (ID,NAME,TELEPHONE,Gender,REGISTERATIONDATE) Values(?,?,?,?,?);");

			stmt.setInt(1, r.getId());
			stmt.setString(2, r.getName());
			stmt.setString(3, r.getTelephone());
			stmt.setString(4, r.getGender());
			stmt.setDate(5, (Date) r.getRegistrationDate());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			return -1;
		}

	}

	@Override
	public int edit(String key, String value, Record record) {
		PatientRecord r = (PatientRecord) record;
		try {
			PreparedStatement stmt = con.prepareStatement("Update STAFF SET  NAME = ? , TELEPHONE = ?, "
					+ "GENDER = ? , REGISTERATIONDATE = ? " + " WHERE " + key + " = ? ;");

			stmt.setString(1, r.getName());
			stmt.setString(2, r.getTelephone());
			stmt.setString(3, r.getGender());
			stmt.setDate(4, (Date) r.getRegistrationDate());
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
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Patient " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public ResultSet getAll() {

		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "SELECT * from Patient;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ResultSet search(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Patient " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return (ResultSet) stmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
