package save.time.dao.impl;

import javax.persistence.Query;

import save.time.connection.Connection;

public class UserDAOImpl {

	public Query findByUser() {
		
		Query query = null;
		try {
			query = Connection.getConnection().createQuery("SELECT TB01 "
					+ "FROM "
					+ "User TB01 "
					+ "WHERE "
					+ "TB01.login = ?1");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		
		return query;
	}
}
