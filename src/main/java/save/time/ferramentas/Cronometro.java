package save.time.ferramentas;

import save.time.view.CursosPanel;
import save.time.view.EspiritualPanel;
import save.time.view.ExerciciosPanel;
import save.time.view.IdiomasPanel;
import save.time.view.LeiturasPanel;

public class Cronometro extends Thread {
	
	public static Thread thread = new Cronometro();
	private static boolean contador = false;
	private static boolean zerado = false;

	public void run() {
		
		int contSegundos = 0;
		int contMinutos = 0;
		int contHoras = 0;
		
		String defaultSegundos = "0";
		String defaultMinutos = "0";
		String defaultHoras = "0";
		
		try {
			
			while (true) {
				
				Thread.sleep(1000L);
				
				if (isContador()) {
					
					if (isZerado()) {
						defaultSegundos = "0";
						defaultMinutos = "0";
						defaultHoras = "0";
						contSegundos = 0;
						contMinutos = 0;
						contHoras = 0;
						setZerado(false);
					}
					
					String time = String.valueOf(defaultHoras) + contHoras + ":" + defaultMinutos + contMinutos + ":"
							+ defaultSegundos + contSegundos++;
					atualizaCronometros(time);
					
					if (contSegundos == 59) {
						
						contSegundos = 0;
						contMinutos++;
						defaultSegundos = "0";
						
						if (contMinutos == 59) {
							
							contMinutos = 0;
							contHoras++;
							defaultMinutos = "0";
							
							if (contHoras < 10) {
								defaultHoras = "0";
								continue;
							}
							
							defaultHoras = "";
							continue;
						}
						if (contMinutos == 10)
							defaultMinutos = "";
						
						continue;
					}
					
					if (contSegundos == 10)
						defaultSegundos = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public static Thread getThread() {
		return thread;
	}

	public static void setThread(Thread thread) {
		Cronometro.thread = thread;
	}

	public static boolean isContador() {
		return contador;
	}

	public static boolean isZerado() {
		return zerado;
	}

	public static void setZerado(boolean zero) {
		zerado = zero;
	}

	public static void iniciar() {
		contador = true;
	}

	public static void pausar() {
		contador = false;
	}

	public static void continuar() {
		contador = true;
	}

	public static void finalizar() {
		contador = false;
		zerado = true;
	}

	public static void atualizaCronometros(String time) {
		LeiturasPanel.getLblTime().setText(time);
		ExerciciosPanel.getLblTime().setText(time);
		CursosPanel.getLblTime().setText(time);
		IdiomasPanel.getLblTime().setText(time);
		EspiritualPanel.getLblTime().setText(time);
	}
}
