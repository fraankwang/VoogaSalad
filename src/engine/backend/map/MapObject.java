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

	private DisplayComponent image = new DisplayComponent();
	private SizeComponent size;
	private List<Quadrant> quadrants = new ArrayList<Quadrant>();
	private Path path;
	
	public MapObject() {
//		this.image.setImage("Crampton_stage.png");
		this.size = new SizeComponent(300, 300);
	}
	
	public String getImage(){
		System.out.println(image.getImage());
		return image.getImage();
	}
	
	public double getXSize(){
		return size.getWidth();
	}
	
	public double getYSize(){
		return size.getHeight();
	}

}
