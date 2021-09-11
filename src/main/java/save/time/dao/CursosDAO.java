package save.time.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.CursosDAOImpl;
import save.time.entity.Cursos;

public class CursosDAO {
  public static String verificaCurso(int idUsuario, String nomeCurso) {
    CursosDAOImpl cursosDAO = new CursosDAOImpl();
    Query query = cursosDAO.findByUserAndNomeCurso();
    query.setParameter(1, Integer.valueOf(idUsuario));
    query.setParameter(2, nomeCurso);
    String curso = new String();
    try {
      curso = (String)query.getSingleResult();
    } catch (NoResultException noResultException) {}
    return curso;
  }
  
  public static List<Cursos> buscaCursosUser(int idUsuario) {
    CursosDAOImpl cursosDAO = new CursosDAOImpl();
    Query query = cursosDAO.findByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<Cursos> cursos = new ArrayList<Cursos>();
    try {
      cursos = query.getResultList();
    } catch (NoResultException noResultException) {}
    return cursos;
  }
  
  public static List<String> buscaCursosUnique(int idUsuario) {
    CursosDAOImpl cursosDAO = new CursosDAOImpl();
    Query query = cursosDAO.findUniqueCursoByUser();
    query.setParameter(1, Integer.valueOf(idUsuario));
    List<String> cursos = new ArrayList<String>();
    try {
      cursos = query.getResultList();
    } catch (NoResultException noResultException) {}
    return cursos;
  }
  
  public static List<Cursos> verificaCursoRecente(int idUsuario, String curso) {
    CursosDAOImpl cursosDAO = new CursosDAOImpl();
    Query query = cursosDAO.findMAXByUserAndCurso();
    query.setParameter(1, Integer.valueOf(idUsuario));
    query.setParameter(2, curso);
    List<Cursos> cursos = new ArrayList<Cursos>();
    try {
      cursos = query.getResultList();
    } catch (NoResultException noResultException) {}
    return cursos;
  }
}
