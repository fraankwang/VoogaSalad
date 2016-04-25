package engine.backend.components;

import java.util.ArrayList;

/**
 * author Kushal Byatnal
 */

import java.util.List;

public class MultiDirectionalFiringComponent extends FiringComponent{
	
	private List<Vector> myDirectionsToFire;
	
	public MultiDirectionalFiringComponent(FiringComponent component){
		super(component);
		initializeDirectionVectors();
	}
	
	public MultiDirectionalFiringComponent(){
		initializeDirectionVectors();
	}
	
	@Override
	public int getNumDirections(){
		return myDirectionsToFire.size();
	}
	
	public Vector getDirectionAtIndex(int i){
		return myDirectionsToFire.get(i);
	}
	
	//create vectors corresponding to all 8 cardinal and ordinal directions
	private void initializeDirectionVectors(){
		myDirectionsToFire = new ArrayList<Vector>();
		for(int x = -1; x < 2; x++){
			for(int y = -1; y < 2; y++){
	
				if(x == 0 && y == 0){
					continue;
				}
				myDirectionsToFire.add(new Vector(x, y)); 
			}
		}
	}
	
	
	//everything else is the same here except for direction to fire, make calls to super
}
