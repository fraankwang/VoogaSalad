package engine.backend;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FrontEndAccessController implements IFrontEndAccess {

	private Group myRoot;
	private boolean keyEvent, mouseClickedEvent;
	public FrontEndAccessController(Group myRoot) {
		this.myRoot = myRoot;
	}
	
	public void createCharacterImage(double xCoord, double yCoord, String image, double width, double height){
		ImageView myPlayer = new ImageView(new Image(image));
		myPlayer.setFitWidth(width);
		myPlayer.setFitHeight(height);
		myPlayer.setX(xCoord);
		myPlayer.setY(yCoord);
		myRoot.getChildren().add(myPlayer);
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub


	}

    private void setKeyEvent(boolean newBoolean){
        keyEvent = newBoolean;
    }
    private void setMouseClickedEvent(boolean newBoolean){
        mouseClickedEvent = newBoolean;
    }
	
	public void handleEvent(Event e){
		if(e instanceof KeyEvent){
			 System.out.println("hello");
            setKeyEvent(true);
            setMouseClickedEvent(false);
		}else if(e instanceof MouseEvent){
            setKeyEvent(false);
            setMouseClickedEvent(true);
        }else{
            setKeyEvent(false);
            setMouseClickedEvent(false);
        }
	}

	public boolean isKeyEvent(){
		return keyEvent;
	}
	public boolean isMouseClickedEvent(){
		return mouseClickedEvent;
	}
}
