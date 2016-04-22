/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */


package engine.backend.components;

public class DisplayComponent extends Component {
	
	private boolean canBeShown;
	private String image;
	
	public DisplayComponent() {
		
	}
	
	public DisplayComponent(String image){
		this.image = image;
		canBeShown = true;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public DisplayComponent(boolean shown) {
		this.canBeShown = shown;
	}
	
	public boolean shouldBeShown(){
		return canBeShown;
	}
	
	public String getImage(){
		return image;
	}
	
	public void doNotShow(){
		canBeShown = false;
	}
	
	public void setCanBeShown(String bool){
		boolean value = Boolean.parseBoolean(bool);
		this.canBeShown = value;
	}
	
	@Override
	public String toString() {
		return this.getTag() + this.image;
	}

	@Override
	public String getComponentInfo() {
		return "CanBeShown:" + canBeShown + "," + "Image:" + image;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equals("Image")) {
			if (!data.equals("0")) {
				this.image = data;
				this.canBeShown = true;
			}
		}
	}

}
