package engine.backend.components;

public class PurchaseComponent extends Component implements IComponent{

	private double myValue;
	
	public void setValue(double myValue) {
		this.myValue = myValue;
	}
	
	public String getComponentInfo() {
		return myValue + "";
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub
		
	}

}
