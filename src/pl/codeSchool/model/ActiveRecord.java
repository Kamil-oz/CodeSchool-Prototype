package pl.codeSchool.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActiveRecord {
	protected String tableName = "";
	protected String id = "0";
	protected HashMap<String, String> tableFieldsValues = new HashMap<String, String>();
	protected ArrayList<String> displayOrder = new ArrayList<>();
	protected Connection conn = null;

	public String getTableName() {
		return tableName;
	}

	public void setTableFieldValues(String... fieldNames) {
		for (String string : fieldNames) {
			displayOrder.add(string);
			tableFieldsValues.put(string, "");
		}
	}

	public void setValue(String key, String value) {
		if (tableFieldsValues.containsKey(key)) {
			tableFieldsValues.put(key, value);
		} else {
			throw new RuntimeException(String.format("%s is not present in %s fields", key, tableName));
		}
	}

	public String getValue(String key) {
		return tableFieldsValues.get(key);
	}

	public String[] getFields() {
		String[] result = new String[tableFieldsValues.size()];
		return displayOrder.toArray(result);
	}

	public ActiveRecord() {
		try {
			this.conn = DbUtil.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String trimBrackets(String text) {
		if (text.indexOf('[') == 0 && text.indexOf(']') == text.length() - 1) {
			return text.substring(1, text.length() - 1);
		}
		return text;
	}

	private String getQuotationMarks() {
		String result = "";
		for (String string : displayOrder) {
			result = result + ",?";
		}
		return result.substring(1, result.length());
	}

	public void createNew() {
		String sql = String.format("INSERT INTO %s(%s) values (%s)", tableName, trimBrackets(displayOrder.toString()),
				getQuotationMarks());
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			for (String string : displayOrder) {
				stmt.setString(index++, getValue(string));
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		if (id.equals("0")) {
			createNew();
		}else {
	//		update();
		}
	}

	// public ArrayList<ActiveRecord> getAll() {
	//
	// }

	public ActiveRecord getById(int id) {
		String sql = String.format("SELECT * FROM %s where id = ?", tableName);
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			for (String fieldName : tableFieldsValues.keySet()) {
				tableFieldsValues.put(fieldName, result.getString(fieldName));
			}
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ActiveRecord();
		}

	}

	public void update() {

	}

}
