package com.diego.xlanches.dao;

import com.diego.xlanches.data.ItemCaixa;
import com.diego.xlanches.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemCaixaDAO extends IDAO<ItemCaixa, Integer> {

	private static ItemCaixaDAO instance;
	public static ItemCaixaDAO get() {
		if (instance == null) {
			instance = new ItemCaixaDAO();
			instance.create();
		}
		return instance;
	}
	
	@Override
	protected void create() {
		final String sql =
				"CREATE TABLE IF NOT EXISTS ITEMCAIXA ("
                + "		id integer PRIMARY KEY,"
                + "		idProduto integer NOT NULL,"
                + "		qtd integer,"
				+ "		fechado text"
                + ");";
		run(sql);
	}

	@Override
	protected void drop() {
		run("DROP TABLE IF EXISTS ITEMCAIXA");
	}

	@Override
	public boolean insert(ItemCaixa item) {
		final String sql = "INSERT INTO ITEMCAIXA(idProduto, qtd, fechado) VALUES(?, ?, '')";
		try (Connection conn = DB.connect(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, item.getProduto().getId());
			st.setInt(2, item.getQuantidade());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(ItemCaixa item) {
		if (item == null) return false;
		final String sql = "DELETE FROM ITEMCAIXA WHERE id = ?";
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
	public boolean update(ItemCaixa item) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean fecha(int id) {
		String sql = "UPDATE ITEMCAIXA SET fechado = '*'";
		try (
				Connection conn = DB.connect();
				PreparedStatement st = conn.prepareStatement(sql)
		) {
			st.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<ItemCaixa> select(Integer... ps) {
		ArrayList<ItemCaixa> res = new ArrayList<>();
		String sql = "SELECT * FROM ITEMCAIXA WHERE fechado = ''";
		if (ps.length > 0) {
			sql += " AND id = " + ps[0];
		}
		try (
				Connection conn = DB.connect();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
		) {
			while (rs.next()) {
				ItemCaixa p = new ItemCaixa();
				p.setId(rs.getInt("id"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setProduto(ProdutoDAO.get().select(rs.getInt("idProduto")).get(0));
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}
	
}
