package save.time.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import save.time.controller.HomeController;
import save.time.ferramentas.ApoioRedimensionarImagem;
import save.time.session.UserSession;

public class HomeView {
	
	private static JTabbedPane tabbedPane;

	private static JPanel panelHome;

	private static JPanel panelExercicios;

	private static JPanel panelCursos;

	private static JPanel panelIdiomas;

	private static JPanel panelLeituras;

	private static JPanel panelEspiritual;

	private static JPanel panelDietas;

	private static JLabel lblBemVindo;

	private static JLabel lblLogo;

	private static JButton btnSair;

	private static boolean recarrega = false;

	public static void carregaHome() {
		LoginView.getLoginFrame().revalidate();
		tabbedPane = new JTabbedPane(1);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBounds(0, 0, 1366, 706);
		LoginView.getLoginFrame().getContentPane().add(tabbedPane);
		panelHome = new JPanel();
		panelHome.setBackground(Color.DARK_GRAY);
		panelHome.setLayout((LayoutManager) null);
		panelHome.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(0);
		lblLogo.setBounds(568, 130, 225, 225);
		ApoioRedimensionarImagem resize = new ApoioRedimensionarImagem();
		ImageIcon imagem = new ImageIcon(HomeView.class.getResource("/img/logocolorido.png"));
		imagem = resize.scaleImage(imagem, 225, 225);
		lblLogo.setIcon(imagem);
		panelHome.add(lblLogo);
		lblBemVindo = new JLabel("Bem vindo(a), "
				+ UserSession.getUser().getNome().substring(0, UserSession.getUser().getNome().lastIndexOf(" ")) + "!");
		lblBemVindo.setHorizontalAlignment(0);
		lblBemVindo.setForeground(Color.ORANGE);
		lblBemVindo.setFont(new Font("Tahoma", 0, 18));
		lblBemVindo.setBounds(449, 375, 463, 62);
		panelHome.add(lblBemVindo);
		JLabel lblMensagemInicial = new JLabel("Eu sou a Save Time, sua nova plataforma organizacional.");
		lblMensagemInicial.setForeground(Color.ORANGE);
		lblMensagemInicial.setHorizontalAlignment(0);
		lblMensagemInicial.setFont(new Font("Tahoma", 0, 18));
		lblMensagemInicial.setBounds(449, 425, 463, 62);
		panelHome.add(lblMensagemInicial);
		tabbedPane.addTab("Home", (Icon) null, panelHome, (String) null);
		tabbedPane.setEnabledAt(0, true);
		panelLeituras = new JPanel();
		panelLeituras.setBackground(Color.DARK_GRAY);
		panelLeituras.setLayout((LayoutManager) null);
		panelLeituras.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Leituras", (Icon) null, panelLeituras, (String) null);
		panelCursos = new JPanel();
		panelCursos.setBackground(Color.DARK_GRAY);
		panelCursos.setLayout((LayoutManager) null);
		panelCursos.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Cursos", (Icon) null, panelCursos, (String) null);
		panelExercicios = new JPanel();
		panelExercicios.setBackground(Color.DARK_GRAY);
		panelExercicios.setLayout((LayoutManager) null);
		panelExercicios.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Exercícios", (Icon) null, panelExercicios, (String) null);
		panelEspiritual = new JPanel();
		panelEspiritual.setBorder(new BevelBorder(1, null, null, null, null));
		panelEspiritual.setBackground(Color.DARK_GRAY);
		panelEspiritual.setLayout((LayoutManager) null);
		panelEspiritual.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Espiritual", (Icon) null, panelEspiritual, (String) null);
		tabbedPane.setEnabledAt(0, true);
		panelDietas = new JPanel();
		panelDietas.setBorder(new BevelBorder(1, null, null, null, null));
		panelDietas.setBackground(Color.DARK_GRAY);
		panelDietas.setLayout((LayoutManager) null);
		panelDietas.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Dietas", (Icon) null, panelDietas, (String) null);
		tabbedPane.setEnabledAt(0, true);
		panelIdiomas = new JPanel();
		panelIdiomas.setBorder(new BevelBorder(1, null, null, null, null));
		panelIdiomas.setBackground(Color.DARK_GRAY);
		panelIdiomas.setLayout((LayoutManager) null);
		panelIdiomas.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				HomeView.verificaModificacoes();
			}
		});
		tabbedPane.addTab("Idiomas", (Icon) null, panelIdiomas, (String) null);
		tabbedPane.setEnabledAt(0, true);
		LoginView.getLoginFrame().getContentPane().setBackground(Color.LIGHT_GRAY);
		LoginView.getLoginFrame().getContentPane().setVisible(true);
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeView.reloadLogin();
			}
		});
		btnSair.setBounds(1280, 10, 70, 29);
		panelHome.add(btnSair);
		HomeController.carregaMenus();
	}

	public static void verificaModificacoes() {
		if (recarrega) {
			recarrega = false;
		} else if (LeiturasPanel.isControle() || ExerciciosPanel.isControle() || IdiomasPanel.isControle()
				|| EspiritualPanel.isControle() || DietasPanel.isControle() || CursosPanel.isControle()) {
			Object[] options = { "Prosseguir", "Cancelar" };
			int option = JOptionPane.showOptionDialog(getPanelHome(),
					"Existem dados não salvos, deseja prosseguir e perdê-los?", "Mensagem", -1, 1, null,
					options, options[0]);
			if (option == 0) {
				LeiturasPanel.resetPanel();
				LeiturasPanel.setControle(false);
				LeiturasPanel.finalizarActionPerformed();
				CursosPanel.resetPanel();
				CursosPanel.setControle(false);
				CursosPanel.finalizarActionPerformed();
				ExerciciosPanel.resetPanel();
				ExerciciosPanel.setControle(false);
				ExerciciosPanel.finalizarActionPerformed();
				EspiritualPanel.resetPanel();
				EspiritualPanel.setControle(false);
				EspiritualPanel.finalizarActionPerformed();
				DietasPanel.resetPanel();
				DietasPanel.setControle(false);
				IdiomasPanel.resetPanel();
				IdiomasPanel.setControle(false);
				IdiomasPanel.finalizarActionPerformed();
			} else {
				if (LeiturasPanel.isControle()) {
					tabbedPane.setSelectedIndex(1);
				} else if (CursosPanel.isControle()) {
					tabbedPane.setSelectedIndex(2);
				} else if (ExerciciosPanel.isControle()) {
					tabbedPane.setSelectedIndex(3);
				} else if (EspiritualPanel.isControle()) {
					tabbedPane.setSelectedIndex(4);
				} else if (DietasPanel.isControle()) {
					tabbedPane.setSelectedIndex(5);
				} else if (IdiomasPanel.isControle()) {
					tabbedPane.setSelectedIndex(6);
				}
				recarrega = true;
			}
		}
	}

	public static void reloadLogin() {
		LoginView.getLoginFrame().getContentPane().remove(tabbedPane);
		LoginView.getLoginFrame().getContentPane().setBackground(Color.DARK_GRAY);
		LoginView.getPanelLogin().setVisible(true);
		LoginView.getUserField().setText("");
		LoginView.getPasswordField().setText("");
		LoginView.getLblMensagem().setVisible(false);
		LoginView.getLoginFrame().setTitle("Login");
	}

	public static JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public static void setTabbedPane(JTabbedPane tabbedPane) {
		HomeView.tabbedPane = tabbedPane;
	}

	public static JPanel getPanelHome() {
		return panelHome;
	}

	public static void setPanelHome(JPanel panelHome) {
		HomeView.panelHome = panelHome;
	}

	public static JPanel getPanelExercicios() {
		return panelExercicios;
	}

	public static void setPanelExercicios(JPanel panelExercicios) {
		HomeView.panelExercicios = panelExercicios;
	}

	public static JPanel getPanelCursos() {
		return panelCursos;
	}

	public static void setPanelCursos(JPanel panelCursos) {
		HomeView.panelCursos = panelCursos;
	}

	public static JPanel getPanelIdiomas() {
		return panelIdiomas;
	}

	public static void setPanelIdiomas(JPanel panelIdiomas) {
		HomeView.panelIdiomas = panelIdiomas;
	}

	public static JPanel getPanelLeituras() {
		return panelLeituras;
	}

	public static void setPanelLeituras(JPanel panelLeituras) {
		HomeView.panelLeituras = panelLeituras;
	}

	public static JPanel getPanelEspiritual() {
		return panelEspiritual;
	}

	public static void setPanelEspiritual(JPanel panelEspiritual) {
		HomeView.panelEspiritual = panelEspiritual;
	}

	public static JPanel getPanelDietas() {
		return panelDietas;
	}

	public static void setPanelDietas(JPanel panelDietas) {
		HomeView.panelDietas = panelDietas;
	}
}
