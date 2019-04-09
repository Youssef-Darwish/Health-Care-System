package model.functionalities;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.records.Record;

public class Medication extends Functionality {

	@Override
	public int add(Record record) {
		return 0;
	}

	@Override
	public int edit(String key, String value, String att, String newValue) {
		return 0;
		
		
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
