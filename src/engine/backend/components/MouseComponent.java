package engine.backend.components;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseComponent extends Component {

    private boolean clicked, cursorOver;

    public MouseComponent(){
        clicked = false;
        cursorOver = false;
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
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String dataName, String data) {
	}

	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
	}
}
