package engine.backend.game_object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import engine.backend.components.IComponent;
import engine.backend.rules.EntityAction;
import engine.backend.utilities.ComponentTagResources;

/**
 * 
 * @author 
 *
 */
public class ModeStatistics {

	private int initialNumLives;
	private int currentNumLives;
	private double initialResources;
	private double currentResources;
	private List<Integer> endOfLevelLives;
	private List<Double> endOfLevelResources;

	public ModeStatistics(int numLives, double resources) {
		setInitialNumLives(numLives);
		setCurrentNumLives(numLives);
		setInitialResources(resources);
		setCurrentResources(resources);
		endOfLevelLives = new ArrayList<Integer>();
		endOfLevelResources = new ArrayList<Double>();
	}

	public void addEndOfLevelLives(int numLives) {
		endOfLevelLives.add(numLives);
	}

	public void addEndOfLevelResources(double resources) {
		endOfLevelResources.add(resources);
	}

	public int getInitialNumLives() {
		return initialNumLives;
	}

	public void setInitialNumLives(int initialNumLives) {
		this.initialNumLives = initialNumLives;
	}

	public int getCurrentNumLives() {
		return currentNumLives;
	}

	public void setCurrentNumLives(int currentNumLives) {
		this.currentNumLives = currentNumLives;
	}

	public double getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(double initialResources) {
		this.initialResources = initialResources;
	}

	public double getCurrentResources() {
		return currentResources;
	}

	public void setCurrentResources(double currentResources) {
		this.currentResources = currentResources;
	}
	
	@Override
	public void applyAction(LevelAction action) {
		String resourceToModify = action.getResourceToModifiy();
		String instanceVar = action.getValueInComponent();
		String newVal = action.getNewValue();
		Method setMethod;

		String fullName = ComponentTagResources.getComponentTag(component);
		//System.out.println(getName() + "   " + fullName);
		Class<? extends IComponent> componentClass = myComponents.get(fullName).getClass();
		//System.out.println(componentClass.getName());
		try {
			Object componentClassInstance = componentClass.newInstance();
			
			componentClassInstance = componentClass.cast(myComponents.get(fullName));
			// put in resource file!!!
			String methodName = "set" + instanceVar;

			setMethod = componentClassInstance.getClass().getMethod(methodName, String.class);

			setMethod.invoke(componentClassInstance, newVal);

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
