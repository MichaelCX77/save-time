package save.time.ferramentas;

import javax.swing.ImageIcon;

public class ApoioRedimensionarImagem {
	
	public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
		
		int nw = icon.getIconWidth();
		int nh = icon.getIconHeight();
		
		if (icon.getIconWidth() > w) {
			nw = w;
			nh = nw * icon.getIconHeight() / icon.getIconWidth();
		}
		if (nh > h) {
			nh = h;
			nw = icon.getIconWidth() * nh / icon.getIconHeight();
		}
		return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, 1));
	}
}
