package save.time.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import save.time.controller.LeiturasPanelController;
import save.time.entity.Leituras;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Cronometro;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class LeiturasPanel {

	private static JTable table;

	private static JScrollPane scrollPane;

	private static JPanel panelForm;

	private static JPanel panelFinalizar;

	private static JPanel panelCronometro;

	private static JButton btnAlterarLivro;

	private static JButton btnProximo;

	private static JButton btnFinalizarTudo;

	private static JButton btnPausar;

	private static JButton btnIniciar;

	private static JButton btnFinalizarCronometro;

	private static JRadioButton rdbtnNovoLivro;

	private static JRadioButton rdbtnLivroExistente;

	private static JLabel lblTotalPaginas;

	private static JLabel lblNomeLivro;

	private static JLabel lblPaginaAtualForm;

	private static JLabel lblPaginaAtualFinalizar;

	private static JLabel lblTempoDedicado;

	private static JLabel lblMeusLivros;

	private static JLabel lblMensagem;

	private static JLabel lblMensagemFinalizar;

	private static JLabel lblTime;

	private static JTextField txtNomeLivro;

	private static JTextField txtTotalPaginasForm;

	private static JTextField txtPaginaAtualFinalizar;

	private static JTextField txtTotalPaginasFinalizar;

	private static JTextField txtNomeLivroFinalizar;

	private static JTextField txtTempoDedicado;

	private static JTextField txtPaginaAtualForm;

	private static JComboBox comboMeusLivros;

	private static JSeparator separator_1;

	private static Leituras objLeituras = new Leituras();

	private static boolean controle = false;

	public static void carregarLeituras() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(42, 59, 773, 564);
		HomeView.getPanelLeituras().add(scrollPane);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(LeiturasPanelController.carregaTable(), (Object[]) new String[] { "Data",
				"Tempo Dedicado", "Livro", "Total de P\u00E1ginas", "P\u00E1gina Atual", "Conclus\u00E3o" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		scrollPane.setViewportView(table);
		panelCronometro = new JPanel();
		panelCronometro.setBounds(926, 92, 318, 253);
		HomeView.getPanelLeituras().add(panelCronometro);
		panelCronometro.setBackground(Color.LIGHT_GRAY);
		panelCronometro.setLayout((LayoutManager) null);
		panelCronometro.setVisible(false);
		JLabel lblContador = new JLabel("");
		lblContador.setBounds(115, 34, 80, 80);
		panelCronometro.add(lblContador);
		lblContador.setFont(new Font("Tahoma", 0, 24));
		lblContador.setHorizontalAlignment(0);
		ApoioRedimensionarImagem resize = new ApoioRedimensionarImagem();
		ImageIcon imagem = new ImageIcon(HomeView.class.getResource("/img/contador.png"));
		imagem = resize.scaleImage(imagem, 80, 80);
		lblContador.setIcon(imagem);
		lblTime = new JLabel("00:00:00");
		lblTime.setBounds(82, 129, 154, 40);
		panelCronometro.add(lblTime);
		lblTime.setFont(new Font("Tahoma", 0, 22));
		lblTime.setHorizontalAlignment(0);
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeiturasPanel.iniciarActionPerformed(e);
				LeiturasPanel.controle = true;
			}
		});
		btnIniciar.setFont(new Font("Tahoma", 0, 12));
		btnIniciar.setBounds(24, 190, 75, 30);
		panelCronometro.add(btnIniciar);
		btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Pausar")) {
					LeiturasPanel.pausarActionPerformed(e);
				} else {
					LeiturasPanel.continuarActionPerformed(e);
				}
			}
		});
		btnPausar.setFont(new Font("Tahoma", 0, 12));
		btnPausar.setBounds(121, 190, 75, 30);
		panelCronometro.add(btnPausar);
		btnFinalizarCronometro = new JButton("Finalizar");
		btnFinalizarCronometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeiturasPanel.txtPaginaAtualFinalizar.setText(LeiturasPanel.txtPaginaAtualForm.getText());
				LeiturasPanel.panelCronometro.setVisible(false);
				LeiturasPanel.panelFinalizar.setVisible(true);
				LeiturasPanel.objLeituras.setTempoGasto(LeiturasPanel.formataTime());
				LeiturasPanel.carregaDadosFinalizar();
				LeiturasPanel.finalizarActionPerformed();
			}
		});
		btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
		btnFinalizarCronometro.setBounds(217, 190, 75, 30);
		btnFinalizarCronometro.setEnabled(false);
		panelCronometro.add(btnFinalizarCronometro);
		panelFinalizar = new JPanel();
		panelFinalizar.setBounds(926, 92, 318, 253);
		HomeView.getPanelLeituras().add(panelFinalizar);
		panelFinalizar.setLayout((LayoutManager) null);
		panelFinalizar.setBackground(Color.LIGHT_GRAY);
		panelFinalizar.setVisible(false);
		btnFinalizarTudo = new JButton("Finalizar");
		btnFinalizarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!LeiturasPanel.txtPaginaAtualFinalizar.getText().matches("[0-9]*")
						|| LeiturasPanel.txtPaginaAtualFinalizar.getText().equals("")) {
					LeiturasPanel.lblMensagemFinalizar.setText("*Verifique p\u00E1gina atual");
					LeiturasPanel.lblMensagemFinalizar.setVisible(true);
				} else if (Integer.parseInt(LeiturasPanel.txtTotalPaginasFinalizar.getText()) < Integer
						.parseInt(LeiturasPanel.txtPaginaAtualFinalizar.getText())) {
					LeiturasPanel.lblMensagemFinalizar.setText("*Verifique p\u00E1gina atual");
					LeiturasPanel.lblMensagemFinalizar.setVisible(true);
				} else {
					Leituras livro = LeiturasPanelController.verificaPaginaMaisRecente(UserSession.getUser().getId(),
							LeiturasPanel.txtNomeLivroFinalizar.getText());
					if (livro.getPgAtual() >= Integer.parseInt(LeiturasPanel.txtPaginaAtualFinalizar.getText())) {
						LeiturasPanel.lblMensagemFinalizar.setText("*Verifique p\u00E1gina atual");
						LeiturasPanel.lblMensagemFinalizar.setVisible(true);
					} else {
						LeiturasPanel.objLeituras
								.setPgAtual(Integer.parseInt(LeiturasPanel.txtPaginaAtualFinalizar.getText()));
						LeiturasPanelController.gravaLeitura();
						LeiturasPanel.recarregaTable();
					}
				}
				LeiturasPanel.controle = false;
			}
		});
		btnFinalizarTudo.setBounds(187, 195, 86, 34);
		panelFinalizar.add(btnFinalizarTudo);
		JLabel lblNomeLivroFinalizar = new JLabel("Nome do Livro:");
		lblNomeLivroFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblNomeLivroFinalizar.setBounds(22, 20, 112, 20);
		panelFinalizar.add(lblNomeLivroFinalizar);
		JLabel lblTotalPaginasFinalizar = new JLabel("Total de P\u00E1ginas:");
		lblTotalPaginasFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblTotalPaginasFinalizar.setBounds(22, 75, 112, 20);
		panelFinalizar.add(lblTotalPaginasFinalizar);
		lblPaginaAtualFinalizar = new JLabel("P\u00E1gina Atual:");
		lblPaginaAtualFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblPaginaAtualFinalizar.setBounds(204, 75, 112, 20);
		panelFinalizar.add(lblPaginaAtualFinalizar);
		txtPaginaAtualFinalizar = new JTextField();
		txtPaginaAtualFinalizar.setColumns(10);
		txtPaginaAtualFinalizar.setBounds(204, 100, 86, 20);
		panelFinalizar.add(txtPaginaAtualFinalizar);
		txtTotalPaginasFinalizar = new JTextField();
		txtTotalPaginasFinalizar.setEditable(false);
		txtTotalPaginasFinalizar.setColumns(10);
		txtTotalPaginasFinalizar.setBounds(22, 100, 86, 20);
		panelFinalizar.add(txtTotalPaginasFinalizar);
		txtNomeLivroFinalizar = new JTextField();
		txtNomeLivroFinalizar.setEditable(false);
		txtNomeLivroFinalizar.setColumns(10);
		txtNomeLivroFinalizar.setBounds(22, 44, 268, 20);
		panelFinalizar.add(txtNomeLivroFinalizar);
		lblTempoDedicado = new JLabel("Tempo Dedicado:");
		lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
		lblTempoDedicado.setBounds(103, 130, 112, 20);
		panelFinalizar.add(lblTempoDedicado);
		txtTempoDedicado = new JTextField();
		txtTempoDedicado.setEditable(false);
		txtTempoDedicado.setBounds(103, 155, 112, 20);
		panelFinalizar.add(txtTempoDedicado);
		txtTempoDedicado.setColumns(10);
		btnAlterarLivro = new JButton("Alterar Livro");
		btnAlterarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeiturasPanel.lblMensagemFinalizar.setVisible(false);
				LeiturasPanel.panelFinalizar.setVisible(false);
				LeiturasPanel.panelForm.setVisible(true);
				LeiturasPanel.btnProximo.setText("Ajustar");
			}
		});
		btnAlterarLivro.setBounds(45, 195, 106, 34);
		panelFinalizar.add(btnAlterarLivro);
		lblMensagemFinalizar = new JLabel("Null");
		lblMensagemFinalizar.setForeground(Color.RED);
		lblMensagemFinalizar.setHorizontalAlignment(0);
		lblMensagemFinalizar.setBounds(39, 175, 240, 14);
		panelFinalizar.add(lblMensagemFinalizar);
		panelForm = new JPanel();
		panelForm.setBackground(Color.LIGHT_GRAY);
		panelForm.setBounds(926, 92, 318, 253);
		HomeView.getPanelLeituras().add(panelForm);
		panelForm.setLayout((LayoutManager) null);
		comboMeusLivros = new JComboBox();
		comboMeusLivros.setVisible(false);
		comboMeusLivros.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
		comboMeusLivros.setBounds(22, 82, 200, 20);
		comboMeusLivros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Selecione...")) {
					LeiturasPanel.txtTotalPaginasForm.setText("");
					LeiturasPanel.txtPaginaAtualForm.setText("");
				} else {
					Leituras livro = LeiturasPanelController.verificaPaginaMaisRecente(UserSession.getUser().getId(),
							e.getItem().toString());
					LeiturasPanel.txtTotalPaginasForm.setText(livro.getPgTotal() + "");
					LeiturasPanel.txtPaginaAtualForm.setText(livro.getPgAtual() + "");
				}
			}
		});
		panelForm.add(comboMeusLivros);
		txtNomeLivro = new JTextField();
		txtNomeLivro.setBounds(22, 82, 268, 20);
		panelForm.add(txtNomeLivro);
		txtNomeLivro.setColumns(10);
		lblNomeLivro = new JLabel("Nome do Livro:");
		lblNomeLivro.setFont(new Font("Tahoma", 0, 14));
		lblNomeLivro.setBounds(22, 58, 121, 20);
		panelForm.add(lblNomeLivro);
		lblTotalPaginas = new JLabel("Total de P\u00E1ginas:");
		lblTotalPaginas.setFont(new Font("Tahoma", 0, 14));
		lblTotalPaginas.setBounds(22, 115, 113, 20);
		panelForm.add(lblTotalPaginas);
		txtTotalPaginasForm = new JTextField();
		txtTotalPaginasForm.setBounds(22, 140, 60, 20);
		panelForm.add(txtTotalPaginasForm);
		txtTotalPaginasForm.setColumns(10);
		rdbtnLivroExistente = new JRadioButton("Livro Existente");
		rdbtnLivroExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LeiturasPanel.rdbtnLivroExistente.isSelected() && LeiturasPanel.rdbtnNovoLivro.isSelected()) {
					LeiturasPanel.habilitaLivroExistente();
				} else {
					LeiturasPanel.rdbtnLivroExistente.setSelected(true);
				}
			}
		});
		rdbtnLivroExistente.setBackground(Color.LIGHT_GRAY);
		rdbtnLivroExistente.setBounds(157, 9, 109, 23);
		panelForm.add(rdbtnLivroExistente);
		rdbtnNovoLivro = new JRadioButton("Novo Livro");
		rdbtnNovoLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LeiturasPanel.rdbtnNovoLivro.isSelected() && LeiturasPanel.rdbtnLivroExistente.isSelected()) {
					LeiturasPanel.habilitaNovoLivro();
				} else {
					LeiturasPanel.rdbtnNovoLivro.setSelected(true);
				}
			}
		});
		rdbtnNovoLivro.setBackground(Color.LIGHT_GRAY);
		rdbtnNovoLivro.setSelected(true);
		rdbtnNovoLivro.setBounds(33, 9, 109, 23);
		panelForm.add(rdbtnNovoLivro);
		btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeiturasPanel.actionProximo(e);
			}
		});
		btnProximo.setBounds(114, 196, 89, 29);
		panelForm.add(btnProximo);
		lblMeusLivros = new JLabel("Meus Livros:");
		lblMeusLivros.setVisible(false);
		lblMeusLivros.setBounds(22, 58, 121, 14);
		panelForm.add(lblMeusLivros);
		lblMeusLivros.setFont(new Font("Tahoma", 0, 14));
		lblPaginaAtualForm = new JLabel("P\u00E1gina Atual:");
		lblPaginaAtualForm.setVisible(false);
		lblPaginaAtualForm.setFont(new Font("Tahoma", 0, 14));
		lblPaginaAtualForm.setBounds(211, 109, 113, 21);
		panelForm.add(lblPaginaAtualForm);
		txtPaginaAtualForm = new JTextField();
		txtPaginaAtualForm.setVisible(false);
		txtPaginaAtualForm.setColumns(10);
		txtPaginaAtualForm.setBounds(211, 133, 60, 21);
		txtPaginaAtualForm.setEditable(false);
		panelForm.add(txtPaginaAtualForm);
		lblMensagem = new JLabel("Null");
		lblMensagem.setForeground(Color.RED);
		lblMensagem.setHorizontalAlignment(0);
		lblMensagem.setAlignmentX(0.5F);
		lblMensagem.setBounds(41, 171, 235, 14);
		lblMensagem.setVisible(false);
		panelForm.add(lblMensagem);
		separator_1 = new JSeparator();
		separator_1.setBorder(new BevelBorder(0, null, null, null, null));
		separator_1.setBounds(10, 38, 298, 2);
		panelForm.add(separator_1);
	}

	public static void actionProximo(ActionEvent e) {
		if (txtNomeLivro.getText().equals("") && comboMeusLivros.getSelectedItem().equals("Selecione...")) {
			lblMensagem.setText("*Informe o Livro");
			lblMensagem.setVisible(true);
		} else if (!txtTotalPaginasForm.getText().matches("[0-9]*") || txtTotalPaginasForm.getText().equals("")) {
			System.out.println(txtTotalPaginasForm.getText());
			lblMensagem.setText("*Verifique o total de p\u00E1ginas");
			lblMensagem.setVisible(true);
		} else {
			String livro = LeiturasPanelController.verificaLivroString(UserSession.getUser().getId(),
					txtNomeLivro.getText());
			if (!livro.equals("")) {
				lblMensagem.setText("*Voc\u00EA j\u00E1 cadastrou esse livro!");
				lblMensagem.setVisible(true);
			} else {
				boolean livroFinalizado = false;
				if (!txtPaginaAtualForm.getText().equals("") && !txtTotalPaginasForm.getText().equals("") && Integer
						.parseInt(txtPaginaAtualForm.getText()) == Integer.parseInt(txtTotalPaginasForm.getText()))
					livroFinalizado = true;
				if (livroFinalizado) {
					lblMensagem.setText("*Esse livro j\u00E1 foi finalizado!");
					lblMensagem.setVisible(true);
				} else {
					panelForm.setVisible(false);
					carregaForm();
					if (e.getActionCommand().equals("Pr\u00F3ximo")) {
						panelCronometro.setVisible(true);
					} else {
						lblMensagem.setVisible(false);
						panelFinalizar.setVisible(true);
						carregaDadosFinalizar();
					}
				}
			}
		}
	}

	private static void iniciarActionPerformed(ActionEvent evt) {
		Cronometro.iniciar();
		btnIniciar.setEnabled(false);
		btnPausar.setEnabled(true);
		btnFinalizarCronometro.setEnabled(true);
	}

	private static void pausarActionPerformed(ActionEvent e) {
		Cronometro.pausar();
		btnPausar.setText("Continuar");
		btnPausar.revalidate();
	}

	private static void continuarActionPerformed(ActionEvent e) {
		Cronometro.continuar();
		btnPausar.setText("Pausar");
		btnPausar.revalidate();
	}

	public static void finalizarActionPerformed() {
		Cronometro.finalizar();
		lblTime.setText("00:00:00");
		lblTime.revalidate();
		btnIniciar.setEnabled(true);
		btnPausar.setEnabled(false);
		btnFinalizarCronometro.setEnabled(false);
	}

	public static void habilitaLivroExistente() {
		comboMeusLivros.removeAllItems();
		comboMeusLivros.addItem("Selecione...");
		LeiturasPanelController.buscaLivros();
		lblMensagem.setVisible(false);
		rdbtnNovoLivro.setSelected(false);
		lblNomeLivro.setVisible(false);
		txtNomeLivro.setVisible(false);
		txtTotalPaginasForm.setEditable(false);
		txtNomeLivro.setText("");
		txtTotalPaginasForm.setText("");
		comboMeusLivros.setVisible(true);
		txtPaginaAtualForm.setVisible(true);
		lblTotalPaginas.setVisible(true);
		lblPaginaAtualForm.setVisible(true);
		lblMeusLivros.setVisible(true);
	}

	public static void habilitaNovoLivro() {
		rdbtnLivroExistente.setSelected(false);
		lblNomeLivro.setVisible(true);
		txtNomeLivro.setVisible(true);
		lblMensagem.setVisible(false);
		txtTotalPaginasForm.setEditable(true);
		comboMeusLivros.setVisible(false);
		lblMeusLivros.setVisible(false);
		comboMeusLivros.setSelectedIndex(0);
		txtPaginaAtualForm.setVisible(false);
		lblPaginaAtualForm.setVisible(false);
	}

	public static void carregaForm() {
		if (!txtNomeLivro.getText().equals("")) {
			objLeituras.setNomeCurso(txtNomeLivro.getText());
		} else {
			objLeituras.setNomeCurso(comboMeusLivros.getSelectedItem().toString());
		}
		Calendar cal = DatasUtil.getCalendarDate();
		Date sqlDate = new Date(cal.getTimeInMillis());
		objLeituras.setData(sqlDate);
		objLeituras.setPgTotal(Integer.parseInt(txtTotalPaginasForm.getText()));
		objLeituras.setUsuario(UserSession.getUser());
	}

	public static String formataTime() {
		String[] formataTime = lblTime.getText().split(":");
		String[] type = { "H", "m", "s" };
		String timeFormatado = "";
		for (int i = 0; i < formataTime.length; i++) {
			if (formataTime[i].substring(0) != "0" && formataTime[i].substring(1) != "0")
				if (formataTime[i].substring(0) == "0") {
					timeFormatado = String.valueOf(timeFormatado) + formataTime[i].substring(1) + type[i] + " ";
				} else {
					timeFormatado = String.valueOf(timeFormatado) + formataTime[i] + type[i] + " ";
				}
		}
		return timeFormatado.substring(0, timeFormatado.length() - 1);
	}

	public static void carregaDadosFinalizar() {
		txtNomeLivroFinalizar.setText(objLeituras.getNomeLivro());
		txtTotalPaginasFinalizar.setText(objLeituras.getPgTotal() + "");
		txtTempoDedicado.setText(objLeituras.getTempoGasto());
		lblMensagemFinalizar.setVisible(false);
	}

	public static void resetPanel() {
		objLeituras = new Leituras();
		panelCronometro.setVisible(false);
		panelFinalizar.setVisible(false);
		panelForm.setVisible(true);
		habilitaLivroExistente();
		habilitaNovoLivro();
		btnProximo.setText("Pr\u00F3ximo");
		rdbtnNovoLivro.setSelected(true);
		txtPaginaAtualFinalizar.setText("");
	}

	public static void recarregaTable() {
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(LeiturasPanelController.carregaTable(), (Object[]) new String[] { "Data",
				"Tempo Dedicado", "Livro", "Total de P\u00E1ginas", "P\u00E1gina Atual", "Conclus\u00E3o" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}
		});
		table.revalidate();
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		LeiturasPanel.table = table;
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		LeiturasPanel.scrollPane = scrollPane;
	}

	public static JPanel getPanelForm() {
		return panelForm;
	}

	public static void setPanelForm(JPanel panelForm) {
		LeiturasPanel.panelForm = panelForm;
	}

	public static JPanel getPanelFinalizar() {
		return panelFinalizar;
	}

	public static void setPanelFinalizar(JPanel panelFinalizar) {
		LeiturasPanel.panelFinalizar = panelFinalizar;
	}

	public static JPanel getPanelCronometro() {
		return panelCronometro;
	}

	public static void setPanelCronometro(JPanel panelCronometro) {
		LeiturasPanel.panelCronometro = panelCronometro;
	}

	public static JButton getBtnEditarLivro() {
		return btnAlterarLivro;
	}

	public static void setBtnEditarLivro(JButton btnEditarLivro) {
		btnAlterarLivro = btnEditarLivro;
	}

	public static JButton getBtnProximo() {
		return btnProximo;
	}

	public static void setBtnProximo(JButton btnProximo) {
		LeiturasPanel.btnProximo = btnProximo;
	}

	public static JButton getBtnFinalizarTudo() {
		return btnFinalizarTudo;
	}

	public static void setBtnFinalizarTudo(JButton btnFinalizarTudo) {
		LeiturasPanel.btnFinalizarTudo = btnFinalizarTudo;
	}

	public static JButton getBtnPausar() {
		return btnPausar;
	}

	public static void setBtnPausar(JButton btnPausar) {
		LeiturasPanel.btnPausar = btnPausar;
	}

	public static JButton getBtnIniciar() {
		return btnIniciar;
	}

	public static void setBtnIniciar(JButton btnIniciar) {
		LeiturasPanel.btnIniciar = btnIniciar;
	}

	public static JButton getBtnFinalizarCronometro() {
		return btnFinalizarCronometro;
	}

	public static void setBtnFinalizarCronometro(JButton btnFinalizarCronometro) {
		LeiturasPanel.btnFinalizarCronometro = btnFinalizarCronometro;
	}

	public static JRadioButton getRdbtnNovoLivro() {
		return rdbtnNovoLivro;
	}

	public static void setRdbtnNovoLivro(JRadioButton rdbtnNovoLivro) {
		LeiturasPanel.rdbtnNovoLivro = rdbtnNovoLivro;
	}

	public static JRadioButton getRdbtnLivroExistente() {
		return rdbtnLivroExistente;
	}

	public static void setRdbtnLivroExistente(JRadioButton rdbtnLivroExistente) {
		LeiturasPanel.rdbtnLivroExistente = rdbtnLivroExistente;
	}

	public static JLabel getLblTotalPaginas() {
		return lblTotalPaginas;
	}

	public static void setLblTotalPaginas(JLabel lblTotalPaginas) {
		LeiturasPanel.lblTotalPaginas = lblTotalPaginas;
	}

	public static JLabel getLblNomeLivro() {
		return lblNomeLivro;
	}

	public static void setLblNomeLivro(JLabel lblNomeLivro) {
		LeiturasPanel.lblNomeLivro = lblNomeLivro;
	}

	public static JLabel getLblPaginaAtualForm() {
		return lblPaginaAtualForm;
	}

	public static void setLblPaginaAtualForm(JLabel lblPaginaAtualForm) {
		LeiturasPanel.lblPaginaAtualForm = lblPaginaAtualForm;
	}

	public static JLabel getLblPaginaAtualFinalizar() {
		return lblPaginaAtualFinalizar;
	}

	public static void setLblPaginaAtualFinalizar(JLabel lblPaginaAtualFinalizar) {
		LeiturasPanel.lblPaginaAtualFinalizar = lblPaginaAtualFinalizar;
	}

	public static JLabel getLblTempoDedicado() {
		return lblTempoDedicado;
	}

	public static void setLblTempoDedicado(JLabel lblTempoDedicado) {
		LeiturasPanel.lblTempoDedicado = lblTempoDedicado;
	}

	public static JLabel getLblMeusLivros() {
		return lblMeusLivros;
	}

	public static void setLblMeusLivros(JLabel lblMeusLivros) {
		LeiturasPanel.lblMeusLivros = lblMeusLivros;
	}

	public static JLabel getLblMensagem() {
		return lblMensagem;
	}

	public static void setLblMensagem(JLabel lblMensagem) {
		LeiturasPanel.lblMensagem = lblMensagem;
	}

	public static JLabel getLblMensagemFinalizar() {
		return lblMensagemFinalizar;
	}

	public static void setLblMensagemFinalizar(JLabel lblMensagemFinalizar) {
		LeiturasPanel.lblMensagemFinalizar = lblMensagemFinalizar;
	}

	public static JTextField getTxtNomeLivro() {
		return txtNomeLivro;
	}

	public static void setTxtNomeLivro(JTextField txtNomeLivro) {
		LeiturasPanel.txtNomeLivro = txtNomeLivro;
	}

	public static JTextField getTxtTotalPaginas() {
		return txtTotalPaginasForm;
	}

	public static void setTxtTotalPaginas(JTextField txtTotalPaginas) {
		txtTotalPaginasForm = txtTotalPaginas;
	}

	public static JTextField getTxtPaginaAtualFinalizar() {
		return txtPaginaAtualFinalizar;
	}

	public static void setTxtPaginaAtualFinalizar(JTextField txtPaginaAtualFinalizar) {
		LeiturasPanel.txtPaginaAtualFinalizar = txtPaginaAtualFinalizar;
	}

	public static JTextField getTxtTotalPaginasFinalizar() {
		return txtTotalPaginasFinalizar;
	}

	public static void setTxtTotalPaginasFinalizar(JTextField txtTotalPaginasFinalizar) {
		LeiturasPanel.txtTotalPaginasFinalizar = txtTotalPaginasFinalizar;
	}

	public static JTextField getTxtNomeLivroFinalizar() {
		return txtNomeLivroFinalizar;
	}

	public static void setTxtNomeLivroFinalizar(JTextField txtNomeLivroFinalizar) {
		LeiturasPanel.txtNomeLivroFinalizar = txtNomeLivroFinalizar;
	}

	public static JTextField getTxtTempoDedicado() {
		return txtTempoDedicado;
	}

	public static void setTxtTempoDedicado(JTextField txtTempoDedicado) {
		LeiturasPanel.txtTempoDedicado = txtTempoDedicado;
	}

	public static JTextField getTxtPaginaAtualForm() {
		return txtPaginaAtualForm;
	}

	public static void setTxtPaginaAtualForm(JTextField txtPaginaAtualForm) {
		LeiturasPanel.txtPaginaAtualForm = txtPaginaAtualForm;
	}

	public static JComboBox getComboMeusLivros() {
		return comboMeusLivros;
	}

	public static void setComboMeusLivros(JComboBox comboMeusLivros) {
		LeiturasPanel.comboMeusLivros = comboMeusLivros;
	}

	public static JSeparator getSeparator_1() {
		return separator_1;
	}

	public static void setSeparator_1(JSeparator separator_1) {
		LeiturasPanel.separator_1 = separator_1;
	}

	public static Leituras getObjLeituras() {
		return objLeituras;
	}

	public static void setObjLeituras(Leituras objLeituras) {
		LeiturasPanel.objLeituras = objLeituras;
	}

	public static JLabel getLblTime() {
		return lblTime;
	}

	public static void setLblTime(JLabel lblTime) {
		LeiturasPanel.lblTime = lblTime;
	}

	public static JTextField getTxtTotalPaginasForm() {
		return txtTotalPaginasForm;
	}

	public static void setTxtTotalPaginasForm(JTextField txtTotalPaginasForm) {
		LeiturasPanel.txtTotalPaginasForm = txtTotalPaginasForm;
	}

	public static boolean isControle() {
		return controle;
	}

	public static void setControle(boolean controle) {
		LeiturasPanel.controle = controle;
	}
}
