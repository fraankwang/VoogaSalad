package engine.frontend.shop;
/**
 * @author HaydenBader
 */
import java.util.List;
import java.util.Map;

import engine.frontend.overall.EngineView;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ShopCell extends ListCell<Map<String, String>> {
	
	private EngineView myEngineView;
	
	ShopCell(EngineView ev){
		myEngineView = ev;
	}
	
	@Override
    public void updateItem(Map<String, String> itemMap, boolean empty) {
		
        super.updateItem(itemMap, empty);
        if (itemMap != null) {
            HBox hbox = new HBox();
//            Color paletteColor = Color.web(item.split("=")[1]);
//            Text text = new Text(item);
//            
            Text name = new Text(itemMap.get("name"));
            Image myImage = new Image(itemMap.get("image"));
            ImageView myPic = new ImageView(myImage);
            myPic.setFitWidth(myEngineView.loadDoubleResource("ShopCellWidth"));
            myPic.setFitHeight(myEngineView.loadDoubleResource("ShopCellHeight"));
            
        	setOnMousePressed(e -> handleClick(e));
            hbox.getChildren().addAll(name, myPic);
   	       	myPic.setImage(myImage);
   	       	setText(String.valueOf(itemMap.get("cost")));
   	        setTextAlignment(TextAlignment.RIGHT);     
            setGraphic(hbox);
        }
    }


	
	private void handleClick(MouseEvent e){
		myEngineView.getEngineController().shopClicked(this.getItem().get("name"));
		myEngineView.getStage().getScene().setCursor(Cursor.NONE);
		myEngineView.getDummyCursor().updateLocation(e.getSceneX(), e.getSceneY());
		myEngineView.getDummyCursor().changePic(new Image(this.getItem().get("image")));		
	}
}
