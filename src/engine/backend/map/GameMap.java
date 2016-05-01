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

	private static final int NUMOFTESTPATHS = 1;

	public GameMap(String image, Path[] paths, double width, double height) {
		this.myMapImage = image;
		this.myMapWidth = width;
		this.myMapHeight = height;
		this.quadrants = new ArrayList<Quadrant>();
		this.paths = paths;
	}

	/* test code */
	public GameMap(String image, Path paths, double width, double height) {
		this.myMapImage = image;
		this.myMapWidth = width;
		this.myMapHeight = height;
		this.quadrants = new ArrayList<Quadrant>();
		Path[] testPaths = { paths };
		this.paths = testPaths;
	}

	public GameMap() {
		this.myMapImage = null;
		this.myMapWidth = 0;
		this.myMapHeight = 0;
		this.quadrants = new ArrayList<Quadrant>();
	}

	public String getMapImage() {
		return myMapImage;
	}

	public void setMapImage(String image) {
		this.myMapImage = image;
	}

	public double getMapWidth() {
		return myMapWidth;
	}

	public void setMapWidth(double width) {
		this.myMapWidth = width;
	}

	public double getMapHeight() {
		return myMapHeight;
	}

	public void setMapHeight(double height) {
		this.myMapHeight = height;
	}

	public String getPathsInfo() {
		StringBuilder sb = new StringBuilder();
		for (Path path : paths) {
			sb.append(path.toString());
			sb.append("_");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public Path[] getPaths() {
		return paths;
	}

	public Path getPath(int index) {
		return paths[index];
	}

	public void setPaths(Path[] paths) {
		this.paths = paths;
	}

	public List<Quadrant> getQuadrants() {
		return quadrants;
	}

	public void setQuandrants(List<Quadrant> quadrants) {
		this.quadrants = quadrants;
	}

}
