package save.time.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import save.time.connection.Connection;
import save.time.view.CarregamentoView;
import save.time.view.LoginView;

public class MainInit {
  EntityManager em = null;
  
  public MainInit(EntityManager em) {
    this.em = em;
  }
  
  public static void main(String[] args) {
	new CarregamentoView();

    EntityManagerFactory entityManegerfactory = null;
	
	try {
		entityManegerfactory = Persistence.createEntityManagerFactory("SAVE_TIME");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(CarregamentoView.getCarregamentoFrame(), "Problema na configuração do banco de dados", "Erro", 0);
		System.exit(0);
	}

    EntityManager em = null;

    try {
      em = entityManegerfactory.createEntityManager();
      Connection.setConnection(em);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(CarregamentoView.getCarregamentoFrame(), "Falha de conex\u00E3o!", "Erro", 0);
      System.exit(0);
    } 
    
	CarregamentoView.getCarregamentoFrame().setVisible(false);
	new LoginView();
	System.out.println("Sucesso");
	
  }
  
  public void setEntity(EntityManager em) {
    this.em = em;
  }
}
