import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

public class BackendAuthoringUseCase {
	/*Use case: Set a map for a specific level
	The method below creates a new Level (with a Map object and a list of sprites) and initiates the Map with the provided background
	image. If wanted, the image can be changed later with the setBackgroundImage() method of the Level.
	 * 
	 */
	public Level createLevelWithMap(Image image){
		return new Level(image);
	}
	
	class Level{
		private Map map;
		private List<Sprite> sprites;
		public Level(Image map){
			this.map = new Map(map);
			this.sprites = new ArrayList<Sprite>();
		}
		
		public void setBackgroundImage(Image i){
			this.map.setImage(i);
		}
	}
	
	class Map{
		private Image image;
		private Path path;
		private List<Integer> defaultPath = Arrays.asList(1,2,3,4,5);
		public Map(Image image){
			this.image = image;
			this.path = new Path(defaultPath);
		}
		
		public void setPath(Path path){
			this.path = path;
		}
		
		public void setImage(Image i){
			this.image = i;
		}
		
	}
	
	class Path{
		private List<Integer> coordinates;
		public Path(List<Integer> input){
			this.coordinates = input;
		}
	}
	
	class Sprite{
		//temporary
	}
	
}