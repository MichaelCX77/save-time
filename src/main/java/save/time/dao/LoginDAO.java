package save.time.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import save.time.dao.impl.UserDAOImpl;
import save.time.entity.User;

public class LoginDAO {
	public static User verificaUsuario(String usuario) {
		
		UserDAOImpl userDAO = new UserDAOImpl();
		Query query = userDAO.findByUser();
		query.setParameter(1, usuario);
		User user = new User();
		
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException noResultException) {
		}
		return user;
	}
}
