package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbClass {
	
	static Connection con=null;
	
	public static Connection getConnection() {
		try {
			String url = "com.mysql.cj.jdbc.Driver";
			String dburl = "jdbc:mysql://localhost:3307/jobportal";
			String username ="root";
			String password = "toor";
			Class.forName(url);
			con = DriverManager.getConnection(dburl,username,password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
