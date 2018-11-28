package com.diego.xlanches.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.diego.xlanches.dao.ProdutoDAO;
import com.diego.xlanches.data.Produto;
import com.diego.xlanches.db.DB;

import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainFrame extends JFrame {
	public static final Locale ptBR = new Locale("pt", "BR");
	
	private JTextField tx_codprod;
	private JTable tb_caixa;
	private JTable table;
	private JTextField tx_nomeprod;
	private JTextField tx_proddesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("X-Lanches - Gestor de Lanchonetes");
		setBounds(100, 100, 527, 449);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
			}
		});
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pn_caixa = new JPanel();
		pn_caixa.setBorder(null);
		tabbedPane.addTab("Caixa", new ImageIcon(MainFrame.class.getResource("/com/diego/xlanches/res/cash-multiple.png")), pn_caixa, null);
		pn_caixa.setLayout(new BorderLayout(0, 0));
		
		JPanel pn_controles = new JPanel();
		pn_controles.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pn_caixa.add(pn_controles, BorderLayout.NORTH);
		GridBagLayout gbl_pn_controles = new GridBagLayout();
		gbl_pn_controles.columnWidths = new int[]{119, 397, 79, 0, 0};
		gbl_pn_controles.rowHeights = new int[]{0, 0, 0};
		gbl_pn_controles.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pn_controles.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pn_controles.setLayout(gbl_pn_controles);
		
		JLabel lblCdProduto = new JLabel("C\u00D3D. PRODUTO");
		GridBagConstraints gbc_lblCdProduto = new GridBagConstraints();
		gbc_lblCdProduto.anchor = GridBagConstraints.WEST;
		gbc_lblCdProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdProduto.gridx = 0;
		gbc_lblCdProduto.gridy = 0;
		pn_controles.add(lblCdProduto, gbc_lblCdProduto);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		GridBagConstraints gbc_lblProduto = new GridBagConstraints();
		gbc_lblProduto.anchor = GridBagConstraints.WEST;
		gbc_lblProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduto.gridx = 1;
		gbc_lblProduto.gridy = 0;
		pn_controles.add(lblProduto, gbc_lblProduto);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblQtd = new JLabel("QTD.");
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.anchor = GridBagConstraints.WEST;
		gbc_lblQtd.insets = new Insets(0, 0, 5, 5);
		gbc_lblQtd.gridx = 2;
		gbc_lblQtd.gridy = 0;
		pn_controles.add(lblQtd, gbc_lblQtd);
		
		tx_codprod = new JTextField();
		GridBagConstraints gbc_tx_codprod = new GridBagConstraints();
		gbc_tx_codprod.fill = GridBagConstraints.BOTH;
		gbc_tx_codprod.insets = new Insets(0, 0, 0, 5);
		gbc_tx_codprod.gridx = 0;
		gbc_tx_codprod.gridy = 1;
		pn_controles.add(tx_codprod, gbc_tx_codprod);
		tx_codprod.setEditable(false);
		tx_codprod.setColumns(10);
		
		JComboBox cb_produto = new JComboBox();
		GridBagConstraints gbc_cb_produto = new GridBagConstraints();
		gbc_cb_produto.fill = GridBagConstraints.BOTH;
		gbc_cb_produto.insets = new Insets(0, 0, 0, 5);
		gbc_cb_produto.gridx = 1;
		gbc_cb_produto.gridy = 1;
		pn_controles.add(cb_produto, gbc_cb_produto);
		
		JSpinner sp_qtd = new JSpinner();
		GridBagConstraints gbc_sp_qtd = new GridBagConstraints();
		gbc_sp_qtd.fill = GridBagConstraints.BOTH;
		gbc_sp_qtd.insets = new Insets(0, 0, 0, 5);
		gbc_sp_qtd.gridx = 2;
		gbc_sp_qtd.gridy = 1;
		pn_controles.add(sp_qtd, gbc_sp_qtd);
		sp_qtd.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		JButton bt_inserir = new JButton("+");
		bt_inserir.setToolTipText("Adicionar");
		GridBagConstraints gbc_bt_inserir = new GridBagConstraints();
		gbc_bt_inserir.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_inserir.gridx = 3;
		gbc_bt_inserir.gridy = 1;
		pn_controles.add(bt_inserir, gbc_bt_inserir);
		
		tb_caixa = new JTable();
		pn_caixa.add(tb_caixa, BorderLayout.CENTER);
		
		JPanel pn_footer = new JPanel();
		pn_footer.setBackground(Color.GRAY);
		pn_footer.setBorder(new EmptyBorder(8, 8, 8, 8));
		pn_caixa.add(pn_footer, BorderLayout.SOUTH);
		pn_footer.setLayout(new BorderLayout(6, 0));
		
		JPanel pn_tot = new JPanel();
		pn_tot.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pn_footer.add(pn_tot, BorderLayout.CENTER);
		pn_tot.setLayout(new BorderLayout(0, 0));
		
		JLabel tx_total = new JLabel("R$ 0,00");
		tx_total.setFont(new Font("Tahoma", Font.BOLD, 20));
		pn_tot.add(tx_total, BorderLayout.CENTER);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_tot.add(lblTotal, BorderLayout.NORTH);
		
		JButton btnFecharVenda = new JButton("FECHAR VENDA");
		pn_footer.add(btnFecharVenda, BorderLayout.EAST);
		bt_inserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel pn_produtos = new JPanel();
		tabbedPane.addTab("Produtos", new ImageIcon(MainFrame.class.getResource("/com/diego/xlanches/res/food.png")), pn_produtos, null);
		pn_produtos.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pn_produtos.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{397, 79, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 31, 0, 26, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		JLabel lblValor = new JLabel("VALOR");
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.anchor = GridBagConstraints.WEST;
		gbc_lblValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValor.gridx = 1;
		gbc_lblValor.gridy = 0;
		panel.add(lblValor, gbc_lblValor);
		
		tx_nomeprod = new JTextField();
		GridBagConstraints gbc_tx_nomeprod = new GridBagConstraints();
		gbc_tx_nomeprod.insets = new Insets(0, 0, 5, 5);
		gbc_tx_nomeprod.fill = GridBagConstraints.BOTH;
		gbc_tx_nomeprod.gridx = 0;
		gbc_tx_nomeprod.gridy = 1;
		panel.add(tx_nomeprod, gbc_tx_nomeprod);
		tx_nomeprod.setColumns(10);
		
		final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ptBR);
		currencyFormat.setMinimumFractionDigits(0);
		
		JFormattedTextField tx_valor = new JFormattedTextField(
				new DefaultFormatterFactory(
						new NumberFormatter(currencyFormat),
						new NumberFormatter(currencyFormat),
						new NumberFormatter(NumberFormat.getNumberInstance())
				)
		);
		tx_valor.setColumns(10);
		GridBagConstraints gbc_tx_valor = new GridBagConstraints();
		gbc_tx_valor.gridwidth = 2;
		gbc_tx_valor.insets = new Insets(0, 0, 5, 5);
		gbc_tx_valor.fill = GridBagConstraints.BOTH;
		gbc_tx_valor.gridx = 1;
		gbc_tx_valor.gridy = 1;
		panel.add(tx_valor, gbc_tx_valor);
		
		JLabel lblDescrio = new JLabel("DESCRI\u00C7\u00C3O");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescrio.anchor = GridBagConstraints.WEST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 2;
		panel.add(lblDescrio, gbc_lblDescrio);
		
		tx_proddesc = new JTextField();
		GridBagConstraints gbc_tx_proddesc = new GridBagConstraints();
		gbc_tx_proddesc.gridwidth = 2;
		gbc_tx_proddesc.insets = new Insets(0, 0, 0, 5);
		gbc_tx_proddesc.fill = GridBagConstraints.BOTH;
		gbc_tx_proddesc.gridx = 0;
		gbc_tx_proddesc.gridy = 3;
		panel.add(tx_proddesc, gbc_tx_proddesc);
		tx_proddesc.setColumns(10);
		
		JButton bt_add = new JButton("+");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		bt_add.setToolTipText("Adicionar");
		GridBagConstraints gbc_bt_add = new GridBagConstraints();
		gbc_bt_add.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_add.gridx = 2;
		gbc_bt_add.gridy = 3;
		panel.add(bt_add, gbc_bt_add);
		
		table = new JTable();
		pn_produtos.add(table, BorderLayout.CENTER);

	}

}
