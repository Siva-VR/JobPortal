  package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DbClass;

public class UserDAO {
	public static Connection con = null;
	public static Statement smt = null;

	public boolean addUser(String name, String email, String pass, String role) {
		try {
			con = DbClass.getConnection();
			smt = con.createStatement();

			String query = "insert into users(name,email,password,role)" + "values('" + name + "','" + email + "','"
					+ pass + "','" + role + "')";
			int rows = smt.executeUpdate(query);
			if (rows != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				smt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<User> getAllUsers() {
		List<User> ul = new ArrayList<>();
		try {
			con = DbClass.getConnection();
			smt = con.createStatement();
			String query = "select * from users";
			ResultSet rs = smt.executeQuery(query);
			System.out.println();
			User u = null;
			while (rs.next()) {
				int uid = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String role = rs.getString("role");
				u = new User(uid, name, email, pass, role);
				ul.add(u);
			}
			rs.close();
			return ul;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				smt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public User getUserById(int id, List<User> ul) {

		try {
			con = DbClass.getConnection();
			smt = con.createStatement();
			String query = "select * from users where id=" + id;
			ResultSet rs = smt.executeQuery(query);
			System.out.println();
			User u = null;
			if (rs.next()) {
				int uid = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String role = rs.getString("role");
				u = new User(uid, name, email, pass, role);
			}
			rs.close();
			return u;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				smt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String updateUser(int id, String name, String email, String pass, String role) {
		try {
			con = DbClass.getConnection();
			String query = "update users set name=?,email=?,password=? role=? where id=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, pass);
			psmt.setString(4, role);
			psmt.setInt(5, id);
			int rows = psmt.executeUpdate();
			psmt.close();
			if (rows != 0) {
				return "User Details updated sucessfully";
			} else {
				return "User Details not updated";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public boolean deleteUser(int id) {
		try {
			con = DbClass.getConnection();
			smt = con.createStatement();
			String query = "Delete from users where id=" + id;
			int rows = smt.executeUpdate(query);
			if (rows != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				smt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public User searchUser(String email, String password, List<User> userList) {

		for (User u : userList) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				return u;
			}

		}

		return null;
	}
}
