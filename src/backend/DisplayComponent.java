package backend;

public class DisplayComponent implements Component {
	
	private String name;
	
	public DisplayComponent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
