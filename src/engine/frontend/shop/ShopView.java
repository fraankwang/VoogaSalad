package engine.frontend.shop;

/**
 * @author HaydenBader
 */
import engine.controller.EngineController;
import engine.frontend.overall.EngineView;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ShopView {
	
	private EngineView myEngineView;
	String myType;
	private HBox myHBox; 
	private ImageView myImageView;
	
	public ShopView(EngineView ev, String image, String type, double width, double height){
		myHBox = new HBox();
		
		Text name = new Text(type);		
		myEngineView = ev;
		myType = type;
		
		myImageView = new ImageView(new Image(image));
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		
		myHBox.getChildren().addAll(name, myImageView);
		
		myHBox.setOnMousePressed(e -> handleClick(e));
	}
		
	public void handleClick(MouseEvent e){
		myEngineView.getEngineController().shopClicked(myType);
		myEngineView.getStage().getScene().setCursor(Cursor.NONE);
		myEngineView.getDummyCursor().updateLocation(e.getSceneX(), e.getSceneY());
		myEngineView.getDummyCursor().changePic(myImageView.getImage());		
	}
	
	public Node getNode(){
		return myHBox;
	}
}
