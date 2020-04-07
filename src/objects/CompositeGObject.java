package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		this.x += dX;
		this.y += dY;
		for(GObject go: gObjects){
//			go.x +=dX;
//			go.y +=dY;
			go.move(dX,dY);
		}
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		GObject obj = gObjects.get(0);
		int min_x = obj.x;
		int min_y = obj.y;
		int max_x = min_x+ obj.width;
		int max_y = min_y+ obj.height;

		for( GObject go: gObjects){
			if(min_x> go.x){
				min_x = go.x;
			}
			if(min_y>go.y){
				min_y = go.y;
			}
			if(max_x < go.x + go.width){
				max_x = go.x + go.width;
			}
			if(max_y < go.y+ go.height){
				max_y = go.y + go.height;
			}
			this.x = min_x;
			this.y = min_y;
			this.width = max_x -min_x;
			this.height = max_y - min_y;
		}
		System.out.println("x: "+min_x+" y: "+min_y);
		System.out.println("width: "+width+" height: "+height);
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for(GObject go : gObjects){
			go.paintObject(g);
		}

	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Group",x+40, y+height+20);

	}
	
}
