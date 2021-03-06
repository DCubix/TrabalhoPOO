package com.diego.xlanches.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.diego.xlanches.data.Produto;
import com.diego.xlanches.db.DB;

public class ProdutoDAO extends IDAO<Produto, Integer> {

	private static ProdutoDAO instance;
	public static ProdutoDAO get() {
		if (instance == null) {
			instance = new ProdutoDAO();
			instance.create();
		}
		return instance;
	}

	@Override
	protected void create() {
		final String sql =
				"CREATE TABLE IF NOT EXISTS PRODUTOS ("
                + "		id integer PRIMARY KEY,"
                + "		nome text NOT NULL,"
                + "		desc text,"
                + "		valor real,"
                + "		__del text"
                + ");";
		run(sql);
	}

	@Override
	protected void drop() {
		run("DROP TABLE IF EXISTS PRODUTOS");
	}

	@Override
	public boolean insert(Produto item) {
		final String sql = "INSERT INTO PRODUTOS(nome, desc, valor, __del) VALUES(?, ?, ?, '')";
		try (Connection conn = DB.connect(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, item.getNome());
			st.setString(2, item.getDescricao());
			st.setDouble(3, item.getValor());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Produto item) {
		if (item == null) return false;
		final String sql = "DELETE FROM PRODUTOS WHERE id = ?";
		try (Connection conn = DB.connect(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, item.getId());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Produto item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Produto> select(Integer... ps) {
		ArrayList<Produto> res = new ArrayList<>();
		String sql = "SELECT * FROM PRODUTOS WHERE __del = ''";
		if (ps.length > 0) {
			sql += " AND id = " + ps[0];
		}
		try (
				Connection conn = DB.connect();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
		) {
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("desc"));
				p.setValor(rs.getDouble("valor"));
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}

}
