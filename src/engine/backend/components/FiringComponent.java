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
	private double myDirectionToFire;
	
	public FiringComponent(String ammunitionName, int amount, double speed, double range, double direction){
		myAmmunition = ammunitionName;
		myAmmunitionAmount = amount;
		myAmmunitionSpeed = speed;
		myEnemyInSightRange = range;
		myDirectionToFire = direction;
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

	@Override
	public void initWithParams(List params) {
	
	}
	
	public double getDirectionToFire() {
		return myDirectionToFire;
	}

	public void setDirectionToFire(double myDirectionToFire) {
		this.myDirectionToFire = myDirectionToFire;
	}

	public double getAmmunitionSpeed() {
		return myAmmunitionSpeed;
	}

	public void setAmmunitionSpeed(double myAmmunitionSpeed) {
		this.myAmmunitionSpeed = myAmmunitionSpeed;
	}
	
}
