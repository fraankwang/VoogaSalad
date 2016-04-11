/**
 * 
 * @author mario_oliver93
 * 
 */


package backend.game_object.components;

import java.util.ResourceBundle;

public abstract class Component implements IComponent{
	
	public String getTag(){
		return this.getClass().getSimpleName();
	}
	
}
