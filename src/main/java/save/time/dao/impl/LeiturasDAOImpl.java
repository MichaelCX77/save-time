package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class LeiturasDAOImpl {
	public Query findByUserAndNomeLivro() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.nomeLivro "
					+ "FROM Leituras TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.nomeLivro = ?2 "
					+ "GROUP BY TB01.nomeLivro");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findMAXByUserAndNomeLivro() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT MAX(TB01.pgAtual), TB01.pgTotal "
					+ "FROM Leituras TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.nomeLivro = ?2 "
					+ "GROUP BY TB01.pgTotal");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findUniqueLivroByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.nomeLivro "
					+ "FROM Leituras TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "GROUP BY TB01.nomeLivro");
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
					+ "FROM Leituras TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
