package engine.backend.entities;

import engine.backend.components.IComponent;
import engine.backend.rules.EntityAction;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;

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

	public String getType();
	
	public int getID();

	public void setLevelID(int levelID);
	
	public void applyAction(EntityAction action, ResourceBundle myComponentTagResources);
}
