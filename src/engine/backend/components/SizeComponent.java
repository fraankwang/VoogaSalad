/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */
package engine.backend.components;

public class SizeComponent extends Component implements IComponent {
	private double width;
	private double height;

	private static final int DEFAULT_HEIGHT = 50;
	private static final int DEFAULT_WIDTH = 50;

	// default component
	public SizeComponent() {
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
	}

	public SizeComponent(SizeComponent component) {
		this.width = component.getWidth();
		this.height = component.getHeight();
	}

	@Override
	public String toString() {
		return this.getTag() + " with width: " + this.width + " with height: " + this.height;
	}
/**
 * 
 * @return height
 */
	public double getHeight() {
		return height;
	}
/**
 * Increases height and width by the same delta
 * @param delta
 */
	public void increaseSize(int delta) {
		this.width += delta;
		this.height += delta;
	}
/**
 * Parses string to increase size
 * @param delta
 */
	public void increaseSize(String delta) {
		int newVal = Integer.parseInt(delta);
		increaseSize(newVal);
	}
/**
 * 
 * @return width
 */
	public double getWidth() {
		return width;
	}

	@Override
	public String getComponentInfo() {
		return "Width:" + width + "," + "Height:" + height;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equalsIgnoreCase("Width")) {
			this.width = Double.parseDouble(data);
			return;
		}
		if (dataName.equalsIgnoreCase("Height")) {
			this.height = Double.parseDouble(data);
			return;

		}
	}
	public void initWithParams(String[] params) {
		if (params.length > 0) {
			this.width = Double.parseDouble(params[0]);
			this.height = Double.parseDouble(params[1]);
		}
	}

}
