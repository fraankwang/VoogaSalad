package engine.backend.deprecated;

import engine.backend.components.Component;

public class ArmorComponent extends Component {

	private double resistanceToDamage;

	public void setResistanceToDamage(double value) {
		this.resistanceToDamage = value;
	}

	public void setResistanceToDamage(String value) {
		double newValue = Double.parseDouble(value);
		this.resistanceToDamage = newValue;
	}

	@Override
	public String getComponentInfo() {
		return "ResistanceToDamage:" + resistanceToDamage;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equals("ResistanceToDamage")) {
			this.resistanceToDamage = Double.parseDouble(data);
		}
	}
}
