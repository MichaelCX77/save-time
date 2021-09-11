package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class ExerciciosDAOImpl {
	
	public Query findUniqueExercicioByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.exercicio "
					+ "FROM Exercicios TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "GROUP BY TB01.exercicio");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findByUserAndNomeLivro() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.exercicio "
					+ "FROM Exercicios TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.exercicio = ?2 "
					+ "GROUP BY TB01.exercicio");
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
					+ "FROM Exercicios TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
