package save.time.connection;

import javax.persistence.EntityManager;

public class Connection {

	private static EntityManager em;

	public static void setConnection(EntityManager em) {
		Connection.em = em;
	}

	public static EntityManager getConnection() {
		return em;
	}
	
}
