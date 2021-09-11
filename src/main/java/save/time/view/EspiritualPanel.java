package save.time.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import save.time.controller.EspiritualPanelController;
import save.time.entity.Espiritual;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Cronometro;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class EspiritualPanel {
	private static JTable table;

	private static JScrollPane scrollPane;

	private static JPanel panelForm;

	private static JPanel panelFinalizar;

	private static JPanel panelCronometro;

	private static JButton btnAlterarAtividade;

	private static JButton btnProximo;

	private static JButton btnFinalizarTudo;

	private static JButton btnPausar;

	private static JButton btnIniciar;

	private static JButton btnFinalizarCronometro;

	private static JRadioButton rdbtnNovaAtividade;

	private static JRadioButton rdbtnAtividadeExistente;

	private static JLabel lblNomeAtividade;

	private static JLabel lblTempoDedicado;

	private static JLabel lblMinhasAtividades;

	private static JLabel lblMensagem;

	private static JLabel lblMensagemFinalizar;

	private static JLabel lblTime;

	private static JTextField txtNomeAtividade;

	private static JTextField txtNomeAtividadeFinalizar;

	private static JTextField txtTempoDedicado;

	private static JComboBox comboMinhasAtividades;

	private static JSeparator separator_1;

	private static JSeparator separator_2;

	private static Espiritual objEspiritual = new Espiritual();

	private static boolean controle = false;

	public static void carregarEspiritual() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(42, 59, 773, 564);
		HomeView.getPanelEspiritual().add(scrollPane);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(EspiritualPanelController.carregaTable(),
				(Object[]) new String[] { "Data", "Tempo Dedicado", "Atividade" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}
		});
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		scrollPane.setViewportView(table);
		panelCronometro = new JPanel();
		panelCronometro.setBounds(926, 92, 318, 253);
		HomeView.getPanelEspiritual().add(panelCronometro);
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
				EspiritualPanel.iniciarActionPerformed(e);
				EspiritualPanel.controle = true;
			}
		});
		btnIniciar.setFont(new Font("Tahoma", 0, 12));
		btnIniciar.setBounds(24, 190, 75, 30);
		panelCronometro.add(btnIniciar);
		btnPausar = new JButton("Pausar");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Pausar")) {
					EspiritualPanel.pausarActionPerformed(e);
				} else {
					EspiritualPanel.continuarActionPerformed(e);
				}
			}
		});
		btnPausar.setFont(new Font("Tahoma", 0, 12));
		btnPausar.setBounds(121, 190, 75, 30);
		panelCronometro.add(btnPausar);
		btnFinalizarCronometro = new JButton("Finalizar");
		btnFinalizarCronometro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspiritualPanel.panelCronometro.setVisible(false);
				EspiritualPanel.panelFinalizar.setVisible(true);
				EspiritualPanel.objEspiritual.setTempoGasto(EspiritualPanel.formataTime());
				EspiritualPanel.carregaDadosFinalizar();
				EspiritualPanel.finalizarActionPerformed();
			}
		});
		btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
		btnFinalizarCronometro.setBounds(217, 190, 75, 30);
		btnFinalizarCronometro.setEnabled(false);
		panelCronometro.add(btnFinalizarCronometro);
		panelFinalizar = new JPanel();
		panelFinalizar.setBounds(926, 92, 318, 253);
		HomeView.getPanelEspiritual().add(panelFinalizar);
		panelFinalizar.setLayout((LayoutManager) null);
		panelFinalizar.setBackground(Color.LIGHT_GRAY);
		panelFinalizar.setVisible(false);
		btnFinalizarTudo = new JButton("Finalizar");
		btnFinalizarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspiritualPanelController.gravaAtividade();
				EspiritualPanel.recarregaTable();
				EspiritualPanel.controle = false;
			}
		});
		btnFinalizarTudo.setBounds(187, 195, 86, 34);
		panelFinalizar.add(btnFinalizarTudo);
		JLabel lblNomeAtividadeFinalizar = new JLabel("Nome da Atividade:");
		lblNomeAtividadeFinalizar.setFont(new Font("Tahoma", 0, 14));
		lblNomeAtividadeFinalizar.setBounds(22, 30, 130, 20);
		panelFinalizar.add(lblNomeAtividadeFinalizar);
		txtNomeAtividadeFinalizar = new JTextField();
		txtNomeAtividadeFinalizar.setEditable(false);
		txtNomeAtividadeFinalizar.setColumns(10);
		txtNomeAtividadeFinalizar.setBounds(22, 55, 268, 20);
		panelFinalizar.add(txtNomeAtividadeFinalizar);
		lblTempoDedicado = new JLabel("Tempo Dedicado:");
		lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
		lblTempoDedicado.setBounds(103, 107, 112, 20);
		panelFinalizar.add(lblTempoDedicado);
		txtTempoDedicado = new JTextField();
		txtTempoDedicado.setEditable(false);
		txtTempoDedicado.setBounds(103, 132, 112, 20);
		panelFinalizar.add(txtTempoDedicado);
		txtTempoDedicado.setColumns(10);
		btnAlterarAtividade = new JButton("Alterar Atividade");
		btnAlterarAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspiritualPanel.lblMensagemFinalizar.setVisible(false);
				EspiritualPanel.panelFinalizar.setVisible(false);
				EspiritualPanel.panelForm.setVisible(true);
				EspiritualPanel.btnProximo.setText("Ajustar");
			}
		});
		btnAlterarAtividade.setBounds(45, 195, 135, 34);
		panelFinalizar.add(btnAlterarAtividade);
		lblMensagemFinalizar = new JLabel("Null");
		lblMensagemFinalizar.setForeground(Color.RED);
		lblMensagemFinalizar.setHorizontalAlignment(0);
		lblMensagemFinalizar.setBounds(39, 175, 240, 14);
		panelFinalizar.add(lblMensagemFinalizar);
		panelForm = new JPanel();
		panelForm.setBackground(Color.LIGHT_GRAY);
		panelForm.setBounds(926, 92, 318, 253);
		HomeView.getPanelEspiritual().add(panelForm);
		panelForm.setLayout((LayoutManager) null);
		comboMinhasAtividades = new JComboBox();
		comboMinhasAtividades.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
		comboMinhasAtividades.setBounds(22, 158, 268, 20);
		comboMinhasAtividades.setEnabled(false);
		panelForm.add(comboMinhasAtividades);
		txtNomeAtividade = new JTextField();
		txtNomeAtividade.setBounds(22, 69, 268, 20);
		panelForm.add(txtNomeAtividade);
		txtNomeAtividade.setColumns(10);
		lblNomeAtividade = new JLabel("Nome da Atividade:");
		lblNomeAtividade.setFont(new Font("Tahoma", 0, 14));
		lblNomeAtividade.setBounds(22, 45, 121, 20);
		panelForm.add(lblNomeAtividade);
		rdbtnAtividadeExistente = new JRadioButton("Atividade Existente");
		rdbtnAtividadeExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EspiritualPanel.rdbtnAtividadeExistente.isSelected()
						&& EspiritualPanel.rdbtnNovaAtividade.isSelected()) {
					EspiritualPanel.habilitaAtividadeExistente();
				} else {
					EspiritualPanel.rdbtnAtividadeExistente.setSelected(true);
				}
			}
		});
		rdbtnAtividadeExistente.setBackground(Color.LIGHT_GRAY);
		rdbtnAtividadeExistente.setBounds(22, 97, 150, 23);
		panelForm.add(rdbtnAtividadeExistente);
		rdbtnNovaAtividade = new JRadioButton("Nova Atividade");
		rdbtnNovaAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EspiritualPanel.rdbtnNovaAtividade.isSelected()
						&& EspiritualPanel.rdbtnAtividadeExistente.isSelected()) {
					EspiritualPanel.habilitaNovaAtividade();
				} else {
					EspiritualPanel.rdbtnNovaAtividade.setSelected(true);
				}
			}
		});
		rdbtnNovaAtividade.setBackground(Color.LIGHT_GRAY);
		rdbtnNovaAtividade.setSelected(true);
		rdbtnNovaAtividade.setBounds(20, 9, 109, 23);
		panelForm.add(rdbtnNovaAtividade);
		btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspiritualPanel.actionProximo(e);
			}
		});
		btnProximo.setBounds(114, 206, 89, 29);
		panelForm.add(btnProximo);
		lblMinhasAtividades = new JLabel("Minhas Atividades:");
		lblMinhasAtividades.setBounds(22, 134, 121, 14);
		panelForm.add(lblMinhasAtividades);
		lblMinhasAtividades.setFont(new Font("Tahoma", 0, 14));
		lblMinhasAtividades.setEnabled(false);
		lblMensagem = new JLabel("Null");
		lblMensagem.setForeground(Color.RED);
		lblMensagem.setHorizontalAlignment(0);
		lblMensagem.setAlignmentX(0.5F);
		lblMensagem.setBounds(41, 184, 235, 14);
		lblMensagem.setVisible(false);
		panelForm.add(lblMensagem);
		separator_1 = new JSeparator();
		separator_1.setBorder(new BevelBorder(0, null, null, null, null));
		separator_1.setBounds(10, 38, 298, 2);
		panelForm.add(separator_1);
		separator_2 = new JSeparator();
		separator_2.setBorder(new BevelBorder(0, null, null, null, null));
		separator_2.setBounds(10, 124, 298, 2);
		panelForm.add(separator_2);
	}

	public static void actionProximo(ActionEvent e) {
		if (txtNomeAtividade.getText().equals("") && comboMinhasAtividades.getSelectedItem().equals("Selecione...")) {
			lblMensagem.setText("*Informe o Atividade");
			lblMensagem.setVisible(true);
		} else {
			String livro = EspiritualPanelController.verificaAtividadeString(UserSession.getUser().getId(),
					txtNomeAtividade.getText());
			if (!livro.equals("")) {
				lblMensagem.setText("*Voc\u00EA j\u00E1 cadastrou esse Atividade!");
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

	public static void habilitaAtividadeExistente() {
		comboMinhasAtividades.removeAllItems();
		comboMinhasAtividades.addItem("Selecione...");
		EspiritualPanelController.buscaAtividades();
		lblMensagem.setVisible(false);
		rdbtnNovaAtividade.setSelected(false);
		lblNomeAtividade.setEnabled(false);
		txtNomeAtividade.setEnabled(false);
		txtNomeAtividade.setText("");
		comboMinhasAtividades.setEnabled(true);
		lblMinhasAtividades.setEnabled(true);
	}

	public static void habilitaNovaAtividade() {
		rdbtnAtividadeExistente.setSelected(false);
		lblNomeAtividade.setEnabled(true);
		txtNomeAtividade.setEnabled(true);
		lblMensagem.setVisible(false);
		comboMinhasAtividades.setEnabled(false);
		lblMinhasAtividades.setEnabled(false);
		comboMinhasAtividades.setSelectedIndex(0);
	}

	public static void carregaForm() {
		if (!txtNomeAtividade.getText().equals("")) {
			objEspiritual.setAtividade(txtNomeAtividade.getText());
		} else {
			objEspiritual.setAtividade(comboMinhasAtividades.getSelectedItem().toString());
		}
		Calendar cal = DatasUtil.getCalendarDate();
		Date sqlDate = new Date(cal.getTimeInMillis());
		objEspiritual.setData(sqlDate);
		objEspiritual.setUsuario(UserSession.getUser());
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
		txtNomeAtividadeFinalizar.setText(objEspiritual.getAtividade());
		txtTempoDedicado.setText(objEspiritual.getTempoGasto());
		lblMensagemFinalizar.setVisible(false);
	}

	public static void resetPanel() {
		objEspiritual = new Espiritual();
		panelCronometro.setVisible(false);
		panelFinalizar.setVisible(false);
		panelForm.setVisible(true);
		habilitaAtividadeExistente();
		habilitaNovaAtividade();
		btnProximo.setText("Pr\u00F3ximo");
		rdbtnNovaAtividade.setSelected(true);
	}

	public static void recarregaTable() {
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(0);
		table = new JTable();
		table.setSelectionBackground(new Color(220, 220, 220));
		table.setCursor(Cursor.getPredefinedCursor(0));
		table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		table.setBackground(new Color(255, 204, 51));
		table.setModel(new DefaultTableModel(EspiritualPanelController.carregaTable(),
				(Object[]) new String[] { "Data", "Tempo Dedicado", "Atividade" }) {
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
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		EspiritualPanel.table = table;
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		EspiritualPanel.scrollPane = scrollPane;
	}

	public static JPanel getPanelForm() {
		return panelForm;
	}

	public static void setPanelForm(JPanel panelForm) {
		EspiritualPanel.panelForm = panelForm;
	}

	public static JPanel getPanelFinalizar() {
		return panelFinalizar;
	}

	public static void setPanelFinalizar(JPanel panelFinalizar) {
		EspiritualPanel.panelFinalizar = panelFinalizar;
	}

	public static JPanel getPanelCronometro() {
		return panelCronometro;
	}

	public static void setPanelCronometro(JPanel panelCronometro) {
		EspiritualPanel.panelCronometro = panelCronometro;
	}

	public static JButton getBtnEditarAtividade() {
		return btnAlterarAtividade;
	}

	public static void setBtnEditarAtividade(JButton btnEditarLivro) {
		btnAlterarAtividade = btnEditarLivro;
	}

	public static JButton getBtnProximo() {
		return btnProximo;
	}

	public static void setBtnProximo(JButton btnProximo) {
		EspiritualPanel.btnProximo = btnProximo;
	}

	public static JButton getBtnFinalizarTudo() {
		return btnFinalizarTudo;
	}

	public static void setBtnFinalizarTudo(JButton btnFinalizarTudo) {
		EspiritualPanel.btnFinalizarTudo = btnFinalizarTudo;
	}

	public static JButton getBtnPausar() {
		return btnPausar;
	}

	public static void setBtnPausar(JButton btnPausar) {
		EspiritualPanel.btnPausar = btnPausar;
	}

	public static JButton getBtnIniciar() {
		return btnIniciar;
	}

	public static void setBtnIniciar(JButton btnIniciar) {
		EspiritualPanel.btnIniciar = btnIniciar;
	}

	public static JButton getBtnFinalizarCronometro() {
		return btnFinalizarCronometro;
	}

	public static void setBtnFinalizarCronometro(JButton btnFinalizarCronometro) {
		EspiritualPanel.btnFinalizarCronometro = btnFinalizarCronometro;
	}

	public static JRadioButton getRdbtnNovaAtividade() {
		return rdbtnNovaAtividade;
	}

	public static void setRdbtnNovaAtividade(JRadioButton rdbtnNovaAtividade) {
		EspiritualPanel.rdbtnNovaAtividade = rdbtnNovaAtividade;
	}

	public static JRadioButton getRdbtnAtividadeExistente() {
		return rdbtnAtividadeExistente;
	}

	public static void setRdbtnAtividadeExistente(JRadioButton rdbtnAtividadeExistente) {
		EspiritualPanel.rdbtnAtividadeExistente = rdbtnAtividadeExistente;
	}

	public static JLabel getLblNomeAtividade() {
		return lblNomeAtividade;
	}

	public static void setLblNomeAtividade(JLabel lblNomeAtividade) {
		EspiritualPanel.lblNomeAtividade = lblNomeAtividade;
	}

	public static JLabel getLblTempoDedicado() {
		return lblTempoDedicado;
	}

	public static void setLblTempoDedicado(JLabel lblTempoDedicado) {
		EspiritualPanel.lblTempoDedicado = lblTempoDedicado;
	}

	public static JLabel getLblMinhasAtividades() {
		return lblMinhasAtividades;
	}

	public static void setLblMinhasAtividades(JLabel lblMinhasAtividades) {
		EspiritualPanel.lblMinhasAtividades = lblMinhasAtividades;
	}

	public static JLabel getLblMensagem() {
		return lblMensagem;
	}

	public static void setLblMensagem(JLabel lblMensagem) {
		EspiritualPanel.lblMensagem = lblMensagem;
	}

	public static JLabel getLblMensagemFinalizar() {
		return lblMensagemFinalizar;
	}

	public static void setLblMensagemFinalizar(JLabel lblMensagemFinalizar) {
		EspiritualPanel.lblMensagemFinalizar = lblMensagemFinalizar;
	}

	public static JTextField getTxtNomeAtividade() {
		return txtNomeAtividade;
	}

	public static void setTxtNomeAtividade(JTextField txtNomeAtividade) {
		EspiritualPanel.txtNomeAtividade = txtNomeAtividade;
	}

	public static JTextField getTxtNomeAtividadeFinalizar() {
		return txtNomeAtividadeFinalizar;
	}

	public static void setTxtNomeAtividadeFinalizar(JTextField txtNomeAtividadeFinalizar) {
		EspiritualPanel.txtNomeAtividadeFinalizar = txtNomeAtividadeFinalizar;
	}

	public static JTextField getTxtTempoDedicado() {
		return txtTempoDedicado;
	}

	public static void setTxtTempoDedicado(JTextField txtTempoDedicado) {
		EspiritualPanel.txtTempoDedicado = txtTempoDedicado;
	}

	public static JComboBox getComboMinhasAtividades() {
		return comboMinhasAtividades;
	}

	public static void setComboMinhasAtividades(JComboBox comboMinhasAtividades) {
		EspiritualPanel.comboMinhasAtividades = comboMinhasAtividades;
	}

	public static JSeparator getSeparator_1() {
		return separator_1;
	}

	public static void setSeparator_1(JSeparator separator_1) {
		EspiritualPanel.separator_1 = separator_1;
	}

	public static Espiritual getObjEspiritual() {
		return objEspiritual;
	}

	public static void setObjEspiritual(Espiritual objEspiritual) {
		EspiritualPanel.objEspiritual = objEspiritual;
	}

	public static JLabel getLblTime() {
		return lblTime;
	}

	public static void setLblTime(JLabel lblTime) {
		EspiritualPanel.lblTime = lblTime;
	}

	public static boolean isControle() {
		return controle;
	}

	public static void setControle(boolean controle) {
		EspiritualPanel.controle = controle;
	}
}
