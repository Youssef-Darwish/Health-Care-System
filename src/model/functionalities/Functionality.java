package model.functionalities;

import model.records.Record;

public interface Functionality {

	public int add(Record record);

	public int edit(String key, String value, String att, String newValue);

	public int delete(String key, String value);

	public Record get(String key, String value);

}
