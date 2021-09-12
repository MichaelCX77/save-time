package save.time.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import save.time.dao.DietasDAO;
import save.time.entity.Dietas;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.DietasPanel;

public class DietasPanelController {
	public static void gravaDietas() {
		
		try {
			AuxiliarBd.persist(DietasPanel.getObjDietas());
			JOptionPane.showMessageDialog(DietasPanel.getPanelForm(), "Registro adicionado com sucesso!");
			DietasPanel.resetPanel();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(DietasPanel.getPanelForm(), "Problema: " + e2);
		}
	}

	public static Object[][] carregaTable() {
		
		List<Dietas> Dietas = new ArrayList<Dietas>();
		Dietas = DietasDAO.buscaDietasUser(UserSession.getUser().getId());
		Object[][] tabela = null;
		
		if (Dietas.size() < 27) {
			tabela = new Object[27][6];
		} else {
			tabela = new Object[Dietas.size()][6];
		}
		
		for (int i = 0; i < Dietas.size(); i++) {
			tabela[i][0] = DatasUtil.convertAndFormatToString(((Dietas) Dietas.get(i)).getData(), "dd/MM/yyyy");
			tabela[i][1] = ((Dietas) Dietas.get(i)).getHorario();
			tabela[i][2] = ((Dietas) Dietas.get(i)).getRefeicao();
			tabela[i][3] = ((Dietas) Dietas.get(i)).getAlimento();
			tabela[i][4] = ((Dietas) Dietas.get(i)).getQuantidade();
		}
		return tabela;
	}

	public static void recarregaComboHorario() {
		
		DietasPanel.getComboHorario().removeAllItems();
		String horario = null;
		
		for (int hora = 0; hora < 24; hora++) {
			if (hora < 10) {
				horario = "0" + hora + ":" + "00";
				DietasPanel.getComboHorario().addItem(horario);
				horario = "0" + hora + ":" + "30";
				DietasPanel.getComboHorario().addItem(horario);
			} else {
				horario = String.valueOf(hora) + ":" + "00";
				DietasPanel.getComboHorario().addItem(horario);
				horario = String.valueOf(hora) + ":" + "30";
				DietasPanel.getComboHorario().addItem(horario);
			}
		}
	}

	public static void recarregaComboRefeicao() {
		DietasPanel.getComboRefeicao().removeAllItems();
		DietasPanel.getComboRefeicao().addItem("Selecione...");
		DietasPanel.getComboRefeicao().addItem("Café da Manhã");
		DietasPanel.getComboRefeicao().addItem("Almoço");
		DietasPanel.getComboRefeicao().addItem("Café da Tarde");
		DietasPanel.getComboRefeicao().addItem("Janta");
		DietasPanel.getComboRefeicao().addItem("Lanche");
	}
}
