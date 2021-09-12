package save.time.test;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import save.time.entity.Leituras;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Contador;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class TesteView {
	
	private static JFrame loginFrame;
	private static JTabbedPane tabbedPane;
	private static JPanel panel;
	private static JPanel panel_1;
	private static JPanel panel_2;
	private static JPanel panel_3;
	private static JScrollPane scrollPane;
	private static JTable table;
	private static JLabel lblTime;
	private static JLabel lblBemVindo;
	private static JPanel panelCronometro;
	private static JPanel panelForm;
	private static JPanel panelFinalizar;
	private JButton btnFinalizarTudo;
	private JButton btnPausar;
	private JButton btnIniciar;
	private JButton btnFinalizarCronometro;
	private static JRadioButton rdbtnNovoLivro;
	private static JRadioButton rdbtnLivroExistente;
	private static JLabel lblTotalPaginas;
	private static JLabel lblNomeLivro;
	private static JLabel lblPaginaAtualForm;
	private static JComboBox comboMeusLivros;
	private static boolean contador = false;
	private static boolean zerado = false;
	private JTextField txtNomeLivro;
	private JTextField txtTotalPaginas;
	private JSeparator separator_1;
	private JLabel lblPaginaAtualFinalizar;
	private JTextField txtPaginaAtualFinalizar;
	private JTextField txtTotalPaginasFinalizar;
	private JTextField txtNomeLivroFinalizar;
	private JLabel lblTempoDedicado;
	private JTextField txtTempoDedicado;
	private JButton btnEditarLivro;
	private JButton btnProximo;
	private JLabel lblMeusLivros;
	private JTextField txtPaginaAtualForm;
	private JLabel lblMensagem;
	private Leituras objLeituras = new Leituras();
	private JLabel lblMensagemFinalizar;

	public TesteView() {
		initialize();
		Contador.getThread().start();
	}

	private void initialize() {
		
		loginFrame = new JFrame();
		loginFrame.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
		loginFrame.getContentPane().setFocusTraversalKeysEnabled(false);
		loginFrame.setTitle("Pagina Inicial");
		loginFrame.setSize(new Dimension(1372, 735));
		loginFrame.setDefaultCloseOperation(3);
		loginFrame.setLocation(-3, -1);
		loginFrame.getContentPane().setLayout((LayoutManager) null);
		loginFrame.getContentPane().setBackground(new Color(255, 255, 102));
		
		tabbedPane = new JTabbedPane(1);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBounds(0, 0, 1366, 706);
		loginFrame.getContentPane().add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Exercícios", (Icon) null, panel_1, (String) null);
		panel_1.setLayout((LayoutManager) null);
		
		ApoioRedimensionarImagem resize = new ApoioRedimensionarImagem();
		lblBemVindo = new JLabel("");
		ImageIcon imagem = new ImageIcon(getClass().getResource("/img/logocolorido.png"));
		imagem = resize.scaleImage(imagem, 200, 200);
		lblBemVindo.setIcon(imagem);
		lblBemVindo.setHorizontalAlignment(0);
		lblBemVindo.setForeground(Color.ORANGE);
		lblBemVindo.setFont(new Font("Tahoma", 0, 18));
		lblBemVindo.setBounds(46, 177, 257, 265);
		panel_1.add(lblBemVindo);
		
		JLabel lblEuSouA = new JLabel("Eu sou a Save Time, sua nova plataforma organizacional.");
		lblEuSouA.setHorizontalAlignment(0);
		lblEuSouA.setForeground(Color.ORANGE);
		lblEuSouA.setFont(new Font("Tahoma", 0, 18));
		lblEuSouA.setBounds(449, 296, 463, 62);
		panel_1.add(lblEuSouA);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Cursos", (Icon) null, panel_2, (String) null);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(1, null, null, null, null));
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Linguagens", (Icon) null, panel_3, (String) null);
		
		panel = new JPanel();
		panel.addComponentListener(new ComponentAdapter() {
			
			public void componentShown(ComponentEvent e) {
				System.out.println("funciona");
			}
		});
		
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Leitura", (Icon) null, panel, (String) null);
		tabbedPane.setEnabledAt(3, true);
		panel.setLayout((LayoutManager) null);
		panelFinalizar = new JPanel();
		panelFinalizar.setBounds(926, 92, 318, 253);
		panel.add(panelFinalizar);
		panelFinalizar.setLayout((LayoutManager) null);
		panelFinalizar.setBackground(Color.LIGHT_GRAY);
		panelFinalizar.setVisible(false);
		
		this.btnFinalizarTudo = new JButton("Finalizar");
		this.btnFinalizarTudo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!TesteView.this.txtPaginaAtualFinalizar.getText().matches("[0-9]")
						|| TesteView.this.txtPaginaAtualFinalizar.getText().equals("")) {
					TesteView.this.lblMensagemFinalizar.setText("*Verifique página atual");
					TesteView.this.lblMensagemFinalizar.setVisible(true);
				}
			}
		});
		
		this.btnFinalizarTudo.setBounds(187, 195, 86, 34);
		panelFinalizar.add(this.btnFinalizarTudo);
		JLabel lblNomeLivroFinalizar = new JLabel("Nome do Livro:");
		lblNomeLivroFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblNomeLivroFinalizar.setBounds(22, 11, 112, 14);
		
		panelFinalizar.add(lblNomeLivroFinalizar);
		JLabel lblTotalPaginasFinalizar = new JLabel("Total de Páginas:");
		lblTotalPaginasFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblTotalPaginasFinalizar.setBounds(22, 65, 112, 14);
		
		panelFinalizar.add(lblTotalPaginasFinalizar);
		this.lblPaginaAtualFinalizar = new JLabel("Página Atual:");
		this.lblPaginaAtualFinalizar.setFont(new Font("Tahoma", 0, 14));
		this.lblPaginaAtualFinalizar.setBounds(204, 65, 112, 14);
		
		panelFinalizar.add(this.lblPaginaAtualFinalizar);
		this.txtPaginaAtualFinalizar = new JTextField();
		this.txtPaginaAtualFinalizar.setColumns(10);
		this.txtPaginaAtualFinalizar.setBounds(204, 90, 86, 20);
		
		panelFinalizar.add(this.txtPaginaAtualFinalizar);
		this.txtTotalPaginasFinalizar = new JTextField();
		this.txtTotalPaginasFinalizar.setEditable(false);
		this.txtTotalPaginasFinalizar.setEnabled(false);
		this.txtTotalPaginasFinalizar.setColumns(10);
		this.txtTotalPaginasFinalizar.setBounds(22, 90, 86, 20);
		
		panelFinalizar.add(this.txtTotalPaginasFinalizar);
		this.txtNomeLivroFinalizar = new JTextField();
		this.txtNomeLivroFinalizar.setEditable(false);
		this.txtNomeLivroFinalizar.setEnabled(false);
		this.txtNomeLivroFinalizar.setColumns(10);
		this.txtNomeLivroFinalizar.setBounds(22, 36, 268, 20);
		
		panelFinalizar.add(this.txtNomeLivroFinalizar);
		this.lblTempoDedicado = new JLabel("Tempo Dedicado:");
		this.lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
		this.lblTempoDedicado.setBounds(103, 124, 112, 14);
		
		panelFinalizar.add(this.lblTempoDedicado);
		this.txtTempoDedicado = new JTextField();
		this.txtTempoDedicado.setEditable(false);
		this.txtTempoDedicado.setEnabled(false);
		this.txtTempoDedicado.setBounds(103, 149, 112, 20);
		
		panelFinalizar.add(this.txtTempoDedicado);
		this.txtTempoDedicado.setColumns(10);
		this.btnEditarLivro = new JButton("Alterar Livro");
		this.btnEditarLivro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				TesteView.this.lblMensagemFinalizar.setVisible(false);
				TesteView.panelFinalizar.setVisible(false);
				TesteView.panelForm.setVisible(true);
				TesteView.this.btnProximo.setText("Ajustar");
			}
		});
		
		this.btnEditarLivro.setBounds(45, 195, 106, 34);
		panelFinalizar.add(this.btnEditarLivro);
		
		this.lblMensagemFinalizar = new JLabel("Null");
		this.lblMensagemFinalizar.setForeground(Color.RED);
		this.lblMensagemFinalizar.setHorizontalAlignment(0);
		this.lblMensagemFinalizar.setBounds(39, 175, 240, 14);
		panelFinalizar.add(this.lblMensagemFinalizar);
		
		panelForm = new JPanel();
		panelForm.setBackground(Color.LIGHT_GRAY);
		panelForm.setBounds(926, 92, 318, 253);
		panel.add(panelForm);
		panelForm.setLayout((LayoutManager) null);
		
		comboMeusLivros = new JComboBox();
		comboMeusLivros.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				JOptionPane.showMessageDialog(TesteView.comboMeusLivros, "Click funciona");
			}
		});
		
		comboMeusLivros.setVisible(false);
		comboMeusLivros.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
		comboMeusLivros.addItem("Alice no Pa\uFFFDs das Maravilhas");
		comboMeusLivros.setBounds(10, 59, 200, 20);
		panelForm.add(comboMeusLivros);
		
		this.txtNomeLivro = new JTextField();
		this.txtNomeLivro.setBounds(10, 59, 298, 21);
		panelForm.add(this.txtNomeLivro);
		this.txtNomeLivro.setColumns(10);
		
		lblNomeLivro = new JLabel("Nome do Livro:");
		lblNomeLivro.setFont(new Font("Tahoma", 0, 14));
		lblNomeLivro.setBounds(10, 28, 121, 34);
		panelForm.add(lblNomeLivro);
		
		lblTotalPaginas = new JLabel("Total de Páginas:");
		lblTotalPaginas.setFont(new Font("Tahoma", 0, 14));
		lblTotalPaginas.setBounds(10, 89, 113, 21);
		panelForm.add(lblTotalPaginas);
		this.txtTotalPaginas = new JTextField();
		this.txtTotalPaginas.setBounds(10, 112, 60, 21);
		panelForm.add(this.txtTotalPaginas);
		this.txtTotalPaginas.setColumns(10);
		rdbtnLivroExistente = new JRadioButton("Livro Existente");
		rdbtnLivroExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TesteView.rdbtnLivroExistente.isSelected() && TesteView.rdbtnNovoLivro.isSelected()) {
					TesteView.this.habilitaLivroExistente();
				} else {
					TesteView.rdbtnLivroExistente.setSelected(true);
				}
			}
		});
		rdbtnLivroExistente.setBackground(Color.LIGHT_GRAY);
		rdbtnLivroExistente.setBounds(175, 4, 109, 23);
		panelForm.add(rdbtnLivroExistente);
		rdbtnNovoLivro = new JRadioButton("Novo Livro");
		rdbtnNovoLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TesteView.rdbtnNovoLivro.isSelected() && TesteView.rdbtnLivroExistente.isSelected()) {
					TesteView.this.habilitaNovoLivro();
				} else {
					TesteView.rdbtnNovoLivro.setSelected(true);
				}
			}
		});
		rdbtnNovoLivro.setBackground(Color.LIGHT_GRAY);
		rdbtnNovoLivro.setSelected(true);
		rdbtnNovoLivro.setBounds(33, 4, 109, 23);
		panelForm.add(rdbtnNovoLivro);
		JSeparator separator = new JSeparator();
		separator.setBounds(66, 43, 1, 2);
		panelForm.add(separator);
		this.separator_1 = new JSeparator();
		this.separator_1.setBorder(new BevelBorder(0, null, null, null, null));
		this.separator_1.setBounds(10, 28, 298, 2);
		panelForm.add(this.separator_1);
		this.btnProximo = new JButton("Próximo");
		this.btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TesteView.this.txtNomeLivro.getText().equals("")
						&& TesteView.comboMeusLivros.getSelectedItem().equals("Selecione...")) {
					TesteView.this.lblMensagem.setText("*Informe o Livro");
					TesteView.this.lblMensagem.setVisible(true);
				} else if (!TesteView.this.txtTotalPaginas.getText().matches("[0-9]*")
						|| TesteView.this.txtTotalPaginas.getText().equals("")) {
					System.out.println(TesteView.this.txtTotalPaginas.getText());
					TesteView.this.lblMensagem.setText("*Verifique o total de páginas");
					TesteView.this.lblMensagem.setVisible(true);
				} else {
					TesteView.panelForm.setVisible(false);
					TesteView.this.carregaForm();
					if (e.getActionCommand().equals("Próximo")) {
						TesteView.panelCronometro.setVisible(true);
					} else {
						TesteView.this.lblMensagem.setVisible(false);
						TesteView.panelFinalizar.setVisible(true);
						TesteView.this.carregaDadosFinalizar();
					}
				}
			}
		});
		this.btnProximo.setBounds(114, 196, 89, 29);
		panelForm.add(this.btnProximo);
		this.lblMeusLivros = new JLabel("Meus Livros:");
		this.lblMeusLivros.setVisible(false);
		this.lblMeusLivros.setBounds(10, 38, 76, 14);
		panelForm.add(this.lblMeusLivros);
		this.lblMeusLivros.setFont(new Font("Tahoma", 0, 14));
		lblPaginaAtualForm = new JLabel("Página Atual:");
		lblPaginaAtualForm.setVisible(false);
		lblPaginaAtualForm.setFont(new Font("Tahoma", 0, 14));
		lblPaginaAtualForm.setBounds(211, 89, 113, 21);
		panelForm.add(lblPaginaAtualForm);
		this.txtPaginaAtualForm = new JTextField();
		this.txtPaginaAtualForm.setVisible(false);
		this.txtPaginaAtualForm.setColumns(10);
		this.txtPaginaAtualForm.setBounds(211, 112, 60, 21);
		this.txtPaginaAtualForm.setEnabled(false);
		panelForm.add(this.txtPaginaAtualForm);
		this.lblMensagem = new JLabel("Null");
		this.lblMensagem.setForeground(Color.RED);
		this.lblMensagem.setHorizontalAlignment(0);
		this.lblMensagem.setAlignmentX(0.5F);
		this.lblMensagem.setBounds(41, 171, 235, 14);
		this.lblMensagem.setVisible(false);
		panelForm.add(this.lblMensagem);
		panelCronometro = new JPanel();
		panelCronometro.setBounds(926, 92, 318, 253);
		panel.add(panelCronometro);
		panelCronometro.setBackground(Color.LIGHT_GRAY);
		panelCronometro.setLayout((LayoutManager) null);
		panelCronometro.setVisible(false);
		JLabel lblNewLabel_1 = new JLabel("Contador");
		lblNewLabel_1.setBounds(106, 42, 105, 27);
		panelCronometro.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", 0, 24));
		lblNewLabel_1.setHorizontalAlignment(0);
		lblTime = new JLabel("00:00:00");
		lblTime.setBounds(82, 102, 154, 40);
		panelCronometro.add(lblTime);
		lblTime.setFont(new Font("Tahoma", 0, 22));
		lblTime.setHorizontalAlignment(0);
		this.btnIniciar = new JButton("Iniciar");
		this.btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TesteView.this.iniciarActionPerformed(e);
			}
		});
		this.btnIniciar.setFont(new Font("Tahoma", 0, 12));
		this.btnIniciar.setBounds(11, 179, 91, 40);
		panelCronometro.add(this.btnIniciar);
		this.btnPausar = new JButton("Pausar");
		this.btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Pausar")) {
					TesteView.this.pausarActionPerformed(e);
				} else {
					TesteView.this.continuarActionPerformed(e);
				}
			}
		});
		this.btnPausar.setFont(new Font("Tahoma", 0, 12));
		this.btnPausar.setBounds(113, 179, 91, 40);
		panelCronometro.add(this.btnPausar);
		this.btnFinalizarCronometro = new JButton("Finalizar");
		this.btnFinalizarCronometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TesteView.this.zerarActionPerformed(e);
				TesteView.panelCronometro.setVisible(false);
				TesteView.panelFinalizar.setVisible(true);
				TesteView.this.objLeituras.setTempoGasto(TesteView.this.formataTime());
				TesteView.this.carregaDadosFinalizar();
			}
		});
		this.btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
		this.btnFinalizarCronometro.setBounds(215, 179, 91, 40);
		panelCronometro.add(this.btnFinalizarCronometro);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 50, 773, 567);
		scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
		scrollPane.setBackground(Color.DARK_GRAY);
		panel.add(scrollPane);
		table = new JTable();
		table.setRowHeight(20);
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(1));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, "teste" }, new Object[6], new Object[6] },
						(Object[]) new String[] { "Livro", "Total de Páginas", "Página Atual", "Data",
								"Tempo Gasto", "Conclusão" }) {
					Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class,
							String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return this.columnTypes[columnIndex];
					}
				});
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		loginFrame.setVisible(true);
	}

	public static JFrame getLoginFrame() {
		return loginFrame;
	}

	public static void setLoginFrame(JFrame loginFrame) {
		TesteView.loginFrame = loginFrame;
	}

	public static JLabel getLblTime() {
		return lblTime;
	}

	public static void setLblTime(JLabel lblTime) {
		TesteView.lblTime = lblTime;
	}

	private void iniciarActionPerformed(ActionEvent evt) {
		contador = true;
		this.btnIniciar.setEnabled(false);
		this.btnPausar.setEnabled(true);
	}

	private void pausarActionPerformed(ActionEvent e) {
		contador = false;
		this.btnPausar.setText("Continuar");
		this.btnPausar.revalidate();
	}

	private void continuarActionPerformed(ActionEvent e) {
		contador = true;
		this.btnPausar.setText("Pausar");
		this.btnPausar.revalidate();
	}

	private void zerarActionPerformed(ActionEvent e) {
		contador = false;
		lblTime.setText("00:00:00");
		zerado = true;
		lblTime.revalidate();
		this.btnIniciar.setEnabled(true);
		this.btnPausar.setEnabled(false);
	}

	public static boolean isContador() {
		return contador;
	}

	public static boolean isZerado() {
		return zerado;
	}

	public static void setZerado(boolean zero) {
		zerado = zero;
	}

	public void habilitaLivroExistente() {
		this.lblMensagem.setVisible(false);
		rdbtnNovoLivro.setSelected(false);
		lblNomeLivro.setVisible(false);
		this.txtNomeLivro.setVisible(false);
		this.txtTotalPaginas.setEnabled(false);
		this.txtNomeLivro.setText("");
		this.txtTotalPaginas.setText("");
		comboMeusLivros.setVisible(true);
		this.txtPaginaAtualForm.setVisible(true);
		lblTotalPaginas.setVisible(true);
		lblPaginaAtualForm.setVisible(true);
		this.lblMeusLivros.setVisible(true);
	}

	public void habilitaNovoLivro() {
		rdbtnLivroExistente.setSelected(false);
		lblNomeLivro.setVisible(true);
		this.txtNomeLivro.setVisible(true);
		this.lblMensagem.setVisible(false);
		this.txtTotalPaginas.setEnabled(true);
		comboMeusLivros.setVisible(false);
		this.lblMeusLivros.setVisible(false);
		comboMeusLivros.setSelectedIndex(0);
		this.txtPaginaAtualForm.setVisible(false);
		lblPaginaAtualForm.setVisible(false);
	}

	public void carregaForm() {
		if (!this.txtNomeLivro.getText().equals("")) {
			this.objLeituras.setNomeCurso(this.txtNomeLivro.getText());
		} else {
			this.objLeituras.setNomeCurso(comboMeusLivros.getSelectedItem().toString());
		}
		Calendar cal = DatasUtil.getCalendarDate();
		Date sqlDate = new Date(cal.getTimeInMillis());
		this.objLeituras.setData(sqlDate);
		this.objLeituras.setPgTotal(Integer.parseInt(this.txtTotalPaginas.getText()));
		this.objLeituras.setUsuario(UserSession.getUser());
	}

	public String formataTime() {
		String[] formataTime = lblTime.getText().split(":");
		String[] type = { "s", "m", "H" };
		String timeFormatado = "";
		for (int i = formataTime.length - 1; i >= 0; i--) {
			if (formataTime[i].substring(0) != "0" && formataTime[i].substring(1) != "0")
				if (formataTime[i].substring(0) == "0") {
					timeFormatado = String.valueOf(timeFormatado) + formataTime[i].substring(1) + type[i] + " ";
				} else {
					timeFormatado = String.valueOf(timeFormatado) + formataTime[i] + type[i] + " ";
				}
		}
		return timeFormatado.substring(0, timeFormatado.length() - 1);
	}

	public void carregaDadosFinalizar() {
		this.txtNomeLivroFinalizar.setText(this.objLeituras.getNomeLivro());
		this.txtTotalPaginasFinalizar.setText(this.objLeituras.getPgTotal() + "");
		this.txtTempoDedicado.setText(this.objLeituras.getTempoGasto());
		this.lblMensagemFinalizar.setVisible(false);
	}
}
