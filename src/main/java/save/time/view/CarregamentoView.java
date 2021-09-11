package save.time.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import save.time.ferramentas.ApoioRedimensionarImagem;

public class CarregamentoView {
	
	private static JFrame carregamentoFrame;

	public CarregamentoView() {
		initialize();
	}

	private void initialize() {
		
		carregamentoFrame = new JFrame();
		carregamentoFrame.setUndecorated(true);
		carregamentoFrame.setFocusTraversalPolicyProvider(true);
		carregamentoFrame.setDefaultCloseOperation(3);
		carregamentoFrame.setAlwaysOnTop(true);
		carregamentoFrame.setResizable(false);
		carregamentoFrame.setType(Window.Type.UTILITY);
		carregamentoFrame.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
		carregamentoFrame.getContentPane().setFocusTraversalKeysEnabled(false);
		carregamentoFrame.setTitle("Iniciando...");
		carregamentoFrame.setSize(new Dimension(400, 230));
		carregamentoFrame.setLocation(490, 240);
		carregamentoFrame.getContentPane().setLayout((LayoutManager) null);
		carregamentoFrame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(115, 16, 150, 150);
		carregamentoFrame.getContentPane().add(lblLogo);
		
		ApoioRedimensionarImagem resize = new ApoioRedimensionarImagem();
		ImageIcon imagem = new ImageIcon(HomeView.class.getResource("/img/logocolorido.png"));
		imagem = resize.scaleImage(imagem, 150, 150);
		lblLogo.setIcon(imagem);
		
		JLabel lblCarregando = new JLabel("Carregando...");
		lblCarregando.setForeground(new Color(255, 204, 0));
		lblCarregando.setFont(new Font("Sitka Banner", 1, 30));
		lblCarregando.setHorizontalAlignment(0);
		lblCarregando.setBounds(108, 180, 183, 39);
		
		carregamentoFrame.getContentPane().add(lblCarregando);
		carregamentoFrame.setVisible(true);
	}

	public static JFrame getCarregamentoFrame() {
		return carregamentoFrame;
	}

	public static void setCarregamentoFrame(JFrame carregamentoFrame) {
		CarregamentoView.carregamentoFrame = carregamentoFrame;
	}
}
