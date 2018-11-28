package com.diego.xlanches.data;

import com.diego.xlanches.forms.MainForm;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemCaixaTableModel extends AbstractTableModel {

	private ArrayList<ItemCaixa> data;

	public ItemCaixaTableModel(ArrayList<ItemCaixa> data) {
		this.data = data;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int column) {
		final String[] columns = new String[] {
			"#", "PRODUTO", "QTD.", "VALOR"
		};
		return columns[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemCaixa p = data.get(rowIndex);
		final Object[] values = new Object[] {
			(Integer)p.getId(),
			p.getProduto().getNome(),
			(Integer)p.getQuantidade(),
			NumberFormat.getCurrencyInstance(MainForm.ptBR).format(p.getProduto().getValor())
		};
		return values[columnIndex];
	}

	public ArrayList<ItemCaixa> getData() {
		return data;
	}
	
}
