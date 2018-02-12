package pl.codeSchool.model;

public class Excercise {
	private int id = 0;
	private String title;
	private String description;

	public Excercise(int id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public Excercise(String title, String description) {
		super();
		this.id = 0;
		this.title = title;
		this.description = description;
	}

	public Excercise() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

}
