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
		for(GObject gObject: gObjects){
			gObject.move(dX,dY);
		}
	}

	public void recalculateRegion() {
		// TODO: Implement this method.
		GObject object = gObjects.get(0);
		int min_x = object.x;
		int min_y = object.y;
		int max_x = min_x+ object.width;
		int max_y = min_y+ object.height;

		for( GObject gObject: gObjects){
			if(min_x> gObject.x){
				min_x = gObject.x;
			}
			if(max_x < gObject.x + gObject.width){
				max_x = gObject.x + gObject.width;
			}
			if(min_y>gObject.y){
				min_y = gObject.y;
			}
			if(max_y < gObject.y+ gObject.height){
				max_y = gObject.y + gObject.height;
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
		g.drawString("Group",x, y+height+12);
	}
}