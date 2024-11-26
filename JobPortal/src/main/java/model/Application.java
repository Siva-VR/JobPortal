package model;

import java.sql.Timestamp;

public class Application {

	private int id;
	private int jobId;
	private int userId;
	private String status;
	private Timestamp applicationDate;
	
	public Application() {}
	public Application(int id, int jobId, int userId, String status, Timestamp applicationDate) {
		this.id = id;
		this.jobId = jobId;
		this.userId = userId;
		this.status = status;
		this.applicationDate = applicationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Timestamp applicationDate) {
		this.applicationDate = applicationDate;
	}

}
