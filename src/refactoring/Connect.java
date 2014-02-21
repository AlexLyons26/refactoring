package refactoring;

import java.sql.Connection;
import java.sql.DriverManager;

// creating a connection factory to encapsulate a set of connection configuration parameters that has been defined.
public class Connect {

	
	String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	String conUrl = "jdbc:odbc:Registration";
	String dbUser = "root";
	String dbPwd = "root";

	private static Connect connection = null;

	private Connect() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection()  {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(conUrl, dbUser, dbPwd);

		} catch (Exception e) {
			e.toString();
		}
		return conn;
	}

	public static Connect getInstance() {
		if (connection == null) {
			connection = new Connect();
		}
		return connection;
	}
}