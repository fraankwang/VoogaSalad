package backend.game_object.components;

public class DisplayComponent implements Component {
	
	private String tag = "Display";
	private boolean canBeShown;
	
	public DisplayComponent() {
		this.canBeShown = true;
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return tag;
	}
	
	public boolean shouldBeShown(){
		return canBeShown;
	}

}
