package model;

public class Job {

	private int id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private double salary;
	private int postedBy;
		
	public Job() {}
	public Job(int id, String title, String description, String companyName, String location, double salary,
			int postedBy) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.companyName = companyName;
		this.location = location;
		this.salary = salary;
		this.postedBy = postedBy;
	}
	// Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String company) {
		this.companyName = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}
		
}
