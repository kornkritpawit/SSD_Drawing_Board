package objects;

import java.awt.*;

public class Oval extends GObject {

	private Color color;
	
	public Oval(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}



	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		g.setColor(color);
		g.drawOval(x,y,width,height);
		g.fillOval(x,y,width,height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Oval",x,y+height+12);
	}
	
}
