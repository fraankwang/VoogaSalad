/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.components;

import java.util.List;

public class DisplayComponent extends Component implements IComponent{
	
	private boolean canBeShown;
	private String imageToShow = "/DrumpfVader.png";
	
	public DisplayComponent(){

	}
	
	public void setImage(String image){
		imageToShow = image;
	}
	
	public DisplayComponent(boolean shown) {
		this.canBeShown = shown;
	}
	
	public boolean shouldBeShown(){
		return canBeShown;
	}
	
	public String getImage(){
		return imageToShow;
	}
	
	public void doNotShow(){
		canBeShown = false;
	}
	
	@Override
	public String toString() {
		return this.getTag() + this.imageToShow;
	}

	@Override
	public void initWithParams(String[] params) {
		this.canBeShown = true; //default
	}

}
