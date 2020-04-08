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
		for (GObject gObject : gObjects){
			gObject.x += dX;
			gObject.y += dY;
			gObject.move(dX,dY);
		}

	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		GObject gObject = gObjects.get(0);
		int min_x = gObject.x;
		int min_y = gObject.y;
		int max_x = min_x+ gObject.width;
		int max_y = min_y+ gObject.height;
		for( GObject gObject1: gObjects){
			if(min_x> gObject1.x){
				min_x = gObject1.x;
			}
			if(max_x < gObject1.x + gObject1.width){
				max_x = gObject1.x + gObject1.width;
			}
			if(min_y>gObject1.y){
				min_y = gObject1.y;
			}
			if(max_y < gObject1.y+ gObject1.height){
				max_y = gObject1.y + gObject1.height;
			}
			this.x = min_x;
			this.y = min_y;
			this.width = max_x - min_x;
			this.height = max_y - min_y;
		}
	}
	
	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for(GObject gObject : gObjects){
			gObject.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Group",x,y+height+12);
	}
	
}
