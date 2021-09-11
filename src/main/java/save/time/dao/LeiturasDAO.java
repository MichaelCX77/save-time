package save.time.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.LeiturasDAOImpl;
import save.time.entity.Leituras;

public class LeiturasDAO {
	public static String verificaLivro(int idUsuario, String nomeLivro) {
		
		LeiturasDAOImpl leiturasDAO = new LeiturasDAOImpl();
		Query query = leiturasDAO.findByUserAndNomeLivro();
		query.setParameter(1, Integer.valueOf(idUsuario));
		query.setParameter(2, nomeLivro);
		String livro = new String();
		
		try {
			livro = (String) query.getSingleResult();
		} catch (NoResultException noResultException) {
		}
		return livro;
	}

	public static List<Leituras> verificaLivroRecente(int idUsuario, String nomeLivro) {
		
		LeiturasDAOImpl leiturasDAO = new LeiturasDAOImpl();
		Query query = leiturasDAO.findMAXByUserAndNomeLivro();
		query.setParameter(1, Integer.valueOf(idUsuario));
		query.setParameter(2, nomeLivro);
		List<Leituras> livros = new ArrayList<Leituras>();
		
		try {
			livros = query.getResultList();
		} catch (NoResultException noResultException) {
		}
		return livros;
	}

	public static List<String> buscaLivrosUnique(int idUsuario) {
		
		LeiturasDAOImpl leiturasDAO = new LeiturasDAOImpl();
		Query query = leiturasDAO.findUniqueLivroByUser();
		query.setParameter(1, Integer.valueOf(idUsuario));
		List<String> livros = new ArrayList<String>();
		
		try {
			livros = query.getResultList();
		} catch (NoResultException noResultException) {
		}
		return livros;
	}

	public static List<Leituras> buscaLeiturasUser(int idUsuario) {
		
		LeiturasDAOImpl leiturasDAO = new LeiturasDAOImpl();
		Query query = leiturasDAO.findByUser();
		query.setParameter(1, Integer.valueOf(idUsuario));
		List<Leituras> livros = new ArrayList<Leituras>();
		
		try {
			livros = query.getResultList();
		} catch (NoResultException noResultException) {
		}
		return livros;
	}
}
