package refactoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OfferingPersistence {
	Connection conn;
	static String id;
	private static Course course;
	private static String daysTimes;

	public static Offering create(Course course, String daysTimesCsv) throws Exception {
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM offering;");
			result.next();
			int newId = 1 + result.getInt(1);
			statement.executeUpdate("INSERT INTO offering VALUES ('"+ newId + "','" + course.getName() + "','" + daysTimesCsv + "');");
			return new Offering(newId, course, daysTimesCsv);
		} 
		finally {
			try { 
				conn.close(); 
				} 
			catch (Exception ignored) {}
		}
	}

	public static Offering find(int id) {
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM offering WHERE ID =" + id + ";");
			if (result.next() == false)
				return null;
			String courseName = result.getString("Course");
			Course course = CoursePersistence.find(courseName);
			String dateTime = result.getString("DateTime");
			conn.close();
			return new Offering(id, course, dateTime);
		} 
		catch (Exception ex) {
			try { 
				conn.close(); 
			} catch (Exception ignored) {}
			return null;
		}
	}
	
	public static void update(Offering offering) throws Exception {
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
		
			statement.executeUpdate("DELETE FROM Offering WHERE ID=" + id + ";");
			statement.executeUpdate("INSERT INTO Offering VALUES('" + id + "','" + course.getName() + "','" + daysTimes + "');");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
