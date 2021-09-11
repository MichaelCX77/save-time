package save.time.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.EspiritualDAOImpl;
import save.time.entity.Espiritual;

public class EspiritualDAO {
  public static String verificaAtividade(int idUsuario, String nomeAtividade) {
    EspiritualDAOImpl espiritualDAOImpl = new EspiritualDAOImpl();
    Query query = espiritualDAOImpl.findByUserAndNomeAtividade();
    query.setParameter(1, Integer.valueOf(idUsuario));
    query.setParameter(2, nomeAtividade);
    String atividade = new String();
    try {
      atividade = (String)query.getSingleResult();
    } catch (NoResultException noResultException) {}
    return atividade;
  }
  
  public static List<Espiritual> buscaEspiritualUser(int idUsuario) {
    EspiritualDAOImpl espiritualDAO = new EspiritualDAOImpl();
    Query query = espiritualDAO.findByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<Espiritual> atividades = new ArrayList<Espiritual>();
    try {
      atividades = query.getResultList();
    } catch (NoResultException noResultException) {}
    return atividades;
  }
  
  public static List<String> buscaAtividadesUnique(int idUsuario) {
    EspiritualDAOImpl atividadesDAO = new EspiritualDAOImpl();
    Query query = atividadesDAO.findUniqueAtividadeByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<String> atividades = new ArrayList<String>();
    try {
      atividades = query.getResultList();
    } catch (NoResultException noResultException) {}
    return atividades;
  }
}
