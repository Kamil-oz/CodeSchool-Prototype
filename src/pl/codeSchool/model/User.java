package pl.codeSchool.model;

public class User extends ActiveRecord {

	public User() {
		super();
		tableName = "user";
		setTableFieldValues("username", "password", "email", "user_group_id");
	}

}
