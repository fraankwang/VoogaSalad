package engine.backend.components;

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
	
	@Override
	public void initWithParams(String[] params) {
		myAmmunition = params[0];
		myAmmunitionAmount = Integer.parseInt(params[1]);
		myAmmunitionSpeed = Double.parseDouble(params[2]);
		myEnemyInSightRange = Double.parseDouble(params[3]);
		myDirectionToFire = Double.parseDouble(params[4]);
	}
	
	@Override
	public String getValue() {
		return myAmmunitionSpeed + "";
	}
	
}
