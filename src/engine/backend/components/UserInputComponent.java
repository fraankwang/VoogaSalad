package engine.backend.components;

import java.util.List;

/**
 * Created by colinduffy on 4/12/16.
 */
public class UserInputComponent extends Component implements IComponent {

    private boolean clicked, cursorOver, keyPressed;
    private String keyValue;

    public UserInputComponent(){
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
    public void initWithParams(List params) {

    }

    @Override
    public String getTag(){
        return "UserInput";
    }
}
