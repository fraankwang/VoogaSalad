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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ShopView {
	
	private EngineView myEngineView;
	String myType;
	private HBox myHBox; 
	private ImageView myImageView;
	
	public ShopView(EngineView ev, String image, String type, double cost, double width, double height){
		myHBox = new HBox();
		
		Text name = new Text(type);		
		myEngineView = ev;
		myType = type;
		myImageView = new ImageView(new Image(image));
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		
		myHBox.getChildren().addAll(name, myImageView);
		myHBox.setOnDragDetected(e -> handleClick(e));
	}
		
	public void handleClick(MouseEvent e){
		
		//myEngineView.getStage().getScene().setCursor(value);
		myEngineView.getDummyCursor().changePic(myImageView.getImage());
		Dragboard db = myHBox.startDragAndDrop(TransferMode.ANY);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(myType);
        db.setContent(content);
        e.consume();		
	}
	
	public Node getNode(){
		return myHBox;
	}
}
