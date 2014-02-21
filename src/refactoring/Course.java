package refactoring;
import java.sql.*;

public class Course {
	private String name;
	private int credits;

Connection conn;
	
	

	Course(String name, int credits) {
		this.name = name;
		this.credits = credits;
	}
	
	public int getCredits() {
		return credits;
	}
	

	public String getName() {
		return name;
	}
	public Connection getConnection() {
		Connection con;
		con = Connect.getInstance().getConnection();
		return con;
	}
}