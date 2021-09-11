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
import save.time.controller.DietasPanelController;
import save.time.entity.Dietas;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;

public class DietasPanel {
  private static JTable table;
  
  private static JScrollPane scrollPane;
  
  private static JPanel panelForm;
  
  private static JButton btnAlterarDados;
  
  private static JButton btnProximo;
  
  private static JLabel lblHorario;
  
  private static JLabel lblRefeicao;
  
  private static JLabel lblAlimento;
  
  private static JLabel lblQuantidade;
  
  private static JLabel lblMensagem;
  
  private static JTextField txtAlimento;
  
  private static JTextField txtQuantidade;
  
  private static JComboBox comboHorario;
  
  private static JComboBox comboRefeicao;
  
  private static JSeparator separator_1;
  
  private static Dietas objDietas = new Dietas();
  
  private static boolean controle = false;
  
  public static void carregarDietas() {
    scrollPane = new JScrollPane();
    scrollPane.setBorder(new BevelBorder(1, null, null, null, null));
    scrollPane.setBackground(Color.DARK_GRAY);
    scrollPane.setBounds(42, 59, 773, 564);
    HomeView.getPanelDietas().add(scrollPane);
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    centralizado.setHorizontalAlignment(0);
    table = new JTable();
    table.setSelectionBackground(new Color(220, 220, 220));
    table.setCursor(Cursor.getPredefinedCursor(0));
    table.setBorder(new BevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    table.setBackground(new Color(255, 204, 51));
    table.setModel(new DefaultTableModel(DietasPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Hor\u00E1rio", "Refei\u00E7\u00E3o", "Alimento", "Quantidade" }) {
          Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class };
          
          public Class getColumnClass(int columnIndex) {
            return this.columnTypes[columnIndex];
          }
        });
    table.getColumnModel().getColumn(3).setPreferredWidth(200);
    table.getColumnModel().getColumn(4).setPreferredWidth(180);
    table.setRowHeight(20);
    table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
    scrollPane.setViewportView(table);
    panelForm = new JPanel();
    panelForm.setBackground(Color.LIGHT_GRAY);
    panelForm.setBounds(926, 92, 318, 253);
    HomeView.getPanelDietas().add(panelForm);
    panelForm.setLayout((LayoutManager)null);
    btnAlterarDados = new JButton("Alterar Dados");
    btnAlterarDados.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DietasPanel.alterarDados();
          }
        });
    btnAlterarDados.setBounds(45, 195, 135, 34);
    btnAlterarDados.setVisible(false);
    panelForm.add(btnAlterarDados);
    comboHorario = new JComboBox();
    comboHorario.setModel(new DefaultComboBoxModel());
    comboHorario.setBounds(22, 57, 80, 20);
    DietasPanelController.recarregaComboHorario();
    panelForm.add(comboHorario);
    lblHorario = new JLabel("Hor\u00E1rio:");
    lblHorario.setFont(new Font("Tahoma", 0, 14));
    lblHorario.setBounds(22, 33, 121, 14);
    panelForm.add(lblHorario);
    btnProximo = new JButton("Pr\u00F3ximo");
    btnProximo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pr\u00F3ximo")) {
              DietasPanel.actionProximo(e);
              DietasPanel.controle = true;
            } else {
              DietasPanel.carregaForm();
              DietasPanelController.gravaDietas();
              DietasPanel.recarregaTable();
              DietasPanel.resetPanel();
              DietasPanel.controle = false;
            } 
          }
        });
    btnProximo.setBounds(114, 206, 89, 29);
    panelForm.add(btnProximo);
    lblRefeicao = new JLabel("Refei\u00E7\u00E3o:");
    lblRefeicao.setBounds(166, 33, 121, 14);
    panelForm.add(lblRefeicao);
    lblRefeicao.setFont(new Font("Tahoma", 0, 14));
    comboRefeicao = new JComboBox();
    comboRefeicao.setModel(new DefaultComboBoxModel());
    comboRefeicao.setBounds(166, 57, 130, 20);
    DietasPanelController.recarregaComboRefeicao();
    panelForm.add(comboRefeicao);
    lblAlimento = new JLabel("Alimento:");
    lblAlimento.setBounds(22, 85, 121, 20);
    panelForm.add(lblAlimento);
    lblAlimento.setFont(new Font("Tahoma", 0, 14));
    txtAlimento = new JTextField();
    txtAlimento.setBounds(22, 109, 200, 20);
    panelForm.add(txtAlimento);
    txtAlimento.setColumns(10);
    lblQuantidade = new JLabel("Quantidade:");
    lblQuantidade.setBounds(22, 133, 121, 20);
    panelForm.add(lblQuantidade);
    lblQuantidade.setFont(new Font("Tahoma", 0, 14));
    txtQuantidade = new JTextField();
    txtQuantidade.setBounds(22, 157, 120, 20);
    panelForm.add(txtQuantidade);
    txtQuantidade.setColumns(10);
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
    if (txtAlimento.getText().equals("") || comboRefeicao.getSelectedItem().equals("Selecione...") || txtQuantidade.getText().equals("")) {
      lblMensagem.setText("*Verifique os campos");
      lblMensagem.setVisible(true);
    } else {
      txtAlimento.setEditable(false);
      txtQuantidade.setEditable(false);
      comboHorario.setEnabled(false);
      comboRefeicao.setEnabled(false);
      lblMensagem.setVisible(false);
      btnAlterarDados.setVisible(true);
      btnProximo.setText("Finalizar");
      btnProximo.setBounds(187, 195, 86, 34);
    } 
  }
  
  public static void carregaForm() {
    Calendar cal = DatasUtil.getCalendarDate();
    Date sqlDate = new Date(cal.getTimeInMillis());
    objDietas.setUsuario(UserSession.getUser());
    objDietas.setData(sqlDate);
    objDietas.setHorario(comboHorario.getSelectedItem().toString());
    objDietas.setRefeicao(comboRefeicao.getSelectedItem().toString());
    objDietas.setAlimento(txtAlimento.getText());
    objDietas.setQuantidade(txtQuantidade.getText());
  }
  
  public static void resetPanel() {
    objDietas = new Dietas();
    comboHorario.setSelectedIndex(0);
    comboRefeicao.setSelectedIndex(0);
    txtAlimento.setText("");
    txtQuantidade.setText("");
    comboHorario.setEnabled(true);
    comboRefeicao.setEnabled(true);
    txtAlimento.setEditable(true);
    txtQuantidade.setEditable(true);
    btnProximo.setText("Pr\u00F3ximo");
    btnProximo.setBounds(114, 206, 89, 29);
    btnAlterarDados.setVisible(false);
  }
  
  private static void alterarDados() {
    txtAlimento.setEditable(true);
    txtQuantidade.setEditable(true);
    comboHorario.setEnabled(true);
    comboRefeicao.setEnabled(true);
    btnAlterarDados.setVisible(false);
    btnProximo.setBounds(114, 206, 89, 29);
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
    table.setModel(new DefaultTableModel(DietasPanelController.carregaTable(), 
          (Object[])new String[] { "Data", "Hor\u00E1rio", "Refei\u00E7\u00E3o", "Alimento", "Quantidade" }) {
          Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class };
          
          public Class getColumnClass(int columnIndex) {
            return this.columnTypes[columnIndex];
          }
        });
    table.revalidate();
    table.getColumnModel().getColumn(3).setPreferredWidth(200);
    table.getColumnModel().getColumn(4).setPreferredWidth(180);
    table.setRowHeight(20);
    table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
    table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
    scrollPane.setViewportView(table);
    scrollPane.revalidate();
  }
  
  public static JTable getTable() {
    return table;
  }
  
  public static void setTable(JTable table) {
    DietasPanel.table = table;
  }
  
  public static JScrollPane getScrollPane() {
    return scrollPane;
  }
  
  public static void setScrollPane(JScrollPane scrollPane) {
    DietasPanel.scrollPane = scrollPane;
  }
  
  public static JPanel getPanelForm() {
    return panelForm;
  }
  
  public static void setPanelForm(JPanel panelForm) {
    DietasPanel.panelForm = panelForm;
  }
  
  public static JButton getBtnEditarAtividade() {
    return btnAlterarDados;
  }
  
  public static void setBtnEditarAtividade(JButton btnEditarLivro) {
    btnAlterarDados = btnEditarLivro;
  }
  
  public static JButton getBtnProximo() {
    return btnProximo;
  }
  
  public static void setBtnProximo(JButton btnProximo) {
    DietasPanel.btnProximo = btnProximo;
  }
  
  public static JLabel getLblNomeAtividade() {
    return lblHorario;
  }
  
  public static void setLblNomeAtividade(JLabel lblNomeAtividade) {
    lblHorario = lblNomeAtividade;
  }
  
  public static JLabel getLblMinhasAtividades() {
    return lblRefeicao;
  }
  
  public static void setLblMinhasAtividades(JLabel lblMinhasAtividades) {
    lblRefeicao = lblMinhasAtividades;
  }
  
  public static JLabel getLblMensagem() {
    return lblMensagem;
  }
  
  public static void setLblMensagem(JLabel lblMensagem) {
    DietasPanel.lblMensagem = lblMensagem;
  }
  
  public static JComboBox getComboHorario() {
    return comboHorario;
  }
  
  public static void setComboHorario(JComboBox comboHorario) {
    DietasPanel.comboHorario = comboHorario;
  }
  
  public static JSeparator getSeparator_1() {
    return separator_1;
  }
  
  public static void setSeparator_1(JSeparator separator_1) {
    DietasPanel.separator_1 = separator_1;
  }
  
  public static Dietas getObjDietas() {
    return objDietas;
  }
  
  public static void setObjDietas(Dietas objDietas) {
    DietasPanel.objDietas = objDietas;
  }
  
  public static boolean isControle() {
    return controle;
  }
  
  public static void setControle(boolean controle) {
    DietasPanel.controle = controle;
  }
  
  public static JComboBox getComboRefeicao() {
    return comboRefeicao;
  }
  
  public static void setComboRefeicao(JComboBox comboRefeicao) {
    DietasPanel.comboRefeicao = comboRefeicao;
  }
}
