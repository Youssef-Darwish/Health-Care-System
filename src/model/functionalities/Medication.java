package model.functionalities;

import com.mysql.jdbc.ResultSet;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet search(String key,String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
