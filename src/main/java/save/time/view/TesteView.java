package save.time.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TesteView {

	private static JFrame loginFrame;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;

	public TesteView() {
		initialize();
	}

	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
		loginFrame.getContentPane().setFocusTraversalKeysEnabled(false);
		loginFrame.setTitle("Pagina Inicial");
		loginFrame.setSize(new Dimension(1372, 735));
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLocation(-3, -1);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBounds(0, 0, 1366, 706);
		loginFrame.getContentPane().add(tabbedPane);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Leitura", null, panel, null);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 59, 773, 567);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBackground(Color.DARK_GRAY);
		panel.add(scrollPane);

		table = new JTable();
		table.setSelectionBackground(Color.WHITE);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY,
				Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Livro", "Total de P\u00E1ginas", "P\u00E1gina Atual", "Data", "Tempo Gasto",
						"Conclus\u00E3o" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		tabbedPane.setEnabledAt(0, true);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Exercícios", null, panel_1, null);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("Null");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(449, 249, 463, 62);
		panel_1.add(lblNewLabel);

		JLabel lblEuSouA = new JLabel("Eu sou a Save Time, sua nova plataforma organizacional.");
		lblEuSouA.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuSouA.setForeground(Color.ORANGE);
		lblEuSouA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEuSouA.setBounds(449, 296, 463, 62);
		panel_1.add(lblEuSouA);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Cursos", null, panel_2, null);

		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Linguagens", null, panel_3, null);
		loginFrame.setVisible(true);
	}

	public static JFrame getLoginFrame() {
		return loginFrame;
	}

	public static void setLoginFrame(JFrame loginFrame) {
		TesteView.loginFrame = loginFrame;
	}
}
