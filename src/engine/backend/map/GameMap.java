/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */

package engine.backend.map;

import java.util.ArrayList;
import java.util.List;

import engine.backend.components.DisplayComponent;
import engine.backend.components.SizeComponent;

public class GameMap {

	private String myMapImage;
	private double myMapWidth;
	private double myMapHeight;
	private List<Quadrant> quadrants = new ArrayList<Quadrant>();
	private Path path;
	
	public GameMap(String image, double width, double height) {
		myMapImage = image;
		myMapWidth = width;
		myMapHeight = height;
	}
	
	public String getMapImage(){
		return myMapImage;
	}
	
	public double getMapWidth(){
		return myMapWidth;
	}
	
	public double getMapHeight(){
		return myMapHeight;
	}

}
