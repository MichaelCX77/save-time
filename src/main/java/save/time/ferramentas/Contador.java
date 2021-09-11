package save.time.ferramentas;

import save.time.test.TesteView;

public class Contador extends Thread {
  public static Thread thread = new Cronometro();
  
  public void run() {
    try {
      int segundo = 0;
      int hora = 0;
      int minuto = 0;
      while (true) {
        Thread.sleep(1000L);
        if (testeTimerdda.isContador()) {
          if (testeTimerdda.isZerado()) {
            segundo = 0;
            hora = 0;
            minuto = 0;
            testeTimerdda.setZerado(false);
          } 
          segundo++;
          if (segundo > 59) {
            segundo = 0;
            minuto++;
          } 
          if (minuto == 59) {
            minuto = 0;
            hora++;
          } 
          String timer = String.valueOf(completaComZero(Integer.valueOf(hora))) + ":" + 
            completaComZero(Integer.valueOf(minuto)) + ":" + 
            completaComZero(Integer.valueOf(segundo));
          TesteView.getLblTime().setText(timer);
          TesteView.getLblTime().revalidate();
        } 
      } 
    } catch (InterruptedException ex) {
      ex.printStackTrace();
      return;
    } 
  }
  
  private String completaComZero(Integer i) {
    String retorno = null;
    if (i.intValue() < 10) {
      retorno = "0" + i;
    } else {
      retorno = i.toString();
    } 
    return retorno;
  }
  
  public static Thread getThread() {
    return thread;
  }
}
