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
import save.time.controller.ExerciciosPanelController;
import save.time.entity.Exercicios;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Cronometro;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class ExerciciosPanel {
  private static JTable table;
  
  private static JScrollPane scrollPane;
  
  private static JPanel panelForm;
  
  private static JPanel panelFinalizar;
  
  private static JPanel panelCronometro;
  
  private static JButton btnAlterarExercicio;
  
  private static JButton btnProximo;
  
  private static JButton btnFinalizarTudo;
  
  private static JButton btnPausar;
  
  private static JButton btnIniciar;
  
  private static JButton btnFinalizarCronometro;
  
  private static JRadioButton rdbtnNovoExercicio;
  
  private static JRadioButton rdbtnExercicioExistente;
  
  private static JLabel lblNomeExercicio;
  
  private static JLabel lblTempoDedicado;
  
  private static JLabel lblMeusExercicios;
  
  private static JLabel lblMensagem;
  
  private static JLabel lblMensagemFinalizar;
  
  private static JLabel lblTime;
  
  private static JTextField txtNomeExercicio;
  
  private static JTextField txtNomeExercicioFinalizar;
  
  private static JTextField txtTempoDedicado;
  
  private static JComboBox comboMeusExercicios;
  
  private static JSeparator separator_1;
  
  private static JSeparator separator_2;
  
  private static Exercicios objExercicios = new Exercicios();
  
  private static boolean controle = false;
  
  public static void carregarExercicios() {
    scrollPane = new JScrollPane();
    scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
    scrollPane.setBackground(Color.DARK_GRAY);
    scrollPane.setBounds(42, 59, 773, 564);
    HomeView.getPanelExercicios().add(scrollPane);
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    centralizado.setHorizontalAlignment(0);
    table = new JTable();
    table.setSelectionBackground(new Color(220, 220, 220));
    table.setCursor(Cursor.getPredefinedCursor(0));
    table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    table.setBackground(new Color(255, 204, 51));
    table.setModel(new DefaultTableModel(ExerciciosPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Tempo Dedicado", "Exerc\u00EDcio" }) {
          Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, String.class, String.class };
          
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
    HomeView.getPanelExercicios().add(panelCronometro);
    panelCronometro.setBackground(Color.LIGHT_GRAY);
    panelCronometro.setLayout((LayoutManager)null);
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
            ExerciciosPanel.iniciarActionPerformed(e);
            ExerciciosPanel.controle = true;
          }
        });
    btnIniciar.setFont(new Font("Tahoma", 0, 12));
    btnIniciar.setBounds(24, 190, 75, 30);
    panelCronometro.add(btnIniciar);
    btnPausar = new JButton("Pausar");
    btnPausar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pausar")) {
              ExerciciosPanel.pausarActionPerformed(e);
            } else {
              ExerciciosPanel.continuarActionPerformed(e);
            } 
          }
        });
    btnPausar.setFont(new Font("Tahoma", 0, 12));
    btnPausar.setBounds(121, 190, 75, 30);
    panelCronometro.add(btnPausar);
    btnFinalizarCronometro = new JButton("Finalizar");
    btnFinalizarCronometro.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ExerciciosPanel.panelCronometro.setVisible(false);
            ExerciciosPanel.panelFinalizar.setVisible(true);
            ExerciciosPanel.objExercicios.setTempoGasto(ExerciciosPanel.formataTime());
            ExerciciosPanel.carregaDadosFinalizar();
            ExerciciosPanel.finalizarActionPerformed();
          }
        });
    btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
    btnFinalizarCronometro.setBounds(217, 190, 75, 30);
    btnFinalizarCronometro.setEnabled(false);
    panelCronometro.add(btnFinalizarCronometro);
    panelFinalizar = new JPanel();
    panelFinalizar.setBounds(926, 92, 318, 253);
    HomeView.getPanelExercicios().add(panelFinalizar);
    panelFinalizar.setLayout((LayoutManager)null);
    panelFinalizar.setBackground(Color.LIGHT_GRAY);
    panelFinalizar.setVisible(false);
    btnFinalizarTudo = new JButton("Finalizar");
    btnFinalizarTudo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ExerciciosPanelController.gravaExercicio();
            ExerciciosPanel.recarregaTable();
            ExerciciosPanel.controle = false;
          }
        });
    btnFinalizarTudo.setBounds(187, 195, 86, 34);
    panelFinalizar.add(btnFinalizarTudo);
    JLabel lblNomeExercicioFinalizar = new JLabel("Nome do Exerc\u00EDcio:");
    lblNomeExercicioFinalizar.setFont(new Font("Tahoma", 0, 14));
    lblNomeExercicioFinalizar.setBounds(22, 30, 130, 20);
    panelFinalizar.add(lblNomeExercicioFinalizar);
    txtNomeExercicioFinalizar = new JTextField();
    txtNomeExercicioFinalizar.setEditable(false);
    txtNomeExercicioFinalizar.setColumns(10);
    txtNomeExercicioFinalizar.setBounds(22, 55, 268, 20);
    panelFinalizar.add(txtNomeExercicioFinalizar);
    lblTempoDedicado = new JLabel("Tempo Dedicado:");
    lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
    lblTempoDedicado.setBounds(103, 107, 112, 20);
    panelFinalizar.add(lblTempoDedicado);
    txtTempoDedicado = new JTextField();
    txtTempoDedicado.setEditable(false);
    txtTempoDedicado.setBounds(103, 132, 112, 20);
    panelFinalizar.add(txtTempoDedicado);
    txtTempoDedicado.setColumns(10);
    btnAlterarExercicio = new JButton("Alterar Exerc\u00EDcio");
    btnAlterarExercicio.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ExerciciosPanel.lblMensagemFinalizar.setVisible(false);
            ExerciciosPanel.panelFinalizar.setVisible(false);
            ExerciciosPanel.panelForm.setVisible(true);
            ExerciciosPanel.btnProximo.setText("Ajustar");
          }
        });
    btnAlterarExercicio.setBounds(45, 195, 135, 34);
    panelFinalizar.add(btnAlterarExercicio);
    lblMensagemFinalizar = new JLabel("Null");
    lblMensagemFinalizar.setForeground(Color.RED);
    lblMensagemFinalizar.setHorizontalAlignment(0);
    lblMensagemFinalizar.setBounds(39, 175, 240, 14);
    panelFinalizar.add(lblMensagemFinalizar);
    panelForm = new JPanel();
    panelForm.setBackground(Color.LIGHT_GRAY);
    panelForm.setBounds(926, 92, 318, 253);
    HomeView.getPanelExercicios().add(panelForm);
    panelForm.setLayout((LayoutManager)null);
    comboMeusExercicios = new JComboBox();
    comboMeusExercicios.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
    comboMeusExercicios.setBounds(22, 158, 200, 20);
    comboMeusExercicios.setEnabled(false);
    panelForm.add(comboMeusExercicios);
    txtNomeExercicio = new JTextField();
    txtNomeExercicio.setBounds(22, 69, 268, 20);
    panelForm.add(txtNomeExercicio);
    txtNomeExercicio.setColumns(10);
    lblNomeExercicio = new JLabel("Nome do Exerc\u00EDcio:");
    lblNomeExercicio.setFont(new Font("Tahoma", 0, 14));
    lblNomeExercicio.setBounds(22, 45, 121, 20);
    panelForm.add(lblNomeExercicio);
    rdbtnExercicioExistente = new JRadioButton("Exerc\u00EDcio Existente");
    rdbtnExercicioExistente.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (ExerciciosPanel.rdbtnExercicioExistente.isSelected() && ExerciciosPanel.rdbtnNovoExercicio.isSelected()) {
              ExerciciosPanel.habilitaExercicioExistente();
            } else {
              ExerciciosPanel.rdbtnExercicioExistente.setSelected(true);
            } 
          }
        });
    rdbtnExercicioExistente.setBackground(Color.LIGHT_GRAY);
    rdbtnExercicioExistente.setBounds(22, 97, 150, 23);
    panelForm.add(rdbtnExercicioExistente);
    rdbtnNovoExercicio = new JRadioButton("Novo Exerc\u00EDcio");
    rdbtnNovoExercicio.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (ExerciciosPanel.rdbtnNovoExercicio.isSelected() && ExerciciosPanel.rdbtnExercicioExistente.isSelected()) {
              ExerciciosPanel.habilitaNovoExercicio();
            } else {
              ExerciciosPanel.rdbtnNovoExercicio.setSelected(true);
            } 
          }
        });
    rdbtnNovoExercicio.setBackground(Color.LIGHT_GRAY);
    rdbtnNovoExercicio.setSelected(true);
    rdbtnNovoExercicio.setBounds(20, 9, 109, 23);
    panelForm.add(rdbtnNovoExercicio);
    btnProximo = new JButton("Pr\u00F3ximo");
    btnProximo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ExerciciosPanel.actionProximo(e);
          }
        });
    btnProximo.setBounds(114, 206, 89, 29);
    panelForm.add(btnProximo);
    lblMeusExercicios = new JLabel("Meus Exerc\u00EDcios:");
    lblMeusExercicios.setBounds(22, 134, 121, 14);
    panelForm.add(lblMeusExercicios);
    lblMeusExercicios.setFont(new Font("Tahoma", 0, 14));
    lblMeusExercicios.setEnabled(false);
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
    if (txtNomeExercicio.getText().equals("") && comboMeusExercicios.getSelectedItem().equals("Selecione...")) {
      lblMensagem.setText("*Informe o Exerc\u00EDcio");
      lblMensagem.setVisible(true);
    } else {
      String livro = ExerciciosPanelController.verificaExercicioString(UserSession.getUser().getId(), txtNomeExercicio.getText());
      if (!livro.equals("")) {
        lblMensagem.setText("*Voc\u00EA j\u00E1 cadastrou esse exercicio!");
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
  
  public static void habilitaExercicioExistente() {
    comboMeusExercicios.removeAllItems();
    comboMeusExercicios.addItem("Selecione...");
    ExerciciosPanelController.buscaExercicios();
    lblMensagem.setVisible(false);
    rdbtnNovoExercicio.setSelected(false);
    lblNomeExercicio.setEnabled(false);
    txtNomeExercicio.setEnabled(false);
    txtNomeExercicio.setText("");
    comboMeusExercicios.setEnabled(true);
    lblMeusExercicios.setEnabled(true);
  }
  
  public static void habilitaNovoExercicio() {
    rdbtnExercicioExistente.setSelected(false);
    lblNomeExercicio.setEnabled(true);
    txtNomeExercicio.setEnabled(true);
    lblMensagem.setVisible(false);
    comboMeusExercicios.setEnabled(false);
    lblMeusExercicios.setEnabled(false);
    comboMeusExercicios.setSelectedIndex(0);
  }
  
  public static void carregaForm() {
    if (!txtNomeExercicio.getText().equals("")) {
      objExercicios.setExercicio(txtNomeExercicio.getText());
    } else {
      objExercicios.setExercicio(comboMeusExercicios.getSelectedItem().toString());
    } 
    Calendar cal = DatasUtil.getCalendarDate();
    Date sqlDate = new Date(cal.getTimeInMillis());
    objExercicios.setData(sqlDate);
    objExercicios.setUsuario(UserSession.getUser());
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
    txtNomeExercicioFinalizar.setText(objExercicios.getExercicio());
    txtTempoDedicado.setText(objExercicios.getTempoGasto());
    lblMensagemFinalizar.setVisible(false);
  }
  
  public static void resetPanel() {
    objExercicios = new Exercicios();
    panelCronometro.setVisible(false);
    panelFinalizar.setVisible(false);
    panelForm.setVisible(true);
    habilitaExercicioExistente();
    habilitaNovoExercicio();
    btnProximo.setText("Pr\u00F3ximo");
    rdbtnNovoExercicio.setSelected(true);
  }
  
  public static void recarregaTable() {
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    centralizado.setHorizontalAlignment(0);
    table = new JTable();
    table.setSelectionBackground(new Color(220, 220, 220));
    table.setCursor(Cursor.getPredefinedCursor(0));
    table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    table.setBackground(new Color(255, 204, 51));
    table.setModel(new DefaultTableModel(ExerciciosPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Tempo Dedicado", "Exerc\u00EDcio" }) {
          Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };
          
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
    ExerciciosPanel.table = table;
  }
  
  public static JScrollPane getScrollPane() {
    return scrollPane;
  }
  
  public static void setScrollPane(JScrollPane scrollPane) {
    ExerciciosPanel.scrollPane = scrollPane;
  }
  
  public static JPanel getPanelForm() {
    return panelForm;
  }
  
  public static void setPanelForm(JPanel panelForm) {
    ExerciciosPanel.panelForm = panelForm;
  }
  
  public static JPanel getPanelFinalizar() {
    return panelFinalizar;
  }
  
  public static void setPanelFinalizar(JPanel panelFinalizar) {
    ExerciciosPanel.panelFinalizar = panelFinalizar;
  }
  
  public static JPanel getPanelCronometro() {
    return panelCronometro;
  }
  
  public static void setPanelCronometro(JPanel panelCronometro) {
    ExerciciosPanel.panelCronometro = panelCronometro;
  }
  
  public static JButton getBtnEditarExercicio() {
    return btnAlterarExercicio;
  }
  
  public static void setBtnEditarExercicio(JButton btnEditarLivro) {
    btnAlterarExercicio = btnEditarLivro;
  }
  
  public static JButton getBtnProximo() {
    return btnProximo;
  }
  
  public static void setBtnProximo(JButton btnProximo) {
    ExerciciosPanel.btnProximo = btnProximo;
  }
  
  public static JButton getBtnFinalizarTudo() {
    return btnFinalizarTudo;
  }
  
  public static void setBtnFinalizarTudo(JButton btnFinalizarTudo) {
    ExerciciosPanel.btnFinalizarTudo = btnFinalizarTudo;
  }
  
  public static JButton getBtnPausar() {
    return btnPausar;
  }
  
  public static void setBtnPausar(JButton btnPausar) {
    ExerciciosPanel.btnPausar = btnPausar;
  }
  
  public static JButton getBtnIniciar() {
    return btnIniciar;
  }
  
  public static void setBtnIniciar(JButton btnIniciar) {
    ExerciciosPanel.btnIniciar = btnIniciar;
  }
  
  public static JButton getBtnFinalizarCronometro() {
    return btnFinalizarCronometro;
  }
  
  public static void setBtnFinalizarCronometro(JButton btnFinalizarCronometro) {
    ExerciciosPanel.btnFinalizarCronometro = btnFinalizarCronometro;
  }
  
  public static JRadioButton getRdbtnNovoExercicio() {
    return rdbtnNovoExercicio;
  }
  
  public static void setRdbtnNovoExercicio(JRadioButton rdbtnNovoExercicio) {
    ExerciciosPanel.rdbtnNovoExercicio = rdbtnNovoExercicio;
  }
  
  public static JRadioButton getRdbtnExercicioExistente() {
    return rdbtnExercicioExistente;
  }
  
  public static void setRdbtnExercicioExistente(JRadioButton rdbtnExercicioExistente) {
    ExerciciosPanel.rdbtnExercicioExistente = rdbtnExercicioExistente;
  }
  
  public static JLabel getLblNomeExercicio() {
    return lblNomeExercicio;
  }
  
  public static void setLblNomeExercicio(JLabel lblNomeExercicio) {
    ExerciciosPanel.lblNomeExercicio = lblNomeExercicio;
  }
  
  public static JLabel getLblTempoDedicado() {
    return lblTempoDedicado;
  }
  
  public static void setLblTempoDedicado(JLabel lblTempoDedicado) {
    ExerciciosPanel.lblTempoDedicado = lblTempoDedicado;
  }
  
  public static JLabel getLblMeusExercicios() {
    return lblMeusExercicios;
  }
  
  public static void setLblMeusExercicios(JLabel lblMeusExercicios) {
    ExerciciosPanel.lblMeusExercicios = lblMeusExercicios;
  }
  
  public static JLabel getLblMensagem() {
    return lblMensagem;
  }
  
  public static void setLblMensagem(JLabel lblMensagem) {
    ExerciciosPanel.lblMensagem = lblMensagem;
  }
  
  public static JLabel getLblMensagemFinalizar() {
    return lblMensagemFinalizar;
  }
  
  public static void setLblMensagemFinalizar(JLabel lblMensagemFinalizar) {
    ExerciciosPanel.lblMensagemFinalizar = lblMensagemFinalizar;
  }
  
  public static JTextField getTxtNomeExercicio() {
    return txtNomeExercicio;
  }
  
  public static void setTxtNomeExercicio(JTextField txtNomeExercicio) {
    ExerciciosPanel.txtNomeExercicio = txtNomeExercicio;
  }
  
  public static JTextField getTxtNomeExercicioFinalizar() {
    return txtNomeExercicioFinalizar;
  }
  
  public static void setTxtNomeExercicioFinalizar(JTextField txtNomeExercicioFinalizar) {
    ExerciciosPanel.txtNomeExercicioFinalizar = txtNomeExercicioFinalizar;
  }
  
  public static JTextField getTxtTempoDedicado() {
    return txtTempoDedicado;
  }
  
  public static void setTxtTempoDedicado(JTextField txtTempoDedicado) {
    ExerciciosPanel.txtTempoDedicado = txtTempoDedicado;
  }
  
  public static JComboBox getComboMeusExercicios() {
    return comboMeusExercicios;
  }
  
  public static void setComboMeusExercicios(JComboBox comboMeusExercicios) {
    ExerciciosPanel.comboMeusExercicios = comboMeusExercicios;
  }
  
  public static JSeparator getSeparator_1() {
    return separator_1;
  }
  
  public static void setSeparator_1(JSeparator separator_1) {
    ExerciciosPanel.separator_1 = separator_1;
  }
  
  public static Exercicios getObjExercicios() {
    return objExercicios;
  }
  
  public static void setObjExercicios(Exercicios objExercicios) {
    ExerciciosPanel.objExercicios = objExercicios;
  }
  
  public static JLabel getLblTime() {
    return lblTime;
  }
  
  public static void setLblTime(JLabel lblTime) {
    ExerciciosPanel.lblTime = lblTime;
  }
  
  public static boolean isControle() {
    return controle;
  }
  
  public static void setControle(boolean controle) {
    ExerciciosPanel.controle = controle;
  }
}
