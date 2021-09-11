package save.time.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.IdiomasDAOImpl;
import save.time.entity.Idiomas;

public class IdiomasDAO {
	public static String verificaIdioma(int idUsuario, String Idioma) {
		
		IdiomasDAOImpl idiomasDAO = new IdiomasDAOImpl();
		Query query = idiomasDAO.findByUserAndNomeIdioma();
		query.setParameter(1, Integer.valueOf(idUsuario));
		query.setParameter(2, Idioma);
		String idioma = new String();
		
		try {
			idioma = (String) query.getSingleResult();
		} catch (NoResultException noResultException) {
		}
		return idioma;
	}

	public static List<Idiomas> buscaIdiomasUser(int idUsuario) {
		
		IdiomasDAOImpl idiomasDAOImpl = new IdiomasDAOImpl();
		Query query = idiomasDAOImpl.findByUser();
		query.setParameter(1, Integer.valueOf(idUsuario));
		List<Idiomas> Idiomas = new ArrayList<Idiomas>();
		
		try {
			Idiomas = query.getResultList();
		} catch (NoResultException noResultException) {
		}
		return Idiomas;
	}
}
