package save.time.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.ExerciciosDAOImpl;
import save.time.entity.Exercicios;

public class ExerciciosDAO {
  public static List<String> buscaExerciciosUnique(int idUsuario) {
    ExerciciosDAOImpl exerciciosDAO = new ExerciciosDAOImpl();
    Query query = exerciciosDAO.findUniqueExercicioByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<String> exercicios = new ArrayList<String>();
    try {
      exercicios = query.getResultList();
    } catch (NoResultException noResultException) {}
    return exercicios;
  }
  
  public static String verificaExercicio(int idUsuario, String nomeExercicio) {
    ExerciciosDAOImpl exerciciosDAO = new ExerciciosDAOImpl();
    Query query = exerciciosDAO.findByUserAndNomeLivro();
    query.setParameter(1, Integer.valueOf(idUsuario));
    query.setParameter(2, nomeExercicio);
    String exercicio = new String();
    try {
      exercicio = (String)query.getSingleResult();
    } catch (NoResultException noResultException) {}
    return exercicio;
  }
  
  public static List<Exercicios> buscaExerciciosUser(int idUsuario) {
    ExerciciosDAOImpl exerciciosDAO = new ExerciciosDAOImpl();
    Query query = exerciciosDAO.findByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<Exercicios> exercicios = new ArrayList<Exercicios>();
    try {
      exercicios = query.getResultList();
    } catch (NoResultException noResultException) {}
    return exercicios;
  }
}
