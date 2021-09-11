package save.time.ferramentas;

import javax.persistence.Query;
import save.time.connection.Connection;

public abstract class AuxiliarBd {
  public static void execute(Query query) {
    Connection.getConnection().getTransaction().begin();
    query.executeUpdate();
    Connection.getConnection().getTransaction().commit();
  }
  
  public static void persist(Object objeto) {
    Connection.getConnection().getTransaction().begin();
    Connection.getConnection().persist(objeto);
    Connection.getConnection().getTransaction().commit();
  }
}
