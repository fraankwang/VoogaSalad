package backend.game_object.components;

/**
 * Created by colinduffy on 4/10/16.
 */
public class InputComponent extends Component implements IComponent {

    private boolean clicked, cursorOver, keyPressed;
    private String keyValue;

    public InputComponent(){
        clicked = false;
        cursorOver = false;
        keyPressed = false;
        keyValue = "";
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

    public boolean isKeyPressed() {
        return keyPressed;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }


    @Override
    public String getTag() {
        return "Mouse";
    }
}
