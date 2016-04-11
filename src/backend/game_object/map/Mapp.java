/**
 * 
 * @author mario_oliver93
 * 
 */

package backend.game_object.map;

import java.util.ArrayList;
import java.util.List;

import backend.FrontEndAccessController;
import backend.game_object.components.DisplayComponent;
import backend.game_object.components.SizeComponent;

public class Mapp {

	private DisplayComponent image = new DisplayComponent();
	private SizeComponent size;
	private List<Quadrant> quadrants = new ArrayList<Quadrant>();
	private Path path;
	
	public Mapp() {
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
