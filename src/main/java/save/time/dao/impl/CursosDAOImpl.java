package save.time.dao.impl;

import javax.persistence.Query;
import save.time.connection.Connection;

public class CursosDAOImpl {
	public Query findByUserAndNomeCurso() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.curso "
					+ "FROM Cursos TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.curso = ?2 "
					+ "GROUP BY "
					+ "TB01.curso");
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
					+ "FROM Cursos TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "ORDER BY TB01.data DESC");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findUniqueCursoByUser() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT TB01.curso "
					+ "FROM Cursos TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 "
					+ "GROUP BY TB01.curso");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}

	public Query findMAXByUserAndCurso() {
		
		Query query = null;
		
		try {
			query = Connection.getConnection().createQuery(
					"SELECT MAX(TB01.auAtual), TB01.auTotal "
					+ "FROM Cursos TB01 "
					+ "WHERE "
					+ "TB01.usuario.id = ?1 AND "
					+ "TB01.curso = ?2 "
					+ "GROUP BY TB01.auTotal");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return query;
	}
}
