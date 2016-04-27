package engine.frontend.shop;
/**
 * @author HaydenBader
 */
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.frontend.overall.EngineView;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ShopCell extends ListCell<Map<String, String>> {
	
	private EngineView myEngineView;
	private Text myName;
	private Image myImage;
	public static final String DEFAULT_RESOURCE = "engine/resources/shop_cell";
	private ResourceBundle myResources;
	
	ShopCell(EngineView ev){
		myEngineView = ev;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
	}
	
	@Override
    public void updateItem(Map<String, String> itemMap, boolean empty) {
		
        super.updateItem(itemMap, empty);
        if (itemMap != null) {
            HBox hbox = new HBox();
//            Color paletteColor = Color.web(item.split("=")[1]);
//            Text text = new Text(item);
//            
            myName = new Text(itemMap.get("name"));
            myImage = new Image(itemMap.get("image"));
            ImageView myPic = new ImageView(myImage);
            myPic.setFitWidth(myEngineView.loadDoubleResource("ShopCellWidth") * myEngineView.getStage().getWidth());
            myPic.setFitHeight(myEngineView.loadDoubleResource("ShopCellHeight") * myEngineView.getStage().getHeight());
            
            if(itemMap.get("purchasable").equals("true")){
                setOnDragDetected(e -> selectTower(e));
            }else{
            	setOnDragDetected(null);
            }


            
            hbox.getChildren().addAll(myName, myPic);
   	       	setText(myResources.getString("ShopCostPrompt") + String.valueOf((itemMap.get("cost"))));
   	        setTextAlignment(TextAlignment.RIGHT);     
   	        hbox.setAlignment(Pos.CENTER_LEFT);
            setGraphic(hbox);
        }
    }


	
	private void selectTower(MouseEvent e){
		myEngineView.getDummyCursor().changePic(myImage);
		myEngineView.getStage().getScene().setCursor(Cursor.NONE);
		Dragboard db = this.startDragAndDrop(TransferMode.ANY);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(myName.getText());
        db.setContent(content);
        e.consume();		
	}
}
