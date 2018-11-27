package com.diego.xlanches.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.diego.xlanches.db.DB;

public abstract class IDAO<T, P> {
	protected abstract void create();
	protected abstract void drop();
	public abstract boolean insert(T item);
	public abstract boolean delete(T item);
	public abstract boolean update(T item);
	public abstract ArrayList<T> select(P... ps);
	
	protected void run(String sql) {
		try (Connection conn = DB.connect(); Statement st = conn.createStatement()) {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
