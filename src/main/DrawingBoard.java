package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter;
	private List<GObject> gObjects;
	private GObject target;

	private int gridSize = 10;

	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}

	public void addGObject(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
	}

	public void groupAll() {
		// TODO: Implement this method.
		CompositeGObject Compo_OBj = new CompositeGObject();
		for(GObject gObject : gObjects){
			Compo_OBj.add(gObject);
		}
		gObjects.clear();
		addGObject(Compo_OBj);
		Compo_OBj.recalculateRegion();
		repaint();
	}

	public void deleteSelected() {
		// TODO: Implement this method.
		gObjects.remove(target);
		repaint();
	}

	public void clear() {
		// TODO: Implement this method.
		gObjects.clear();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {
		// TODO: You need some variables here
		boolean drag;
		private int currentX;
		private int currentY;
		private void deselectAll() {
			// TODO: Implement this method.
			for(GObject gObject: gObjects){
				gObject.deselected();
			}
			drag = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.
			currentX = e.getX();
			currentY = e.getY();
			deselectAll();
			for(GObject gObject: gObjects){
				if(gObject.pointerHit(currentX, currentY)) {
					gObject.selected();
					target = gObject;
					drag = true;
				}
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.
			int desX = e.getX() - currentX;
			int desY = e.getY() - currentY;
			if (drag){
				target.move(desX,desY);
				currentX = e.getX();
				currentY = e.getY();
				repaint();
			}
		}
	}
}