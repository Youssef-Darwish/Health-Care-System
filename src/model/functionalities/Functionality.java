package model.functionalities;

import java.sql.Connection;

import controller.Database;
import model.records.Record;

public abstract class Functionality {
	private static Database db = new Database();
	protected Connection con = db.getCon();

	public abstract int add(Record record);

	public abstract int edit(String key, String value, Record record);

	public abstract int delete(String key, String value);

	public abstract Record get(String key, String value);
}
