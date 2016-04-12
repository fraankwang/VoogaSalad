package backend.game_object.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import backend.game_object.components.IComponent;

/**
 * 
 * @author raghavkedia
 *
 */

//this will be used as an interface for any game object. 
public interface IEntity {

	public void addComponent(IComponent component);
	
	public IComponent getComponent(String tag);
	
	public String toString();
	
	public Set<String> getComponentTags();
	
	public Collection<IComponent> getComponents();
	
	public boolean hasComponent(String tag);
	
	public String getName();
	
	public boolean hasBeenModified();
	
	public void setHasBeenModified(boolean bool);
}
