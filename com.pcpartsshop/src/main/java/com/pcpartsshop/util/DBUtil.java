package com.pcpartsshop.util;

import java.sql.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pc_parts_shopPU");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}

	public static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void closePreparedStatement(Statement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
