package engine.backend.deprecated;

import engine.backend.components.Component;

public class DamageComponent extends Component {

	private double myDamageToHealth;
	private double myDamageToVelocity;
	// private Vector myDamageToPosition;

	public DamageComponent() {
	}

	/**
	 * Returns how much health is damaged
	 * 
	 * @return myDamageToHealth value
	 */
	public double getDamageToHealth() {
		return myDamageToHealth;
	}

	/**
	 * Sets damage to health
	 * 
	 * @param myDamage
	 */
	public void setDamageToHealth(double myDamage) {
		this.myDamageToHealth = myDamage;
	}

	/**
	 * Parses string of damage to myDamageToHealth
	 * 
	 * @param myDamage
	 */
	public void setDamageToHealth(String myDamage) {
		double newVal = Double.parseDouble(myDamage);
		this.myDamageToHealth = newVal;
	}

	/**
	 * Returns how much velocity is affected by the damage
	 * 
	 * @return myDamageToVelocity
	 */
	public double getDamageToVelocity() {
		return myDamageToVelocity;
	}

	/**
	 * Sets damage to velocity
	 * 
	 * @param myDamage
	 */
	public void setDamageToVelocity(double myDamageToVelocity) {
		this.myDamageToVelocity = myDamageToVelocity;
	}

	/**
	 * Parses string of damage to myDamageToVelocity
	 * 
	 * @param myDamageToVelocity
	 */

	public void setDamageToVelocity(String myDamageToVelocity) {
		double newVal = Double.parseDouble(myDamageToVelocity);
		this.myDamageToVelocity = newVal;
	}

	@Override
	public String getComponentInfo() {
		return myDamageToHealth + "";
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

}
