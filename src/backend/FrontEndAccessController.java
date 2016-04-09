package backend;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FrontEndAccessController implements IFrontEndAccess {

	private Group myRoot;
	
	public FrontEndAccessController(Group myRoot) {
		this.myRoot = myRoot;
	}
	
	public void createCharacter(double xCoord, double yCoord, String image){
		ImageView myPlayer = new ImageView(new Image(getClass().getResourceAsStream(image)));
		myPlayer.setFitWidth(80);
		myPlayer.setFitHeight(100);
		myPlayer.setX(xCoord);
		myPlayer.setY(yCoord);
		myRoot.getChildren().add(myPlayer);
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

}