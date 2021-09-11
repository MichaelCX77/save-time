package save.time.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import save.time.dao.impl.DietasDAOImpl;
import save.time.entity.Dietas;

public class DietasDAO {
	public static List<Dietas> buscaDietasUser(int idUsuario) {
		
		DietasDAOImpl dietasDAOImpl = new DietasDAOImpl();
		Query query = dietasDAOImpl.findByUser();
		query.setParameter(1, Integer.valueOf(idUsuario));
		List<Dietas> atividades = new ArrayList<Dietas>();
		
		try {
			atividades = query.getResultList();
		} catch (NoResultException noResultException) {
		}
		return atividades;
	}
}
