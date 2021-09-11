package save.time.ferramentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class testeTimerdda extends JFrame {
  private static final long serialVersionUID = 1L;
  
  private static boolean contador = false;
  
  private static boolean zerado = false;
  
  private JButton iniciar;
  
  private static JLabel lConta;
  
  private JPanel mainPanel;
  
  private JButton pausar;
  
  private JButton zerar;
  
  public static void main(String[] args) {
    (new testeTimerdda()).montaTela();
    Contador cont = new Contador();
    cont.start();
  }
  
  private void montaTela() {
    setTitle("Cronometro");
    setDefaultCloseOperation(3);
    setSize(240, 120);
    this.mainPanel = new JPanel();
    lConta = new JLabel();
    this.iniciar = new JButton();
    this.pausar = new JButton();
    this.zerar = new JButton();
    this.mainPanel.setName("mainPanel");
    lConta.setText("00:00:00");
    this.iniciar.setText(">");
    this.iniciar.setName("iniciar");
    this.iniciar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            testeTimerdda.this.iniciarActionPerformed(evt);
          }
        });
    this.pausar.setText("ii");
    this.pausar.setName("pausar");
    this.pausar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            testeTimerdda.this.pausarActionPerformed(evt);
          }
        });
    this.zerar.setText("zerar");
    this.zerar.setName("zerar");
    this.zerar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            testeTimerdda.this.zerarActionPerformed(evt);
          }
        });
    GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
    this.mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
        mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(mainPanelLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lConta, -1, 273, 32767)
            .addGroup(mainPanelLayout.createSequentialGroup()
              .addComponent(this.iniciar)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, 32767)
              .addComponent(this.pausar)
              .addGap(32, 32, 32)
              .addComponent(this.zerar)))
          .addContainerGap()));
    mainPanelLayout.setVerticalGroup(
        mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(mainPanelLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(lConta)
          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
          .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.iniciar)
            .addComponent(this.zerar)
            .addComponent(this.pausar))
          .addContainerGap(-1, 32767)));
    add(this.mainPanel);
    setVisible(true);
  }
  
  private void iniciarActionPerformed(ActionEvent evt) {
    contador = true;
    this.iniciar.setEnabled(false);
    this.pausar.setEnabled(true);
    this.zerar.setEnabled(false);
  }
  
  private void pausarActionPerformed(ActionEvent evt) {
    contador = false;
    this.iniciar.setEnabled(true);
    this.pausar.setEnabled(false);
    this.zerar.setEnabled(true);
  }
  
  private void zerarActionPerformed(ActionEvent evt) {
    contador = false;
    lConta.setText("00:00:00");
    zerado = true;
    lConta.revalidate();
    this.iniciar.setEnabled(true);
    this.pausar.setEnabled(false);
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
}
