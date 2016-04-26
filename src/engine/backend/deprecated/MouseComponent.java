package engine.backend.deprecated;

import engine.backend.components.Component;
import engine.backend.components.IComponent;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseComponent extends Component implements IComponent {

	private boolean clicked, cursorOver;

	public MouseComponent() {
		clicked = false;
		cursorOver = false;
	}

	public MouseComponent(MouseComponent component) {
    	this.clicked = component.isClicked();
    	this.cursorOver = component.isCursorOver();
    }

	public boolean isClicked() {
		return clicked;
	}

	public boolean isCursorOver() {
		return cursorOver;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void setCursorOver(boolean cursorOver) {
		this.cursorOver = cursorOver;
	}

	@Override
	public String getTag() {
		return "Mouse";
	}

	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
	}
}
