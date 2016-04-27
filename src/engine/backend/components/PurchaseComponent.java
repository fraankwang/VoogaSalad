package engine.backend.components;

/**
 * 
 * @author 
 *
 */
public class PurchaseComponent extends Component {

	private double myValue;
	
	/**
	 * Initializes a purchase component with an existing purchase component.
	 * @param component
	 */
	public PurchaseComponent(PurchaseComponent component) {
		this.myValue = component.getValue();
	}
	
	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @return The double with the value of this component. Represents purchase price.
	 */
	public double getValue() {
		return myValue;
	}

	/**
	 * Sets the value of this component. Represents purchase price.
	 * @param myValue
	 */
	public void setValue(double myValue) {
		this.myValue = myValue;
	}

}
