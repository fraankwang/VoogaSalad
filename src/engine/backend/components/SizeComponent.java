/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.components;

import java.util.List;

public class SizeComponent extends Component implements IComponent{

	private double width;
	private double height;
	
	private static final int DEFAULT_HEIGHT = 200;
	private static final int DEFAULT_WIDTH = 200;
	
	//default component
	public SizeComponent() {
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
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

	@Override
	public void initWithParams(List params) {
		this.width = (double) params.get(0);
		this.height = (double) params.get(1);
	}

}
