package backend.game_object.components;

public class DisplayComponent extends Component {
	
	private boolean canBeShown;
	private String imageToShow = "/DrumpfVader.png";
	
	public DisplayComponent(){
		this.canBeShown = true;
		tag = "Display";
	}
	
	public DisplayComponent(boolean shown) {
		this.canBeShown = shown;
		tag = "Display";
	}
	
	public boolean shouldBeShown(){
		return canBeShown;
	}
	
	public String getImage(){
		return imageToShow;
	}
	
	public static void main(String[] args){
		Object c = new DisplayComponent(true);
		System.out.println(c.getClass());
	}

}
