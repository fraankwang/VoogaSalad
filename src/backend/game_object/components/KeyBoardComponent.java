package backend.game_object.components;

/**
 * Created by colinduffy on 4/12/16.
 */
public class KeyBoardComponent extends Component implements IComponent{
    private String keyValue;
    private boolean keyPressed;

    public KeyBoardComponent(){
        keyValue = "";
        keyPressed = false;
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
        return "Keyboard";
    }
}
