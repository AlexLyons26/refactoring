package refactoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CoursePersistence {
	//method to create a course and add it to the database
	public static Course create(String name, int credits) throws Exception {
		Connection conn = null;
		try {
		
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM course WHERE name = '" + name + "';");
			statement.executeUpdate("INSERT INTO course VALUES ('" + name + "', '" + credits + "');");
			return new Course(name, credits);
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}
//method to allow you to find a course.
	public static Course find(String name) {
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM course WHERE Name = '" + name + "';");
			if (!result.next()) return null;
			int credits = result.getInt("Credits");
			return new Course(name, credits);
		} 
		catch (Exception ex) {
			return null;
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	private String name;
	private String credits;
	
	public static void update(Course course) throws Exception {
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM COURSE WHERE name = '" + course.getName() + "';");
			statement.executeUpdate("INSERT INTO course VALUES('" + course.getName() + "','" + course.getCredits() + "');");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}
}
