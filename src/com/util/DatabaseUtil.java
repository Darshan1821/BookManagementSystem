package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

	private static final String DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
	// private static final String URL =
	// "jdbc:oracle:thin:@intvmoradb04:1521:ORAJAVADB";
	// private static final String PASSWORD ="tcstvm05";
	// private static final String USERNAME ="TVM1819_TVM05_TJA19_TEST";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:dexter";
	private static final String USERNAME = "system";
	private static final String PASSWORD = "dxt12345678";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVERNAME);
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return con;
	}

	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(PreparedStatement ps) throws SQLException {

		if (ps != null) {
			ps.close();
		}
	}

	public static void closeSet(ResultSet rs) throws SQLException {

		if (rs != null) {
			rs.close();
		}
	}
}
