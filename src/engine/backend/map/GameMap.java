/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */

package engine.backend.map;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

	private String myMapImage;
	private double myMapWidth;
	private double myMapHeight;
	private List<Quadrant> quadrants;
	private Path[] paths;
	/**
	 * Map is defined by a image and list of paths, width and height
	 * @param image
	 * @param paths
	 * @param width
	 * @param height
	 */
	public GameMap(String image, Path[] paths, double width, double height) {
		this.myMapImage = image;
		this.myMapWidth = width;
		this.myMapHeight = height;
		this.quadrants = new ArrayList<Quadrant>();
		this.paths = paths;
	}
	
	public GameMap() {
		this.myMapImage = null;
		this.myMapWidth = 0;
		this.myMapHeight = 0;
		this.quadrants = new ArrayList<Quadrant>();
	}
	/**
	 * Returns the map image
	 * @return myMapImage
	 */
	public String getMapImage(){
		return myMapImage;
	}
	/**
	 * Sets the map image
	 * @param image
	 */
	public void setMapImage(String image) {
		this.myMapImage = image;
	}
	/**
	 * 
	 * @return Map Width
	 */
	public double getMapWidth(){
		return myMapWidth;
	}
	/**
	 * Sets the map width
	 * @param width
	 */
	public void setMapWidth(double width) {
		this.myMapWidth = width;
	}
	/**
	 * returns the map height
	 * @return map height
	 */
	public double getMapHeight(){
		return myMapHeight;
	}
	/**
	 * Sets the map height
	 * @param height
	 */
	public void setMapHeight(double height) {
		this.myMapHeight = height;
	}
	/**
	 * Returns the paths separated by an underscores
	 * @return
	 */
	public String getPathsInfo() {
		StringBuilder sb = new StringBuilder();
		for (Path path : paths) {
			sb.append(path.toString());
			sb.append("_");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	/**
	 * Returns the array of paths on the map
	 * @return paths
	 */
	public Path[] getPaths() {
		return paths;
	}
	/**
	 * Returns the specific path at the given index
	 * @param index
	 * @return path at index
	 */
	public Path getPath(int index){
		return paths[index];
	}
	

	public void setPaths(Path[] paths) {
		this.paths = paths;
	}
	/**
	 * Returns the list of Quadrants
	 * @return quadrants
	 */
	public List<Quadrant> getQuadrants() {
		return quadrants;
	}
	/**
	 * Sets the quadrants of the Game Map
	 * @param quadrants
	 */
	public void setQuandrants(List<Quadrant> quadrants) {
		this.quadrants = quadrants;
	}

}
