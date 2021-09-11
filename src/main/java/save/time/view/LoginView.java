package save.time.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import save.time.controller.LoginController;
import save.time.entity.User;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.test.TesteView;

public class LoginView {
  private static JFrame loginFrame;
  
  private static JTextField userField;
  
  private static JPasswordField passwordField;
  
  private static JLabel lblMensagem;
  
  private static Panel panelLogin;
  
  public LoginView() {
    initialize();
  }
  
  private void initialize() {
    loginFrame = new JFrame();
    loginFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(TesteView.class.getResource("/img/logoicon.png")));
    loginFrame.setResizable(false);
    loginFrame.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
    loginFrame.getContentPane().setFocusTraversalKeysEnabled(false);
    loginFrame.setTitle("Login");
    loginFrame.setSize(new Dimension(1372, 735));
    loginFrame.setDefaultCloseOperation(3);
    loginFrame.setLocation(-3, -1);
    loginFrame.getContentPane().setLayout((LayoutManager)null);
    loginFrame.getContentPane().setBackground(Color.DARK_GRAY);
    panelLogin = new Panel();
    panelLogin.setBackground(Color.GRAY);
    panelLogin.setBounds(525, 110, 321, 440);
    loginFrame.getContentPane().add(panelLogin);
    panelLogin.setLayout((LayoutManager)null);
    JLabel label = new JLabel("Usu\u00E1rio:");
    label.setFont(new Font("Tahoma", 0, 14));
    label.setBounds(67, 227, 74, 14);
    panelLogin.add(label);
    userField = new JTextField();
    userField.setFont(new Font("Tahoma", 0, 14));
    userField.setBounds(65, 248, 195, 23);
    userField.setColumns(10);
    panelLogin.add(userField);
    JButton button = new JButton("Entrar");
    button.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent arg0) {
            if (arg0.getKeyChar() == '\n')
              LoginView.this.validaLogin(); 
          }
        });
    button.setFont(new Font("Tahoma", 0, 14));
    button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            LoginView.this.validaLogin();
          }
        });
    button.setBounds(123, 364, 79, 25);
    panelLogin.add(button);
    JLabel label_1 = new JLabel("Senha:");
    label_1.setFont(new Font("Tahoma", 0, 14));
    label_1.setBounds(67, 282, 59, 14);
    panelLogin.add(label_1);
    passwordField = new JPasswordField();
    passwordField.setBounds(65, 303, 195, 23);
    panelLogin.add(passwordField);
    JLabel lblNomeSistema = new JLabel("Save Time");
    lblNomeSistema.setFont(new Font("Tahoma", 1, 24));
    lblNomeSistema.setBounds(106, 120, 150, 150);
    lblNomeSistema.setForeground(Color.ORANGE);
    panelLogin.add(lblNomeSistema);
    ApoioRedimensionarImagem resize = new ApoioRedimensionarImagem();
    JLabel lblSaveTime = new JLabel("");
    lblSaveTime.setFont(new Font("Tahoma", 0, 24));
    lblSaveTime.setBounds(89, 25, 150, 150);
    ImageIcon imagem = new ImageIcon(getClass().getResource("/img/logocolorido.png"));
    imagem = resize.scaleImage(imagem, 150, 150);
    lblSaveTime.setIcon(imagem);
    panelLogin.add(lblSaveTime);
    lblMensagem = new JLabel("null");
    lblMensagem.setHorizontalAlignment(0);
    lblMensagem.setForeground(new Color(255, 0, 0));
    lblMensagem.setFont(new Font("Tahoma", 0, 12));
    lblMensagem.setBounds(83, 334, 159, 14);
    lblMensagem.setVisible(false);
    panelLogin.add(lblMensagem);
    loginFrame.setVisible(true);
  }
  
  private void validaLogin() {
    char[] passwordChar = passwordField.getPassword();
    String passwordString = "";
    for (int i = 0; i < passwordChar.length; i++)
      passwordString = String.valueOf(passwordString) + passwordChar[i]; 
    if (userField.getText().equals("") && passwordString == "") {
      lblMensagem.setText("*Verifique os campos");
      lblMensagem.setVisible(true);
    } else if (userField.getText().equals("")) {
      lblMensagem.setText("*Informe o Usu\uFFFDrio");
      lblMensagem.setVisible(true);
    } else if (passwordString == "") {
      lblMensagem.setText("*Informe a senha");
      lblMensagem.setVisible(true);
    } else {
      lblMensagem.setVisible(false);
      User user = new User();
      user.setLogin(userField.getText());
      user.setSenha(passwordString);
      if (LoginController.Login(user)) {
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } 
        panelLogin.setVisible(false);
        HomeView.carregaHome();
      } 
    } 
  }
  
  public static JFrame getLoginFrame() {
    return loginFrame;
  }
  
  public static void setLoginFrame(JFrame loginFrame) {
    LoginView.loginFrame = loginFrame;
  }
  
  public static JTextField getUserField() {
    return userField;
  }
  
  public static void setUserField(JTextField userField) {
    LoginView.userField = userField;
  }
  
  public static JPasswordField getPasswordField() {
    return passwordField;
  }
  
  public static void setPasswordField(JPasswordField passwordField) {
    LoginView.passwordField = passwordField;
  }
  
  public static JLabel getLblMensagem() {
    return lblMensagem;
  }
  
  public static void setLblMensagem(JLabel lblMensagem) {
    LoginView.lblMensagem = lblMensagem;
  }
  
  public static Panel getPanelLogin() {
    return panelLogin;
  }
  
  public static void setPanelLogin(Panel panelLogin) {
    LoginView.panelLogin = panelLogin;
  }
}
