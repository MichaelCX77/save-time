package save.time.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import save.time.dao.IdiomasDAO;
import save.time.entity.Idiomas;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.IdiomasPanel;

public class IdiomasPanelController {
  public static void gravaIdioma() {
    try {
      AuxiliarBd.persist(IdiomasPanel.getObjIdiomas());
      JOptionPane.showMessageDialog(IdiomasPanel.getPanelFinalizar(), "Registro adicionado com sucesso!");
      IdiomasPanel.resetPanel();
    } catch (Exception e2) {
      JOptionPane.showMessageDialog(IdiomasPanel.getPanelFinalizar(), "Problema: " + e2);
    } 
  }
  
  public static Object[][] carregaTable() {
    List<Idiomas> Idiomas = new ArrayList<Idiomas>();
    Idiomas = IdiomasDAO.buscaIdiomasUser(UserSession.getUser().getId());
    Object[][] tabela = null;
    if (Idiomas.size() < 27) {
      tabela = new Object[27][6];
    } else {
      tabela = new Object[Idiomas.size()][6];
    } 
    for (int i = 0; i < Idiomas.size(); i++) {
      tabela[i][0] = DatasUtil.convertAndFormatToString(((Idiomas)Idiomas.get(i)).getData(), "dd/MM/yyyy");
      tabela[i][1] = ((Idiomas)Idiomas.get(i)).getTempoGasto();
      tabela[i][2] = ((Idiomas)Idiomas.get(i)).getIdioma();
    } 
    return tabela;
  }
}
