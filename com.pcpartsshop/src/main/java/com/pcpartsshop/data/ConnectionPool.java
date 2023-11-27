package com.pcpartsshop.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {
	private static ConnectionPool pool = null;
	private static DataSource dataSource = null;

	private ConnectionPool() {
		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/pc_parts_shop");
		} catch (NamingException ex) {
			System.out.println(ex);
		}
	}

	public static synchronized ConnectionPool getInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException ex) {
			System.out.println(ex);
			return null;
		}
	}

	public void freeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
