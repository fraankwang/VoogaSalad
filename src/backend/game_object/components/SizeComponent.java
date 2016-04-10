/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.game_object.components;

public class SizeComponent {

	private double width;
	private double height;
	
	//default component
	public SizeComponent() {
		this.width = 80;
		this.height = 100;
	}
	
	public SizeComponent(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWidth(){
		return width;
	}

}
