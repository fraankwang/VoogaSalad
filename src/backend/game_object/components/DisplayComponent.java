package backend.game_object.components;

public class DisplayComponent extends Component {
	
	private boolean canBeShown;
	
	public DisplayComponent(boolean shown) {
		this.canBeShown = shown;
		tag = "Display";
	}
	
	public boolean shouldBeShown(){
		return canBeShown;
	}

}
