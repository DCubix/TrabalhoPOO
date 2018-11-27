package com.diego.xlanches.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:xlanches.db");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }

}
