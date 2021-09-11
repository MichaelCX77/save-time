package save.time.controller;

import save.time.ferramentas.Cronometro;
import save.time.view.CursosPanel;
import save.time.view.DietasPanel;
import save.time.view.EspiritualPanel;
import save.time.view.ExerciciosPanel;
import save.time.view.IdiomasPanel;
import save.time.view.LeiturasPanel;
import save.time.view.LoginView;

public class HomeController {
	public static void carregaMenus() {
		
		if (!Cronometro.getThread().isAlive())
		Cronometro.getThread().start();
		LoginView.getLoginFrame().setTitle("Home");
		LeiturasPanel.carregarLeituras();
		IdiomasPanel.carregarIdiomas();
		CursosPanel.carregarCursos();
		ExerciciosPanel.carregarExercicios();
		EspiritualPanel.carregarEspiritual();
		DietasPanel.carregarDietas();
	}
}
