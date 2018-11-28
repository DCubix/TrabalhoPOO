package com.diego.xlanches.forms;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer implements TableCellRenderer {

	private TableCellRenderer __defaultRenderer;

	public ButtonRenderer(TableCellRenderer renderer) {
		__defaultRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected,
			boolean hasFocus,
			int row, int column) {
		if (value instanceof Component) {
			return (Component) value;
		}
		return __defaultRenderer.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
	}
}
