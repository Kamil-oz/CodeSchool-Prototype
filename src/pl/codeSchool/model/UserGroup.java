package pl.codeSchool.model;

public class UserGroup {
	private Integer id = 0;
	private String name = "";

	public UserGroup(String name) {
		super();
		this.name = name;
		this.id = 0;
	}

	public UserGroup() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
