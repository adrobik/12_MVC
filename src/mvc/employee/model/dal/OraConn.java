package mvc.employee.model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class OraConn {

	private static Connection conn;

	public static Connection open(String link, String login, String password) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		if (conn == null)
			conn = DriverManager.getConnection(link, login, password);
		return conn;
	}

	public static void close() throws SQLException {
		if (conn != null)
			conn.close();
	}

	public static Connection getConnection() throws SQLException {
		if (conn != null)
			return conn;
		else {
			return open("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "xtemp01", "xtemp01");
		}
	}

}
