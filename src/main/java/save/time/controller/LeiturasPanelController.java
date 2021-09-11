package save.time.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import save.time.dao.LeiturasDAO;
import save.time.entity.Leituras;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.LeiturasPanel;

public class LeiturasPanelController {
	
	public static void gravaLeitura() {
		
		try {
			if (Integer.parseInt(LeiturasPanel.getTxtTotalPaginasFinalizar().getText()) == Integer.parseInt(LeiturasPanel.getTxtPaginaAtualFinalizar().getText())) {
				Calendar cal = DatasUtil.getCalendarDate();
				Date sqlDate = new Date(cal.getTimeInMillis());
				LeiturasPanel.getObjLeituras().setConclusao(sqlDate);
			}
			
			AuxiliarBd.persist(LeiturasPanel.getObjLeituras());
			JOptionPane.showMessageDialog(LeiturasPanel.getPanelFinalizar(), "Registro adicionado com sucesso!");
			LeiturasPanel.resetPanel();
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(LeiturasPanel.getPanelFinalizar(), "Problema: " + e2);
		}
	}

	public static void buscaLivros() {
		
		List<String> livros = new ArrayList<String>();
		livros = LeiturasDAO.buscaLivrosUnique(UserSession.getUser().getId());
		
		for (int i = 0; i < livros.size(); i++)
			LeiturasPanel.getComboMeusLivros().addItem(livros.get(i));
	}

	public static String verificaLivroString(int idUsuario, String livro) {
		String livros = LeiturasDAO.verificaLivro(idUsuario, livro);
		return livros;
	}

	public static Leituras verificaPaginaMaisRecente(int idUsuario, String livro) {
		
		List<Leituras> listLivros = LeiturasDAO.verificaLivroRecente(idUsuario, livro);
		Iterator it = listLivros.iterator();
		Leituras infoLivro = new Leituras();
		
		while (it.hasNext()) {
			Object[] line = (Object[]) it.next();
			infoLivro.setPgAtual((Integer) line[0]);
			infoLivro.setPgTotal((Integer) line[1]);
		}
		return infoLivro;
	}

	public static Object[][] carregaTable() {
		
		List<Leituras> leituras = new ArrayList<Leituras>();
		leituras = LeiturasDAO.buscaLeiturasUser(UserSession.getUser().getId());
		Object[][] tabela = null;
		
		if (leituras.size() < 27) {
			tabela = new Object[27][6];
		} else {
			tabela = new Object[leituras.size()][6];
		}
		
		for (int i = 0; i < leituras.size(); i++) {
			
			tabela[i][0] = DatasUtil.convertAndFormatToString(((Leituras) leituras.get(i)).getData(), "dd/MM/yyyy");
			tabela[i][1] = ((Leituras) leituras.get(i)).getTempoGasto();
			tabela[i][2] = ((Leituras) leituras.get(i)).getNomeLivro();
			tabela[i][3] = Integer.valueOf(((Leituras) leituras.get(i)).getPgTotal());
			tabela[i][4] = Integer.valueOf(((Leituras) leituras.get(i)).getPgAtual());
			
			if (((Leituras) leituras.get(i)).getConclusao() != null)
				tabela[leituras.size() - 1 - i][5] = DatasUtil.convertAndFormatToString(((Leituras) leituras.get(i)).getConclusao(), "dd/MM/yyyy");
		}
		return tabela;
	}
}
