package model.functionalities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.StaffRecord;
import model.records.Record;

public class StaffMember extends Functionality {

	@Override
	public int add(Record record) {
		StaffRecord r = (StaffRecord) record;
		System.out.println("entered");

		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO STAFF (NAME,ROLE,TELEPHONE,SALARY,PASS) Values(?,?,?,?,MD5(?));");

			stmt.setString(1, r.getName());
			stmt.setInt(2, r.getRole());
			stmt.setString(3, r.getTelephone());
			stmt.setDouble(4, r.getSalary());
			stmt.setString(5, r.getPassword());
			return stmt.executeUpdate();

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
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}
	
	@Override
	public ResultSet search (String key,String value) {

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM STAFF " 
			+ " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return (ResultSet)stmt.executeQuery();
////			ResultSet result = (ResultSet) stat.executeQuery(query);
////			//System.out.println(query+ "   "+result.getRow());
////			//result.getFetchSize()
//			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ResultSet getAll() {

		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			//check select all query
//			query += "SELECT * from STAFF WHERE " + key + " = " + value + ";";
			query += "SELECT * from STAFF;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResultSet getAllStaff() {

		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			//check select all query
			query += "SELECT * from STAFF;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
