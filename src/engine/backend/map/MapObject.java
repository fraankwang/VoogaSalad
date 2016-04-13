/**
 * 
 * @author mario_oliver93
 * 
 */

package engine.backend.map;

import java.util.ArrayList;
import java.util.List;

import engine.backend.components.DisplayComponent;
import engine.backend.components.SizeComponent;

public class MapObject {

	private DisplayComponent image;
	private SizeComponent size;
	private List<Quadrant> quadrants;
	private Path path;
	
	public MapObject(String image, Path path) {
		this.image = new DisplayComponent(image);
		this.size = new SizeComponent(300, 300);
		this.quadrants = new ArrayList<Quadrant>();
		this.path = path;
	}
	
	public MapObject() {
		
	}
	
	public String getImage(){
		return image.getImage();
	}
	
	public double getXSize(){
		return size.getWidth();
	}
	
	public double getYSize(){
		return size.getHeight();
	}
	
	public Path getPath() {
		return path;
	}
	
	public List<Quadrant> getQuadrants() {
		return quadrants;
	}

}
