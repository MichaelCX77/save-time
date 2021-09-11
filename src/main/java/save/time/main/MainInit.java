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
    EntityManagerFactory entityManegerfactory = Persistence.createEntityManagerFactory("SAVE_TIME");
    EntityManager em = null;
    boolean falha = false;
    try {
      em = entityManegerfactory.createEntityManager();
      Connection.setConnection(em);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(CarregamentoView.getCarregamentoFrame(), "Falha de conex\u00E3o!", "Erro", 0);
      falha = true;
    } 
    
    if (falha) {
    	System.out.println("Falha");
      System.exit(0);
    } else {
    	CarregamentoView.getCarregamentoFrame().setVisible(false);
    	new LoginView();
    	System.out.println("Sucesso");
    } 
  }
  
  public void setEntity(EntityManager em) {
    this.em = em;
  }
}
