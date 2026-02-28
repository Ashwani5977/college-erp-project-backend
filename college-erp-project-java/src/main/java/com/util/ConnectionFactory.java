package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	private static HikariDataSource hikariDataSource;
	static {
		try {
			Properties prop = new Properties();
			InputStream rs = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties");
			if (rs == null) {
				throw new RuntimeException("config.properties file not found");
			}
			prop.load(rs);
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(prop.getProperty("jdbc.url"));
			config.setUsername(prop.getProperty("jdbc.username"));
			config.setPassword(prop.getProperty("jdbc.password"));
			config.setDriverClassName(prop.getProperty("jdbc.driver"));
			config.setMaximumPoolSize(10);
			hikariDataSource = new HikariDataSource(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return hikariDataSource.getConnection();
	}

	public static void close(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
