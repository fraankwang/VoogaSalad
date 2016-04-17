package engine.backend.components;

import java.util.List;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringComponent extends Component implements IComponent{
	
	//private IEntity myAmmunition;
	private String myAmmunition;
	private int myAmmunitionAmount;
	private double myAmmunitionSpeed;
	private double myEnemyInSightRange;
	private Vector myDirectionToFire;
	private List<String> myTargets;
	private double myFiringRate;
	private double currentTimeStep;
	
	public FiringComponent(){
	
	}
	
	public String getAmmunition() {
		return myAmmunition;
	}

	public void setAmmunition(String myAmmunition) {
		this.myAmmunition = myAmmunition;
	}

	public int getAmmunitionAmount() {
		return myAmmunitionAmount;
	}

	public void setAmmunitionAmount(int myAmmunitionAmount) {
		this.myAmmunitionAmount = myAmmunitionAmount;
	}

	public double getEnemyInSightRange() {
		return myEnemyInSightRange;
	}

	public void setEnemyInSightRange(double myEnemyInSightRange) {
		this.myEnemyInSightRange = myEnemyInSightRange;
	}
	
	public Vector getDirectionToFire() {
		return myDirectionToFire;
	}

	public void setDirectionToFire(Vector myDirectionToFire) {
		this.myDirectionToFire = myDirectionToFire;
	}

	public double getAmmunitionSpeed() {
		return myAmmunitionSpeed;
	}

	public void setAmmunitionSpeed(double myAmmunitionSpeed) {
		this.myAmmunitionSpeed = myAmmunitionSpeed;
	}
	
	@Override
	public void initWithParams(String[] params) {
		myAmmunition = params[0];
		myAmmunitionAmount = Integer.parseInt(params[1]);
		myAmmunitionSpeed = Double.parseDouble(params[2]);
		myEnemyInSightRange = Double.parseDouble(params[3]);
		myDirectionToFire = Double.parseDouble(params[4]);
	}

	public List<String> getTargets() {
		return myTargets;
	}

	public void setTargets(List<String> myTargets) {
		this.myTargets = myTargets;
	}

	public double getCurrentTimeStep() {
		return currentTimeStep;
	}

	public void incrementCurrentTimeStep(){
		currentTimeStep++;
	}
	
	public void resetCurrentTimeStep(){
		currentTimeStep = 0;
	}

	public double getFiringRate() {
		return myFiringRate;
	}

	public void setFiringRate(double myFiringRate) {
		this.myFiringRate = myFiringRate;
	}
	
}
