package com.diego.xlanches.data;

import com.diego.xlanches.forms.MainForm;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {

	private ArrayList<Produto> data;

	public ProdutoTableModel(ArrayList<Produto> data) {
		this.data = data;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		final String[] columns = new String[] {
			"#", "NOME", "VALOR"
		};
		return columns[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
	
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto p = data.get(rowIndex);
		final Object[] values = new Object[] {
			(Integer)p.getId(),
			p.getNome(),
			NumberFormat.getCurrencyInstance(MainForm.ptBR).format(p.getValor())
		};
		return values[columnIndex];
	}

	public ArrayList<Produto> getData() {
		return data;
	}
	
}
