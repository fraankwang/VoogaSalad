package backend;

import java.util.List;

public class DisplayComponent implements Component {

	private String name;

	public DisplayComponent(String name) {
		this.name = name;
	}

	public DisplayComponent() {

	}

	public String getName() {
		return name;
	}

	@Override
	public void initWithParams(List parameters) {
		// TODO Auto-generated method stub

	}

}
