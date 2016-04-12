/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.game_object.components;

import java.util.List;

public class SizeComponent extends Component{

	private double width;
	private double height;
	
	//default component
	public SizeComponent() {

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

	@Override
	public void initWithParams(List params) {
		this.width = (double) params.get(0);
		this.height = (double) params.get(1);
	}

}
