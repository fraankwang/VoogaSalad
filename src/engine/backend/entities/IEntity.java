package engine.backend.entities;

import java.util.Collection;
import java.util.Set;

import engine.backend.components.IComponent;
import engine.backend.rules.IAction;

/**
 * 
 * @author raghavkedia
 *
 */

// this will be used as an interface for any game object.
public interface IEntity {

	public IComponent getComponent(String tag);

	public String toString();

	public Set<String> getComponentTags();

	public Collection<IComponent> getComponents();

	public void addComponent(IComponent component);

	public boolean hasComponent(String tag);

	public String getName();

	public boolean hasBeenModified();

	public void setHasBeenModified(boolean bool);

	public String getGenre();

	public void setID(int id);

	public int getID();

	public EntityStatistics getStats();

	public void broadcastEntity();

	public void applyAction(IAction action);
}
