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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import save.time.controller.IdiomasPanelController;
import save.time.entity.Idiomas;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.ferramentas.Cronometro;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class IdiomasPanel {
  private static JTable table;
  
  private static JScrollPane scrollPane;
  
  private static JPanel panelForm;
  
  private static JPanel panelFinalizar;
  
  private static JPanel panelCronometro;
  
  private static JButton btnAlterarIdioma;
  
  private static JButton btnProximo;
  
  private static JButton btnFinalizarTudo;
  
  private static JButton btnPausar;
  
  private static JButton btnIniciar;
  
  private static JButton btnFinalizarCronometro;
  
  private static JLabel lblTempoDedicado;
  
  private static JLabel lblSelecioneIdioma;
  
  private static JLabel lblMensagem;
  
  private static JLabel lblMensagemFinalizar;
  
  private static JLabel lblTime;
  
  private static JTextField txtNomeIdiomaFinalizar;
  
  private static JTextField txtTempoDedicado;
  
  private static JComboBox comboMeusIdiomas;
  
  private static JSeparator separator_1;
  
  private static Idiomas objIdiomas = new Idiomas();
  
  private static boolean controle = false;
  
  public static void carregarIdiomas() {
    scrollPane = new JScrollPane();
    scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
    scrollPane.setBackground(Color.DARK_GRAY);
    scrollPane.setBounds(42, 59, 773, 564);
    HomeView.getPanelIdiomas().add(scrollPane);
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    centralizado.setHorizontalAlignment(0);
    table = new JTable();
    table.setSelectionBackground(new Color(220, 220, 220));
    table.setCursor(Cursor.getPredefinedCursor(0));
    table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    table.setBackground(new Color(255, 204, 51));
    table.setModel(new DefaultTableModel(IdiomasPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Tempo Dedicado", "Idioma" }) {
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
    HomeView.getPanelIdiomas().add(panelCronometro);
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
            IdiomasPanel.iniciarActionPerformed(e);
            IdiomasPanel.controle = true;
          }
        });
    btnIniciar.setFont(new Font("Tahoma", 0, 12));
    btnIniciar.setBounds(24, 190, 75, 30);
    panelCronometro.add(btnIniciar);
    btnPausar = new JButton("Pausar");
    btnPausar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pausar")) {
              IdiomasPanel.pausarActionPerformed(e);
            } else {
              IdiomasPanel.continuarActionPerformed(e);
            } 
          }
        });
    btnPausar.setFont(new Font("Tahoma", 0, 12));
    btnPausar.setBounds(121, 190, 75, 30);
    panelCronometro.add(btnPausar);
    btnFinalizarCronometro = new JButton("Finalizar");
    btnFinalizarCronometro.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            IdiomasPanel.panelCronometro.setVisible(false);
            IdiomasPanel.panelFinalizar.setVisible(true);
            IdiomasPanel.objIdiomas.setTempoGasto(IdiomasPanel.formataTime());
            IdiomasPanel.carregaDadosFinalizar();
            IdiomasPanel.finalizarActionPerformed();
          }
        });
    btnFinalizarCronometro.setFont(new Font("Tahoma", 0, 12));
    btnFinalizarCronometro.setBounds(217, 190, 75, 30);
    btnFinalizarCronometro.setEnabled(false);
    panelCronometro.add(btnFinalizarCronometro);
    panelFinalizar = new JPanel();
    panelFinalizar.setBounds(926, 92, 318, 253);
    HomeView.getPanelIdiomas().add(panelFinalizar);
    panelFinalizar.setLayout((LayoutManager)null);
    panelFinalizar.setBackground(Color.LIGHT_GRAY);
    panelFinalizar.setVisible(false);
    btnFinalizarTudo = new JButton("Finalizar");
    btnFinalizarTudo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            IdiomasPanelController.gravaIdioma();
            IdiomasPanel.recarregaTable();
            IdiomasPanel.controle = false;
          }
        });
    btnFinalizarTudo.setBounds(187, 195, 86, 34);
    panelFinalizar.add(btnFinalizarTudo);
    JLabel lblNomeIdiomaFinalizar = new JLabel("Nome do Idioma:");
    lblNomeIdiomaFinalizar.setFont(new Font("Tahoma", 0, 14));
    lblNomeIdiomaFinalizar.setBounds(22, 30, 130, 20);
    panelFinalizar.add(lblNomeIdiomaFinalizar);
    txtNomeIdiomaFinalizar = new JTextField();
    txtNomeIdiomaFinalizar.setEditable(false);
    txtNomeIdiomaFinalizar.setColumns(10);
    txtNomeIdiomaFinalizar.setBounds(22, 55, 268, 20);
    panelFinalizar.add(txtNomeIdiomaFinalizar);
    lblTempoDedicado = new JLabel("Tempo Dedicado:");
    lblTempoDedicado.setFont(new Font("Tahoma", 0, 14));
    lblTempoDedicado.setBounds(103, 107, 112, 20);
    panelFinalizar.add(lblTempoDedicado);
    txtTempoDedicado = new JTextField();
    txtTempoDedicado.setEditable(false);
    txtTempoDedicado.setBounds(103, 132, 112, 20);
    panelFinalizar.add(txtTempoDedicado);
    txtTempoDedicado.setColumns(10);
    btnAlterarIdioma = new JButton("Alterar Idioma");
    btnAlterarIdioma.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            IdiomasPanel.lblMensagemFinalizar.setVisible(false);
            IdiomasPanel.panelFinalizar.setVisible(false);
            IdiomasPanel.panelForm.setVisible(true);
            IdiomasPanel.btnProximo.setText("Ajustar");
          }
        });
    btnAlterarIdioma.setBounds(45, 195, 135, 34);
    panelFinalizar.add(btnAlterarIdioma);
    lblMensagemFinalizar = new JLabel("Null");
    lblMensagemFinalizar.setForeground(Color.RED);
    lblMensagemFinalizar.setHorizontalAlignment(0);
    lblMensagemFinalizar.setBounds(39, 175, 240, 14);
    panelFinalizar.add(lblMensagemFinalizar);
    panelForm = new JPanel();
    panelForm.setBackground(Color.LIGHT_GRAY);
    panelForm.setBounds(926, 92, 318, 253);
    HomeView.getPanelIdiomas().add(panelForm);
    panelForm.setLayout((LayoutManager)null);
    comboMeusIdiomas = new JComboBox();
    comboMeusIdiomas.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione..." }));
    comboMeusIdiomas.setBounds(22, 57, 200, 20);
    comboMeusIdiomas.addItem("Ingl\u00EAs");
    comboMeusIdiomas.addItem("Espanhol");
    comboMeusIdiomas.addItem("Franc\u00EAs");
    panelForm.add(comboMeusIdiomas);
    btnProximo = new JButton("Pr\u00F3ximo");
    btnProximo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            IdiomasPanel.actionProximo(e);
          }
        });
    btnProximo.setBounds(114, 206, 89, 29);
    panelForm.add(btnProximo);
    lblSelecioneIdioma = new JLabel("Selecione o Idioma:");
    lblSelecioneIdioma.setBounds(22, 33, 121, 14);
    panelForm.add(lblSelecioneIdioma);
    lblSelecioneIdioma.setFont(new Font("Tahoma", 0, 14));
    lblMensagem = new JLabel("Null");
    lblMensagem.setForeground(Color.RED);
    lblMensagem.setHorizontalAlignment(0);
    lblMensagem.setAlignmentX(0.5F);
    lblMensagem.setBounds(41, 184, 235, 14);
    lblMensagem.setVisible(false);
    panelForm.add(lblMensagem);
    separator_1 = new JSeparator();
    separator_1.setBorder(new BevelBorder(0, null, null, null, null));
    separator_1.setBounds(10, 18, 298, 2);
    panelForm.add(separator_1);
  }
  
  public static void actionProximo(ActionEvent e) {
    if (comboMeusIdiomas.getSelectedItem().equals("Selecione...")) {
      lblMensagem.setText("*Informe o Idioma");
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
  
  public static void carregaForm() {
    objIdiomas.setIdioma(comboMeusIdiomas.getSelectedItem().toString());
    Calendar cal = DatasUtil.getCalendarDate();
    Date sqlDate = new Date(cal.getTimeInMillis());
    objIdiomas.setData(sqlDate);
    objIdiomas.setUsuario(UserSession.getUser());
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
    txtNomeIdiomaFinalizar.setText(objIdiomas.getIdioma());
    txtTempoDedicado.setText(objIdiomas.getTempoGasto());
    lblMensagemFinalizar.setVisible(false);
  }
  
  public static void resetPanel() {
    objIdiomas = new Idiomas();
    panelCronometro.setVisible(false);
    panelFinalizar.setVisible(false);
    panelForm.setVisible(true);
    comboMeusIdiomas.setSelectedIndex(0);
    btnProximo.setText("Pr\u00F3ximo");
  }
  
  public static void recarregaTable() {
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    centralizado.setHorizontalAlignment(0);
    table = new JTable();
    table.setSelectionBackground(new Color(220, 220, 220));
    table.setCursor(Cursor.getPredefinedCursor(0));
    table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    table.setBackground(new Color(255, 204, 51));
    table.setModel(new DefaultTableModel(IdiomasPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Tempo Dedicado", "Idioma" }) {
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
    IdiomasPanel.table = table;
  }
  
  public static JScrollPane getScrollPane() {
    return scrollPane;
  }
  
  public static void setScrollPane(JScrollPane scrollPane) {
    IdiomasPanel.scrollPane = scrollPane;
  }
  
  public static JPanel getPanelForm() {
    return panelForm;
  }
  
  public static void setPanelForm(JPanel panelForm) {
    IdiomasPanel.panelForm = panelForm;
  }
  
  public static JPanel getPanelFinalizar() {
    return panelFinalizar;
  }
  
  public static void setPanelFinalizar(JPanel panelFinalizar) {
    IdiomasPanel.panelFinalizar = panelFinalizar;
  }
  
  public static JPanel getPanelCronometro() {
    return panelCronometro;
  }
  
  public static void setPanelCronometro(JPanel panelCronometro) {
    IdiomasPanel.panelCronometro = panelCronometro;
  }
  
  public static JButton getBtnEditarIdioma() {
    return btnAlterarIdioma;
  }
  
  public static void setBtnEditarIdioma(JButton btnEditarLivro) {
    btnAlterarIdioma = btnEditarLivro;
  }
  
  public static JButton getBtnProximo() {
    return btnProximo;
  }
  
  public static void setBtnProximo(JButton btnProximo) {
    IdiomasPanel.btnProximo = btnProximo;
  }
  
  public static JButton getBtnFinalizarTudo() {
    return btnFinalizarTudo;
  }
  
  public static void setBtnFinalizarTudo(JButton btnFinalizarTudo) {
    IdiomasPanel.btnFinalizarTudo = btnFinalizarTudo;
  }
  
  public static JButton getBtnPausar() {
    return btnPausar;
  }
  
  public static void setBtnPausar(JButton btnPausar) {
    IdiomasPanel.btnPausar = btnPausar;
  }
  
  public static JButton getBtnIniciar() {
    return btnIniciar;
  }
  
  public static void setBtnIniciar(JButton btnIniciar) {
    IdiomasPanel.btnIniciar = btnIniciar;
  }
  
  public static JButton getBtnFinalizarCronometro() {
    return btnFinalizarCronometro;
  }
  
  public static void setBtnFinalizarCronometro(JButton btnFinalizarCronometro) {
    IdiomasPanel.btnFinalizarCronometro = btnFinalizarCronometro;
  }
  
  public static JLabel getLblTempoDedicado() {
    return lblTempoDedicado;
  }
  
  public static void setLblTempoDedicado(JLabel lblTempoDedicado) {
    IdiomasPanel.lblTempoDedicado = lblTempoDedicado;
  }
  
  public static JLabel getLblMeusIdiomas() {
    return lblSelecioneIdioma;
  }
  
  public static void setLblMeusIdiomas(JLabel lblMeusIdiomas) {
    lblSelecioneIdioma = lblMeusIdiomas;
  }
  
  public static JLabel getLblMensagem() {
    return lblMensagem;
  }
  
  public static void setLblMensagem(JLabel lblMensagem) {
    IdiomasPanel.lblMensagem = lblMensagem;
  }
  
  public static JLabel getLblMensagemFinalizar() {
    return lblMensagemFinalizar;
  }
  
  public static void setLblMensagemFinalizar(JLabel lblMensagemFinalizar) {
    IdiomasPanel.lblMensagemFinalizar = lblMensagemFinalizar;
  }
  
  public static JTextField getTxtNomeIdiomaFinalizar() {
    return txtNomeIdiomaFinalizar;
  }
  
  public static void setTxtNomeIdiomaFinalizar(JTextField txtNomeIdiomaFinalizar) {
    IdiomasPanel.txtNomeIdiomaFinalizar = txtNomeIdiomaFinalizar;
  }
  
  public static JTextField getTxtTempoDedicado() {
    return txtTempoDedicado;
  }
  
  public static void setTxtTempoDedicado(JTextField txtTempoDedicado) {
    IdiomasPanel.txtTempoDedicado = txtTempoDedicado;
  }
  
  public static JComboBox getComboMeusIdiomas() {
    return comboMeusIdiomas;
  }
  
  public static void setComboMeusIdiomas(JComboBox comboMeusIdiomas) {
    IdiomasPanel.comboMeusIdiomas = comboMeusIdiomas;
  }
  
  public static Idiomas getObjIdiomas() {
    return objIdiomas;
  }
  
  public static void setObjIdiomas(Idiomas objIdiomas) {
    IdiomasPanel.objIdiomas = objIdiomas;
  }
  
  public static JLabel getLblTime() {
    return lblTime;
  }
  
  public static void setLblTime(JLabel lblTime) {
    IdiomasPanel.lblTime = lblTime;
  }
  
  public static boolean isControle() {
    return controle;
  }
  
  public static void setControle(boolean controle) {
    IdiomasPanel.controle = controle;
  }
}
