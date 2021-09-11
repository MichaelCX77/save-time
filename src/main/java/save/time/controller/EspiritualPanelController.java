package save.time.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import save.time.dao.EspiritualDAO;
import save.time.entity.Espiritual;
import save.time.ferramentas.AuxiliarBd;
import save.time.ferramentas.DatasUtil;
import save.time.session.UserSession;
import save.time.view.EspiritualPanel;

public class EspiritualPanelController {
  public static void gravaAtividade() {
    try {
      AuxiliarBd.persist(EspiritualPanel.getObjEspiritual());
      JOptionPane.showMessageDialog(EspiritualPanel.getPanelFinalizar(), "Registro adicionado com sucesso!");
      EspiritualPanel.resetPanel();
    } catch (Exception e2) {
      JOptionPane.showMessageDialog(EspiritualPanel.getPanelFinalizar(), "Problema: " + e2);
    } 
  }
  
  public static String verificaAtividadeString(int idUsuario, String nomeAtividade) {
    String atividade = EspiritualDAO.verificaAtividade(idUsuario, nomeAtividade);
    return atividade;
  }
  
  public static Object[][] carregaTable() {
    List<Espiritual> espiritual = new ArrayList<Espiritual>();
    espiritual = EspiritualDAO.buscaEspiritualUser(UserSession.getUser().getId());
    Object[][] tabela = null;
    if (espiritual.size() < 27) {
      tabela = new Object[27][6];
    } else {
      tabela = new Object[espiritual.size()][6];
    } 
    for (int i = 0; i < espiritual.size(); i++) {
      tabela[i][0] = DatasUtil.convertAndFormatToString(((Espiritual)espiritual.get(i)).getData(), "dd/MM/yyyy");
      tabela[i][1] = ((Espiritual)espiritual.get(i)).getTempoGasto();
      tabela[i][2] = ((Espiritual)espiritual.get(i)).getAtividade();
    } 
    return tabela;
  }
  
  public static void buscaAtividades() {
    List<String> atividades = new ArrayList<String>();
    atividades = EspiritualDAO.buscaAtividadesUnique(UserSession.getUser().getId());
    for (int i = 0; i < atividades.size(); i++)
      EspiritualPanel.getComboMinhasAtividades().addItem(atividades.get(i)); 
  }
}
