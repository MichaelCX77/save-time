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
import save.time.controller.CursosPanelController;
import save.time.entity.Cursos;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Cronometro;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class CursosPanel {

	private static JTable table;

	private static JScrollPane scrollPane;

	private static JPanel panelForm;

	private static JPanel panelFinalizar;

	private static JPanel panelCronometro;

	private static JButton btnEditarCurso;

	private static JButton btnProximo;

	private static JButton btnFinalizarTudo;

	private static JButton btnPausar;

	private static JButton btnIniciar;

	private static JButton btnFinalizarCronometro;

	private static JRadioButton rdbtnNovoCurso;

	private static JRadioButton rdbtnCursoExistente;

	private static JLabel lblTotalAulas;

	private static JLabel lblNomeCurso;

	private static JLabel lblAulaAtualForm;

	private static JLabel lblAulaAtualFinalizar;

	private static JLabel lblTempoDedicado;

	private static JLabel lblMeusCursos;

	private static JLabel lblMensagem;

	private static JLabel lblMensagemFinalizar;

	private static JLabel lblTime;

	private static JTextField txtNomeCurso;

	private static JTextField txtTotalAulasForm;

	private static JTextField txtAulaAtualFinalizar;

	private static JTextField txtTotalAulasFinalizar;

	private static JTextField txtNomeCursoFinalizar;

	private static JTextField txtTempoDedicado;

	private static JTextField txtAulaAtualForm;

	private static JComboBox comboMeusCursos;

	private static JSeparator separator_1;

	private static Cursos objCursos = new Cursos();

	private static boolean controle = false;

	public static void carregarCursos() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(42, 59, 773, 564);
		HomeView.getPanelCursos().add(scrollPane);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(CursosPanelController.carregaTable(), (Object[]) new String[] { "Data",
				"Tempo Dedicado", "Nome Curso", "Total de Aulas", "Aula Atual", "Conclusão" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
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
		HomeView.getPanelCursos().add(panelCronometro);
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
				CursosPanel.iniciarActionPerformed(e);
				CursosPanel.controle = true;
			}
		});
		btnIniciar.setFont(new Font("Tahoma", 0, 12));
		btnIniciar.setBounds(24, 190, 75, 30);
		panelCronometro.add(btnIniciar);
		btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Pausar")) {
					CursosPanel.pausarActionPerformed(e);
				} else {
					CursosPanel.continuarActionPerformed(e);
				}
			}
		});
		btnPausar.setFont(new Font("Tahoma", 0, 12));
		btnPausar.setBounds(121, 190, 75, 30);
		panelCronometro.add(btnPausar);
		btnFinalizarCronometro = new JButton("Finalizar");
		btnFinalizarCronometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CursosPanel.txtAulaAtualFinalizar.setText(CursosPanel.txtAulaAtualForm.getText());
				CursosPanel.panelCronometro.setVisible(false);
				CursosPanel.panelFinalizar.setVisible(true);
				CursosPanel.objCursos.setTempoGasto(CursosPanel.formataTime());
				CursosPanel.carregaDadosFinalizar();
				CursosPanel.finalizarActionPerformed();
			}
		});
		btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
		btnFinalizarCronometro.setBounds(217, 190, 75, 30);
		btnFinalizarCronometro.setEnabled(false);
		panelCronometro.add(btnFinalizarCronometro);
		panelFinalizar = new JPanel();
		panelFinalizar.setBounds(926, 92, 318, 253);
		HomeView.getPanelCursos().add(panelFinalizar);
		panelFinalizar.setLayout((LayoutManager) null);
		panelFinalizar.setBackground(Color.LIGHT_GRAY);
		panelFinalizar.setVisible(false);
		btnFinalizarTudo = new JButton("Finalizar");
		btnFinalizarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!CursosPanel.txtAulaAtualFinalizar.getText().matches("[0-9]*")
						|| CursosPanel.txtAulaAtualFinalizar.getText().equals("")) {
					CursosPanel.lblMensagemFinalizar.setText("*Verifique aula atual");
					CursosPanel.lblMensagemFinalizar.setVisible(true);
				} else if (Integer.parseInt(CursosPanel.txtTotalAulasFinalizar.getText()) < Integer
						.parseInt(CursosPanel.txtAulaAtualFinalizar.getText())) {
					CursosPanel.lblMensagemFinalizar.setText("*Verifique aula atual");
					CursosPanel.lblMensagemFinalizar.setVisible(true);
				} else {
					Cursos curso = CursosPanelController.verificaCursoMaisRecente(UserSession.getUser().getId(),
							CursosPanel.txtNomeCursoFinalizar.getText());
					if (curso.getAuAtual() >= Integer.parseInt(CursosPanel.txtAulaAtualFinalizar.getText())) {
						CursosPanel.lblMensagemFinalizar.setText("*Verifique página atual");
						CursosPanel.lblMensagemFinalizar.setVisible(true);
					} else {
						CursosPanel.objCursos.setAuAtual(Integer.parseInt(CursosPanel.txtAulaAtualFinalizar.getText()));
						CursosPanelController.gravaCurso();
						CursosPanel.recarregaTable();
					}
				}
				CursosPanel.controle = false;
			}
		});
		btnFinalizarTudo.setBounds(187, 195, 86, 34);
		panelFinalizar.add(btnFinalizarTudo);
		JLabel lblNomeCursoFinalizar = new JLabel("Nome do Curso:");
		lblNomeCursoFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblNomeCursoFinalizar.setBounds(22, 20, 112, 20);
		panelFinalizar.add(lblNomeCursoFinalizar);
		JLabel lblTotalAulasFinalizar = new JLabel("Total de Aulas:");
		lblTotalAulasFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblTotalAulasFinalizar.setBounds(22, 75, 112, 20);
		panelFinalizar.add(lblTotalAulasFinalizar);
		lblAulaAtualFinalizar = new JLabel("Aula Atual:");
		lblAulaAtualFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblAulaAtualFinalizar.setBounds(204, 75, 112, 20);
		panelFinalizar.add(lblAulaAtualFinalizar);
		txtAulaAtualFinalizar = new JTextField();
		txtAulaAtualFinalizar.setColumns(10);
		txtAulaAtualFinalizar.setBounds(204, 100, 86, 20);
		panelFinalizar.add(txtAulaAtualFinalizar);
		txtTotalAulasFinalizar = new JTextField();
		txtTotalAulasFinalizar.setEditable(false);
		txtTotalAulasFinalizar.setColumns(10);
		txtTotalAulasFinalizar.setBounds(22, 100, 86, 20);
		panelFinalizar.add(txtTotalAulasFinalizar);
		txtNomeCursoFinalizar = new JTextField();
		txtNomeCursoFinalizar.setEditable(false);
		txtNomeCursoFinalizar.setColumns(10);
		txtNomeCursoFinalizar.setBounds(22, 44, 268, 20);
		panelFinalizar.add(txtNomeCursoFinalizar);
		lblTempoDedicado = new JLabel("Tempo Dedicado:");
		lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
		lblTempoDedicado.setBounds(103, 130, 112, 20);
		panelFinalizar.add(lblTempoDedicado);
		txtTempoDedicado = new JTextField();
		txtTempoDedicado.setEditable(false);
		txtTempoDedicado.setBounds(103, 155, 112, 20);
		panelFinalizar.add(txtTempoDedicado);
		txtTempoDedicado.setColumns(10);
		btnEditarCurso = new JButton("Alterar Curso");
		btnEditarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CursosPanel.lblMensagemFinalizar.setVisible(false);
				CursosPanel.panelFinalizar.setVisible(false);
				CursosPanel.panelForm.setVisible(true);
				CursosPanel.btnProximo.setText("Ajustar");
			}
		});
		btnEditarCurso.setBounds(45, 195, 115, 34);
		panelFinalizar.add(btnEditarCurso);
		lblMensagemFinalizar = new JLabel("Null");
		lblMensagemFinalizar.setForeground(Color.RED);
		lblMensagemFinalizar.setHorizontalAlignment(0);
		lblMensagemFinalizar.setBounds(39, 175, 240, 14);
		panelFinalizar.add(lblMensagemFinalizar);
		panelForm = new JPanel();
		panelForm.setBackground(Color.LIGHT_GRAY);
		panelForm.setBounds(926, 92, 318, 253);
		HomeView.getPanelCursos().add(panelForm);
		panelForm.setLayout((LayoutManager) null);
		comboMeusCursos = new JComboBox();
		comboMeusCursos.setVisible(false);
		comboMeusCursos.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
		comboMeusCursos.setBounds(22, 82, 250, 20);
		comboMeusCursos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Selecione...")) {
					CursosPanel.txtTotalAulasForm.setText("");
					CursosPanel.txtAulaAtualForm.setText("");
				} else {
					Cursos curso = CursosPanelController.verificaCursoMaisRecente(UserSession.getUser().getId(),
							e.getItem().toString());
					CursosPanel.txtTotalAulasForm.setText(curso.getAuTotal() + "");
					CursosPanel.txtAulaAtualForm.setText(curso.getAuAtual() + "");
				}
			}
		});
		panelForm.add(comboMeusCursos);
		txtNomeCurso = new JTextField();
		txtNomeCurso.setBounds(22, 82, 268, 20);
		panelForm.add(txtNomeCurso);
		txtNomeCurso.setColumns(10);
		lblNomeCurso = new JLabel("Nome do Curso:");
		lblNomeCurso.setFont(new Font("Tahoma", 0, 14));
		lblNomeCurso.setBounds(22, 58, 121, 20);
		panelForm.add(lblNomeCurso);
		lblTotalAulas = new JLabel("Total de Aulas:");
		lblTotalAulas.setFont(new Font("Tahoma", 0, 14));
		lblTotalAulas.setBounds(22, 115, 113, 20);
		panelForm.add(lblTotalAulas);
		txtTotalAulasForm = new JTextField();
		txtTotalAulasForm.setBounds(22, 140, 60, 20);
		panelForm.add(txtTotalAulasForm);
		txtTotalAulasForm.setColumns(10);
		rdbtnCursoExistente = new JRadioButton("Curso Existente");
		rdbtnCursoExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CursosPanel.rdbtnCursoExistente.isSelected() && CursosPanel.rdbtnNovoCurso.isSelected()) {
					CursosPanel.habilitaCursoExistente();
				} else {
					CursosPanel.rdbtnCursoExistente.setSelected(true);
				}
			}
		});
		rdbtnCursoExistente.setBackground(Color.LIGHT_GRAY);
		rdbtnCursoExistente.setBounds(157, 9, 125, 23);
		panelForm.add(rdbtnCursoExistente);
		rdbtnNovoCurso = new JRadioButton("Novo Curso");
		rdbtnNovoCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CursosPanel.rdbtnNovoCurso.isSelected() && CursosPanel.rdbtnCursoExistente.isSelected()) {
					CursosPanel.habilitaNovoCurso();
				} else {
					CursosPanel.rdbtnNovoCurso.setSelected(true);
				}
			}
		});
		rdbtnNovoCurso.setBackground(Color.LIGHT_GRAY);
		rdbtnNovoCurso.setSelected(true);
		rdbtnNovoCurso.setBounds(33, 9, 109, 23);
		panelForm.add(rdbtnNovoCurso);
		btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CursosPanel.actionProximo(e);
			}
		});
		btnProximo.setBounds(114, 196, 89, 29);
		panelForm.add(btnProximo);
		lblMeusCursos = new JLabel("Meus Cursos:");
		lblMeusCursos.setVisible(false);
		lblMeusCursos.setBounds(22, 58, 121, 14);
		panelForm.add(lblMeusCursos);
		lblMeusCursos.setFont(new Font("Tahoma", 0, 14));
		lblAulaAtualForm = new JLabel("Aula Atual:");
		lblAulaAtualForm.setVisible(false);
		lblAulaAtualForm.setFont(new Font("Tahoma", 0, 14));
		lblAulaAtualForm.setBounds(211, 109, 113, 21);
		panelForm.add(lblAulaAtualForm);
		txtAulaAtualForm = new JTextField();
		txtAulaAtualForm.setVisible(false);
		txtAulaAtualForm.setColumns(10);
		txtAulaAtualForm.setBounds(211, 133, 60, 21);
		txtAulaAtualForm.setEditable(false);
		panelForm.add(txtAulaAtualForm);
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
		if (txtNomeCurso.getText().equals("") && comboMeusCursos.getSelectedItem().equals("Selecione...")) {
			lblMensagem.setText("*Informe o Curso");
			lblMensagem.setVisible(true);
		} else if (!txtTotalAulasForm.getText().matches("[0-9]*") || txtTotalAulasForm.getText().equals("")) {
			lblMensagem.setText("*Verifique o total de aulas");
			lblMensagem.setVisible(true);
		} else {
			String curso = CursosPanelController.verificaCursoString(UserSession.getUser().getId(),
					txtNomeCurso.getText());
			if (!curso.equals("")) {
				lblMensagem.setText("*Você já cadastrou esse curso!");
				lblMensagem.setVisible(true);
			} else {
				boolean cursoFinalizado = false;
				if (!txtAulaAtualForm.getText().equals("") && !txtTotalAulasForm.getText().equals("") && Integer
						.parseInt(txtAulaAtualForm.getText()) == Integer.parseInt(txtTotalAulasForm.getText()))
					cursoFinalizado = true;
				if (cursoFinalizado) {
					lblMensagem.setText("*Esse curso já foi finalizado!");
					lblMensagem.setVisible(true);
				} else {
					panelForm.setVisible(false);
					carregaForm();
					if (e.getActionCommand().equals("Próximo")) {
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

	public static void habilitaCursoExistente() {
		comboMeusCursos.removeAllItems();
		comboMeusCursos.addItem("Selecione...");
		CursosPanelController.buscaCursos();
		lblMensagem.setVisible(false);
		rdbtnNovoCurso.setSelected(false);
		lblNomeCurso.setVisible(false);
		txtNomeCurso.setVisible(false);
		txtTotalAulasForm.setEditable(false);
		txtNomeCurso.setText("");
		txtTotalAulasForm.setText("");
		comboMeusCursos.setVisible(true);
		txtAulaAtualForm.setVisible(true);
		lblTotalAulas.setVisible(true);
		lblAulaAtualForm.setVisible(true);
		lblMeusCursos.setVisible(true);
	}

	public static void habilitaNovoCurso() {
		rdbtnCursoExistente.setSelected(false);
		lblNomeCurso.setVisible(true);
		txtNomeCurso.setVisible(true);
		lblMensagem.setVisible(false);
		txtTotalAulasForm.setEditable(true);
		comboMeusCursos.setVisible(false);
		lblMeusCursos.setVisible(false);
		comboMeusCursos.setSelectedIndex(0);
		txtAulaAtualForm.setVisible(false);
		lblAulaAtualForm.setVisible(false);
	}

	public static void carregaForm() {
		if (!txtNomeCurso.getText().equals("")) {
			objCursos.setCurso(txtNomeCurso.getText());
		} else {
			objCursos.setCurso(comboMeusCursos.getSelectedItem().toString());
		}
		Calendar cal = DatasUtil.getCalendarDate();
		Date sqlDate = new Date(cal.getTimeInMillis());
		objCursos.setData(sqlDate);
		objCursos.setAuTotal(Integer.parseInt(txtTotalAulasForm.getText()));
		objCursos.setUsuario(UserSession.getUser());
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
		txtNomeCursoFinalizar.setText(objCursos.getCurso());
		txtTotalAulasFinalizar.setText(objCursos.getAuTotal() + "");
		txtTempoDedicado.setText(objCursos.getTempoGasto());
		lblMensagemFinalizar.setVisible(false);
	}

	public static void resetPanel() {
		objCursos = new Cursos();
		panelCronometro.setVisible(false);
		panelFinalizar.setVisible(false);
		panelForm.setVisible(true);
		habilitaCursoExistente();
		habilitaNovoCurso();
		btnProximo.setText("Próximo");
		rdbtnNovoCurso.setSelected(true);
		txtAulaAtualFinalizar.setText("");
	}

	public static void recarregaTable() {
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(CursosPanelController.carregaTable(), (Object[]) new String[] { "Data",
				"Tempo Dedicado", "Nome Curso", "Total de Aulas", "Aula Atual", "Conclusão" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}
		});
		table.revalidate();
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
		CursosPanel.table = table;
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		CursosPanel.scrollPane = scrollPane;
	}

	public static JPanel getPanelForm() {
		return panelForm;
	}

	public static void setPanelForm(JPanel panelForm) {
		CursosPanel.panelForm = panelForm;
	}

	public static JPanel getPanelFinalizar() {
		return panelFinalizar;
	}

	public static void setPanelFinalizar(JPanel panelFinalizar) {
		CursosPanel.panelFinalizar = panelFinalizar;
	}

	public static JPanel getPanelCronometro() {
		return panelCronometro;
	}

	public static void setPanelCronometro(JPanel panelCronometro) {
		CursosPanel.panelCronometro = panelCronometro;
	}

	public static JButton getBtnEditarCurso() {
		return btnEditarCurso;
	}

	public static void setBtnEditarCurso(JButton btnEditarCurso) {
		CursosPanel.btnEditarCurso = btnEditarCurso;
	}

	public static JButton getBtnProximo() {
		return btnProximo;
	}

	public static void setBtnProximo(JButton btnProximo) {
		CursosPanel.btnProximo = btnProximo;
	}

	public static JButton getBtnFinalizarTudo() {
		return btnFinalizarTudo;
	}

	public static void setBtnFinalizarTudo(JButton btnFinalizarTudo) {
		CursosPanel.btnFinalizarTudo = btnFinalizarTudo;
	}

	public static JButton getBtnPausar() {
		return btnPausar;
	}

	public static void setBtnPausar(JButton btnPausar) {
		CursosPanel.btnPausar = btnPausar;
	}

	public static JButton getBtnIniciar() {
		return btnIniciar;
	}

	public static void setBtnIniciar(JButton btnIniciar) {
		CursosPanel.btnIniciar = btnIniciar;
	}

	public static JButton getBtnFinalizarCronometro() {
		return btnFinalizarCronometro;
	}

	public static void setBtnFinalizarCronometro(JButton btnFinalizarCronometro) {
		CursosPanel.btnFinalizarCronometro = btnFinalizarCronometro;
	}

	public static JRadioButton getRdbtnNovoCurso() {
		return rdbtnNovoCurso;
	}

	public static void setRdbtnNovoCurso(JRadioButton rdbtnNovoCurso) {
		CursosPanel.rdbtnNovoCurso = rdbtnNovoCurso;
	}

	public static JRadioButton getRdbtnCursoExistente() {
		return rdbtnCursoExistente;
	}

	public static void setRdbtnCursoExistente(JRadioButton rdbtnCursoExistente) {
		CursosPanel.rdbtnCursoExistente = rdbtnCursoExistente;
	}

	public static JLabel getLblTotalAulas() {
		return lblTotalAulas;
	}

	public static void setLblTotalAulas(JLabel lblTotalAulas) {
		CursosPanel.lblTotalAulas = lblTotalAulas;
	}

	public static JLabel getLblNomeCurso() {
		return lblNomeCurso;
	}

	public static void setLblNomeCurso(JLabel lblNomeCurso) {
		CursosPanel.lblNomeCurso = lblNomeCurso;
	}

	public static JLabel getLblAulaAtualForm() {
		return lblAulaAtualForm;
	}

	public static void setLblAulaAtualForm(JLabel lblAulaAtualForm) {
		CursosPanel.lblAulaAtualForm = lblAulaAtualForm;
	}

	public static JLabel getLblAulaAtualFinalizar() {
		return lblAulaAtualFinalizar;
	}

	public static void setLblAulaAtualFinalizar(JLabel lblAulaAtualFinalizar) {
		CursosPanel.lblAulaAtualFinalizar = lblAulaAtualFinalizar;
	}

	public static JLabel getLblTempoDedicado() {
		return lblTempoDedicado;
	}

	public static void setLblTempoDedicado(JLabel lblTempoDedicado) {
		CursosPanel.lblTempoDedicado = lblTempoDedicado;
	}

	public static JLabel getLblMeusCursos() {
		return lblMeusCursos;
	}

	public static void setLblMeusCursos(JLabel lblMeusCursos) {
		CursosPanel.lblMeusCursos = lblMeusCursos;
	}

	public static JLabel getLblMensagem() {
		return lblMensagem;
	}

	public static void setLblMensagem(JLabel lblMensagem) {
		CursosPanel.lblMensagem = lblMensagem;
	}

	public static JLabel getLblMensagemFinalizar() {
		return lblMensagemFinalizar;
	}

	public static void setLblMensagemFinalizar(JLabel lblMensagemFinalizar) {
		CursosPanel.lblMensagemFinalizar = lblMensagemFinalizar;
	}

	public static JTextField getTxtNomeCurso() {
		return txtNomeCurso;
	}

	public static void setTxtNomeCurso(JTextField txtNomeCurso) {
		CursosPanel.txtNomeCurso = txtNomeCurso;
	}

	public static JTextField getTxtTotalAulas() {
		return txtTotalAulasForm;
	}

	public static void setTxtTotalAulas(JTextField txtTotalAulas) {
		txtTotalAulasForm = txtTotalAulas;
	}

	public static JTextField getTxtAulaAtualFinalizar() {
		return txtAulaAtualFinalizar;
	}

	public static void setTxtAulaAtualFinalizar(JTextField txtAulaAtualFinalizar) {
		CursosPanel.txtAulaAtualFinalizar = txtAulaAtualFinalizar;
	}

	public static JTextField getTxtTotalAulasFinalizar() {
		return txtTotalAulasFinalizar;
	}

	public static void setTxtTotalAulasFinalizar(JTextField txtTotalAulasFinalizar) {
		CursosPanel.txtTotalAulasFinalizar = txtTotalAulasFinalizar;
	}

	public static JTextField getTxtNomeCursoFinalizar() {
		return txtNomeCursoFinalizar;
	}

	public static void setTxtNomeCursoFinalizar(JTextField txtNomeCursoFinalizar) {
		CursosPanel.txtNomeCursoFinalizar = txtNomeCursoFinalizar;
	}

	public static JTextField getTxtTempoDedicado() {
		return txtTempoDedicado;
	}

	public static void setTxtTempoDedicado(JTextField txtTempoDedicado) {
		CursosPanel.txtTempoDedicado = txtTempoDedicado;
	}

	public static JTextField getTxtAulaAtualForm() {
		return txtAulaAtualForm;
	}

	public static void setTxtAulaAtualForm(JTextField txtAulaAtualForm) {
		CursosPanel.txtAulaAtualForm = txtAulaAtualForm;
	}

	public static JComboBox getComboMeusCursos() {
		return comboMeusCursos;
	}

	public static void setComboMeusCursos(JComboBox comboMeusCursos) {
		CursosPanel.comboMeusCursos = comboMeusCursos;
	}

	public static JSeparator getSeparator_1() {
		return separator_1;
	}

	public static void setSeparator_1(JSeparator separator_1) {
		CursosPanel.separator_1 = separator_1;
	}

	public static Cursos getObjCursos() {
		return objCursos;
	}

	public static void setObjCursos(Cursos objLeituras) {
		objCursos = objLeituras;
	}

	public static JLabel getLblTime() {
		return lblTime;
	}

	public static void setLblTime(JLabel lblTime) {
		CursosPanel.lblTime = lblTime;
	}

	public static JTextField getTxtTotalAulasForm() {
		return txtTotalAulasForm;
	}

	public static void setTxtTotalAulasForm(JTextField txtTotalAulasForm) {
		CursosPanel.txtTotalAulasForm = txtTotalAulasForm;
	}

	public static boolean isControle() {
		return controle;
	}

	public static void setControle(boolean controle) {
		CursosPanel.controle = controle;
	}
}
