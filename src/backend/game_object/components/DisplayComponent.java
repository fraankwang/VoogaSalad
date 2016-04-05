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
	
	public static void main(String[] args){
		Object c = new DisplayComponent(true);
		System.out.println(c.getClass());
	}

}
