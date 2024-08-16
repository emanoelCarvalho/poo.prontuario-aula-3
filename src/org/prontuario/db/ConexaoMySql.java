package org.prontuario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.prontuario.util.ConfigLoader;

public class ConexaoMySql implements IConnection {

	private final String DB_USER;
	private final String DB_PASS;
	private final String DB_PORT;
	private final String DB_HOST;
	private final String DB_NAME;

	{
		DB_USER = ConfigLoader.getInstance("config").getProperty("DB_USER");
		DB_PASS = ConfigLoader.getInstance("config").getProperty("DB_PASS");
		DB_PORT = ConfigLoader.getInstance("config").getProperty("DB_PORT");
		DB_HOST = ConfigLoader.getInstance("config").getProperty("DB_HOST");
		DB_NAME = ConfigLoader.getInstance("config").getProperty("DB_NAME");
	}

	@Override
	public Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER,
					DB_PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

}
