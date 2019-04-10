package model.functionalities;

import java.sql.Connection;

import controller.Database;
import model.records.Record;

public abstract class Functionality {
	private static Database db = new Database();
	protected Connection con = db.getCon();

	public int add(Record record) {
		return 0;
	}

	public int edit(String key, String value, Record record) {
		return 0;
	}

	public int delete(String key, String value) {
		return 0;
	}

	public Record get(String key, String value) {
		return null;
	}
}
