package model.functionalities;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.Record;

public class Medication extends Functionality {

	@Override
	public int add(Record record) {
		return 0;
	}

	@Override
	public int edit(String key, String value, Record record) {
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
			query += "SELECT * from Medication;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ResultSet search(String key,String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
