package backend;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class FrontEndAccessController implements IFrontEndAccess {

	private Group myRoot;
	
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
	
	public void handleEvent(Event e){
		if(e instanceof KeyEvent){
			 System.out.println("hello");
		}
	}

}
