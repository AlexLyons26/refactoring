package refactoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class SchedulePersistence {
	Connection conn;

	
	ArrayList<Offering> schedule = new ArrayList<Offering>();


	private String name;
	


	public static void deleteAll() throws Exception {
		Connection conn = null;
		try {
		
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE * FROM schedule;");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}
	
	public static Schedule create(String name) throws Exception {
		Connection conn = null;
		try {
		
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + name + "';");
			return new Schedule(name);
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}
	
	public static Schedule find(String name) {
		Connection conn = null;
		try {
		
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM schedule WHERE Name= '" + name + "';");
			Schedule schedule = new Schedule(name);
			while (result.next()) {
				int offeringId = result.getInt("OfferingId");
				Offering offering = OfferingPersistence.find(offeringId);
				schedule.offerings.add(offering);
			}
			return schedule;
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

	public static Collection<Schedule> all() throws Exception {
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		Connection conn = null;
		try {
			
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT DISTINCT Name FROM schedule;");
			while (results.next())
			result.add(SchedulePersistence.find(results.getString("Name")));
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
		return result;
	}

	

	public static void update(Schedule schedule) throws Exception {
		Connection conn = null;
		try {
		
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + schedule.getName() + "';");
			for (int i = 0; i < schedule.offerings.size(); i++) {
				Offering offering = (Offering) schedule.offerings.get(i);
				statement.executeUpdate("INSERT INTO schedule VALUES('" + schedule.getName() + "','" + offering.getId() + "');");
			}
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}


}
