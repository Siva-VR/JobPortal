package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Job;
import util.DbClass;

public class JobDAO {
	private Connection connection;
	private Statement stmt;
	public boolean addJob(String title, String des, String companyName, String location, double salary, int postedBy) {

		String query = "INSERT INTO Jobs (title, description, company_name, location, salary, posted_by) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			 connection = DbClass.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, des);
			ps.setString(3, companyName);
			ps.setString(4, location);
			ps.setDouble(5, salary);
			ps.setInt(6, postedBy);
			int rows = ps.executeUpdate();
			if (rows != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Job> getJobs() {
		String query = "SELECT * FROM Jobs";
		List<Job> jobs = new ArrayList<>();
		try {
			 connection = DbClass.getConnection();
			 stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				jobs.add(new Job(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getString("company_name"), rs.getString("location"), rs.getDouble("salary"),
						rs.getInt("posted_by")));
			}
			return jobs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Job> getJobsByPostedBy(int posted_by,List<Job> jobs) {
		List<Job> jobsPostedBy = new ArrayList<>();
		for(Job job:jobs) {
			if(job.getPostedBy()==posted_by) {
				jobsPostedBy.add(job);
			}
		}
		
		return jobsPostedBy;
	}
	public boolean deleteJob(int jobid) {
		try {
			 connection = DbClass.getConnection();
			stmt = connection.createStatement();
			String query = "Delete from jobs where id=" + jobid;
			int rows = stmt.executeUpdate(query);
			if (rows != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
