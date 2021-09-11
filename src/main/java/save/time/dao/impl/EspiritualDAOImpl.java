package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class EspiritualDAOImpl {
	
	public Query findUniqueAtividadeByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.atividade "
					+ "FROM Espiritual TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "GROUP BY TB01.atividade");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findByUserAndNomeAtividade() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.atividade "
					+ "FROM Espiritual TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.atividade = ?2 "
					+ "GROUP BY TB01.atividade");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01 "
					+ "FROM Espiritual TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
