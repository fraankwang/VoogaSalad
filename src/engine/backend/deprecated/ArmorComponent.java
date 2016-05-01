package engine.backend.deprecated;

import engine.backend.components.Component;

public class ArmorComponent extends Component {

	private double resistanceToDamage;

	/**
	 * Sets resistance to Damage for Armor Component
	 * @param value
	 */
	public void setResistanceToDamage(double value){
		this.resistanceToDamage = value;
	}
	/**
	 * Parses String into double value to set resistance to damage
	 * @param value
	 */
	public void setResistanceToDamage(String value){

		double newValue = Double.parseDouble(value);
		this.resistanceToDamage = newValue;
	}

	@Override
	/**
	 * Returns Name and its double value
	 */
	public String getComponentInfo() {
		return "ResistanceToDamage:" + resistanceToDamage;
	}

	@Override
	/**
	 * Sets resistance to updated value
	 */
	public void update(String dataName, String data) {
		if (dataName.equals("ResistanceToDamage")) {
			setResistanceToDamage(data);
		}
	}
}
