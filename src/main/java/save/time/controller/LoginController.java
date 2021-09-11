package save.time.controller;

import save.time.dao.LoginDAO;
import save.time.entity.User;
import save.time.session.UserSession;
import save.time.view.LoginView;

public class LoginController {
	public static boolean Login(User user) {
		
		User usuario = LoginDAO.verificaUsuario(user.getLogin().toUpperCase());
		
		if (usuario.getId() == 0) {
			LoginView.getLblMensagem().setText("*Usu\uFFFDrio n\uFFFDo existe");
			LoginView.getLblMensagem().setVisible(true);
			return false;
		}
		
		if (!usuario.getSenha().equals(user.getSenha())) {
			LoginView.getLblMensagem().setText("*Senha incorreta");
			LoginView.getLblMensagem().setVisible(true);
			return false;
		}
		
		LoginView.getLblMensagem().setText("*Autentica\uFFFD\uFFFDo Conclu\uFFFDda");
		LoginView.getLblMensagem().setVisible(true);
		UserSession.setUser(usuario);
		return true;
	}
}
