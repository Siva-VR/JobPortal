package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Application;
import util.DbClass;

public class ApplicationDAO {

	// Add a new application
	public boolean applyToJob(int jobId,int userId) {
		String query = "INSERT INTO Applications (job_id, user_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbClass.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, jobId);
			ps.setInt(2, userId);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error applying to job: " + e.getMessage());
			return false;
		} finally {
			closeStatement(ps);
			closeConnection(connection);
		}
	}

	// Get all applications for a specific user (Candidate)
	public List<Application> getApplicationsByUser(int userId) {
		String query = "SELECT * FROM Applications WHERE user_id = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> applications = new ArrayList<>();

		try {
			connection = DbClass.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				applications.add(new Application(rs.getInt("id"), rs.getInt("job_id"), rs.getInt("user_id"),
						rs.getString("status"), rs.getTimestamp("application_date")));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching applications for user: " + e.getMessage());
		} finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(connection);
		}

		return applications;
	}

	// Get all applications for a specific job (Employer)
	public List<Application> getApplicationsByJob(int jobId) {
		String query = "SELECT * FROM Applications WHERE job_id = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> applications = new ArrayList<>();

		try {
			connection = DbClass.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, jobId);
			rs = ps.executeQuery();

			while (rs.next()) {
				applications.add(new Application(rs.getInt("id"), rs.getInt("job_id"), rs.getInt("user_id"),
						rs.getString("status"), rs.getTimestamp("application_date")));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching applications for job: " + e.getMessage());
		} finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(connection);
		}

		return applications;
	}

	// Update application status (e.g., Approved/Rejected)
	public boolean updateApplicationStatus(int applicationId, String newStatus) {
		String query = "UPDATE Applications SET status = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbClass.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, newStatus);
			ps.setInt(2, applicationId);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error updating application status: " + e.getMessage());
			return false;
		} finally {
			closeStatement(ps);
			closeConnection(connection);
		}
	}

	// Delete an application by ID
	public boolean deleteApplication(int applicationId) {
		String query = "DELETE FROM Applications WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbClass.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, applicationId);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error deleting application: " + e.getMessage());
			return false;
		} finally {
			closeStatement(ps);
			closeConnection(connection);
		}
	}

	// Utility methods for closing resources
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Error closing Connection: " + e.getMessage());
			}
		}
	}

	private void closeStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				System.err.println("Error closing PreparedStatement: " + e.getMessage());
			}
		}
	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Error closing ResultSet: " + e.getMessage());
			}
		}
	}
}
