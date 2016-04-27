package engine.backend.components;


/**
 * 
 * @author 
 *
 */

public class HealthComponent extends Component {
	
	private double myHealth;
	private double myDamage;
	private double myCriticalHealth;
	
	public HealthComponent() {
		
	}
	
	public HealthComponent(HealthComponent component) {
		this.myHealth = component.getHealth();
		this.myDamage = component.getDamage();
		this.myCriticalHealth = component.getCriticalHealth();
	}
	
	public HealthComponent(double myHealth){
		this.myHealth = myHealth; 
	}
	
	public double getHealth(){
		return myHealth;
	}
	
	/**
	 * Sets the health of the entity with this health component.
	 * @param deltaHealth
	 */
	public void setHealth(String deltaHealth){
		Double delta = Double.parseDouble(deltaHealth);
		myHealth += delta;
	}
	
	/**
	 * Sets the health of an entity with this health component.
	 * @param newHealth
	 */
	public void setHealth(double newHealth){
		myHealth = newHealth;
	}
	
	/**
	 * Sets the damage taken by an entity with this health component.
	 * @param newDamage
	 */
	public void setDamage(double newDamage){
		myDamage = newDamage;
	}
	
	/**
	 * Sets the damage taken by an entity with this health component.
	 * @param deltaDamage
	 */
	public void setDamage(String deltaDamage){
		Double delta = Double.parseDouble(deltaDamage);
		myDamage = delta;
	}

	/**
	 * 
	 * @return A double representing the damage. 
	 */
	public double getDamage() {
		return myDamage;
	}

	/**
	 * 
	 * @return The critical health for an entity with this component.
	 */
	public double getCriticalHealth() {
		return myCriticalHealth;
	}

	/**
	 * Sets the critical health for an entity with this component.
	 * @param myCriticalHealth
	 */
	public void setCriticalHealth(double myCriticalHealth) {
		this.myCriticalHealth = myCriticalHealth;
	}
	
	/**
	 * Sets the critical health for an entity with this component.
	 * @param myCriticalHealth
	 */
	public void setCriticalHealth(String myCriticalHealth) {
		double newVal = Double.parseDouble(myCriticalHealth);
		this.myCriticalHealth = newVal;
	}

	@Override
	public String getComponentInfo() {
		return "Health:" + myHealth + "," + "CriticalHealth:" + myCriticalHealth;
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "Health":
			this.myHealth = Double.parseDouble(data);
			return;
		case "CriticalHealth":
			this.myCriticalHealth = Double.parseDouble(data);
			return;
			
		}
	}

}
