package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class IdiomasDAOImpl {
	public Query findUniqueIdiomaByUser() {
		Query query = null;
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.idioma "
					+ "FROM Idiomas TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "GROUP BY TB01.idioma");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findByUserAndNomeIdioma() {
		Query query = null;
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.idioma "
					+ "FROM Idiomas TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.idioma = ?2 "
					+ "GROUP BY TB01.idioma");
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
					+ "FROM Idiomas TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
