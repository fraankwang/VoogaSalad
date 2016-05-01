package engine.backend.components;

import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class EmptyComponent extends Component {

	private static final String EMPTY = "EmptyComponent";
	private static final String EMPTYCOMPONENT = "EmptyComponent";
	
	private ExceptionLoader myExceptionLoader;
	
	public EmptyComponent() {
		// TODO Auto-generated constructor stub
		myExceptionLoader = new ExceptionLoader();
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return "Tag:" + EMPTY;
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub
		new DrumpfTowerException(myExceptionLoader.getString(EMPTYCOMPONENT));
	}

}
