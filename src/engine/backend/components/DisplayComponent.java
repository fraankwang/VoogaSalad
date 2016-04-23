/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */


package engine.backend.components;

public class DisplayComponent extends Component implements IComponent{
	
	private boolean canBeShown;
	private String image;
	
	public DisplayComponent(String image){
		this.image = image;
		canBeShown = true;
	}
	
	public DisplayComponent(DisplayComponent component) {
		this.canBeShown = component.shouldBeShown();
		this.image = component.getImage();
	}
	
	/**
	 * Sets the image for the entity that has this component.
	 * @param image
	 */
	public void setImage(String image){
		this.image = image;
	}
	
	public DisplayComponent(boolean shown) {
		this.canBeShown = shown;
	}
	
	/**
	 * 
	 * @return True if the entity with this component can be shown. False if the entity of the component can not be shown.
	 */
	public boolean shouldBeShown(){
		return canBeShown;
	}
	
	/**
	 * 
	 * @return The image of the entity with this component.
	 */
	public String getImage(){
		return image;
	}
	
	/**
	 * Sets the value of whether or not the image can be shown to false.
	 */
	public void doNotShow(){
		canBeShown = false;
	}
	
	/**
	 * Takes a string, turns it into a boolean and sets whether the image can be shown based on the boolean.
	 * @param bool
	 */
	public void setCanBeShown(String bool){
		boolean value = Boolean.parseBoolean(bool);
		this.canBeShown = value;
	}
	
	@Override
	public String toString() {
		return this.getTag() + this.image;
	}

	@Override
	public void initWithParams(String[] params) {
		this.canBeShown = true; //default
	}

}
