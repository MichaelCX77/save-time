package save.time.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import save.time.dao.ExerciciosDAO;
import save.time.entity.Exercicios;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.ExerciciosPanel;

public class ExerciciosPanelController {
	public static void gravaExercicio() {
		
		try {
			AuxiliarBd.persist(ExerciciosPanel.getObjExercicios());
			JOptionPane.showMessageDialog(ExerciciosPanel.getPanelFinalizar(), "Registro adicionado com sucesso!");
			ExerciciosPanel.resetPanel();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(ExerciciosPanel.getPanelFinalizar(), "Problema: " + e2);
		}
	}

	public static void buscaExercicios() {
		List<String> exercicios = new ArrayList<String>();
		exercicios = ExerciciosDAO.buscaExerciciosUnique(UserSession.getUser().getId());
		
		for (int i = 0; i < exercicios.size(); i++)
			ExerciciosPanel.getComboMeusExercicios().addItem(exercicios.get(i));
	}

	public static String verificaExercicioString(int idUsuario, String exercicio) {
		String exercicios = ExerciciosDAO.verificaExercicio(idUsuario, exercicio);
		return exercicios;
	}

	public static Object[][] carregaTable() {
		
		List<Exercicios> Exercicios = new ArrayList<Exercicios>();
		Exercicios = ExerciciosDAO.buscaExerciciosUser(UserSession.getUser().getId());
		Object[][] tabela = null;
		
		if (Exercicios.size() < 27) {
			tabela = new Object[27][6];
		} else {
			tabela = new Object[Exercicios.size()][6];
		}
		
		for (int i = 0; i < Exercicios.size(); i++) {
			tabela[i][0] = DatasUtil.convertAndFormatToString(((Exercicios) Exercicios.get(i)).getData(), "dd/MM/yyyy");
			tabela[i][1] = ((Exercicios) Exercicios.get(i)).getTempoGasto();
			tabela[i][2] = ((Exercicios) Exercicios.get(i)).getExercicio();
		}
		return tabela;
	}
}
