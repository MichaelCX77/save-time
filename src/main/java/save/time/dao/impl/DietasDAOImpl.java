package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class DietasDAOImpl {
	
	public Query findByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01 "
					+ "FROM Dietas TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
