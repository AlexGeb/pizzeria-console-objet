package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.ExitException;

public abstract class DaoJdbc {

	private Connection connection;
	private String host = "blsxb6cjfpu78rs-postgresql.services.clever-cloud.com:5432/blsxb6cjfpu78rs";
	private String password = "q1xKatkfZuz6LdazL1zT";
	private String user = "upq079x4usuy8be9s8yj";
	protected final Logger LOGGER = LoggerFactory.getLogger(DaoJdbc.class);

	public DaoJdbc() {
		_init();
	}

	protected Connection getConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			} else {
				String URLConnection = "jdbc:postgresql://" + host;
				connection = DriverManager.getConnection(URLConnection, user, password);
				return connection;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void _init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void closeSqlResources(Connection conn, Statement statement, ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				manageSqlException(e);
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				manageSqlException(e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				manageSqlException(e);
			}
		}
	}
	protected void manageSqlException(SQLException e) {
		LOGGER.error(e.getMessage());
		throw new ExitException(e.getMessage());
	}
}
