package backend.game_object.components;

public abstract class Component implements IComponent{
	
	protected String tag = "Blank";
	
	public String getTag(){
		return tag;
	}
	
	public void update(){
		
	}
	
}
