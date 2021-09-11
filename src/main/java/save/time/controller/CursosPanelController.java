package save.time.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.lang.Object;
import javax.swing.JOptionPane;
import save.time.dao.CursosDAO;
import save.time.entity.Cursos;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.CursosPanel;

public class CursosPanelController {

	public static String verificaCursoString(int idUsuario, String curso) {
		String cursos = CursosDAO.verificaCurso(idUsuario, curso);
		return cursos;
	}

	public static Cursos verificaCursoMaisRecente(int idUsuario, String curso) {

		List<Cursos> listLivros = CursosDAO.verificaCursoRecente(idUsuario, curso);
		Iterator it = listLivros.iterator();
		Cursos infoCurso = new Cursos();

		while (it.hasNext()) {
			Object[] line = (Object[]) it.next();
			infoCurso.setAuAtual((Integer) line[0]);
			infoCurso.setAuTotal((Integer) line[1]);
		}

		return infoCurso;
	}

	public static void gravaCurso() {

		try {
			if (Integer.parseInt(CursosPanel.getTxtTotalAulasFinalizar().getText()) == Integer.parseInt(CursosPanel.getTxtAulaAtualFinalizar().getText())) {
				
				Calendar cal = DatasUtil.getCalendarDate();
				Date sqlDate = new Date(cal.getTimeInMillis());
				CursosPanel.getObjCursos().setConclusao(sqlDate);
			}
			
			AuxiliarBd.persist(CursosPanel.getObjCursos());
			JOptionPane.showMessageDialog(CursosPanel.getPanelFinalizar(), "Registro adicionado com sucesso!");
			CursosPanel.resetPanel();
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(CursosPanel.getPanelFinalizar(), "Problema: " + e2);
		}

	}

	public static Object[][] carregaTable() {
		
		List<Cursos> cursos = new ArrayList<Cursos>();
		cursos = CursosDAO.buscaCursosUser(UserSession.getUser().getId());
		Object[][] tabela = null;
		
		if (cursos.size() < 27) {
			tabela = new Object[27][6];
		} else {
			tabela = new Object[cursos.size()][6];
		}
		
		for (int i = 0; i < cursos.size(); i++) {
			tabela[i][0] = DatasUtil.convertAndFormatToString(((Cursos) cursos.get(i)).getData(), "dd/MM/yyyy");
			tabela[i][1] = ((Cursos) cursos.get(i)).getTempoGasto();
			tabela[i][2] = ((Cursos) cursos.get(i)).getCurso();
			tabela[i][3] = Integer.valueOf(((Cursos) cursos.get(i)).getAuTotal());
			tabela[i][4] = Integer.valueOf(((Cursos) cursos.get(i)).getAuAtual());
			
			if (((Cursos) cursos.get(i)).getConclusao() != null)
				tabela[cursos.size() - 1 - i][5] = DatasUtil.convertAndFormatToString(((Cursos) cursos.get(i)).getConclusao(), "dd/MM/yyyy");
		}
		return tabela;
	}

	public static void buscaCursos() {
		
		List<String> cursos = new ArrayList<String>();
		cursos = CursosDAO.buscaCursosUnique(UserSession.getUser().getId());
		
		for (int i = 0; i < cursos.size(); i++)
			CursosPanel.getComboMeusCursos().addItem(cursos.get(i));
	}
}
