/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.game_object.components;

public class SizeComponent extends Component implements IComponent{

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
	
	@Override
	public String toString() {
		return this.getTag() + " with width: " + this.width + " with height: " + this.height;
	}

	public double getHeight(){
		return height;
	}
	
	public void increaseSize(int delta){
		this.width += delta;
		this.height += delta;
	}
	
	public double getWidth(){
		return width;
	}

}
