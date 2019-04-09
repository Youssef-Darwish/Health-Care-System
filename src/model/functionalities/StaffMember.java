package model.functionalities;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.records.Record;

public class StaffMember extends Functionality {

	public  int add(Record record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(String key, String value, String att, String newValue) {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "update STAFF set "+ att +" = '"+ newValue +
					"' Where "+ key + " = '"+ value+"';";
			stat.executeUpdate(query);
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
