package engine.frontend.shop;

import java.util.HashMap;
/**
 * @author HaydenBader
 */
import java.util.List;
import java.util.Map;
import engine.backend.game_features.ShopItem;
import engine.frontend.overall.EngineView;
import engine.frontend.shop.ShopCell;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;



public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane = new Pane();
	private VBox myVBox = new VBox();
	//private ListView<Map<String, String>> myListView = new ListView<Map<String, String>>()
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){

		myPane.setStyle("-fx-border-color: black;");
		//we can make this a superclass
		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
		
		//myVBox.getChildren().add(myListView);
	    //VBox.setVgrow(myListView, Priority.ALWAYS);
	    //myListView.setCellFactory( e-> handleShopCreation());
		
		//Map<String, String> addMap = new HashMap();
		//addMap.put("name", "Trumpf");
		//addMap.put("image", "DrumpfVader.png");
		//addMap.put("cost", Double.toString(10.0));
		//myListView.getItems().add(addMap);
	    
	    ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		
		addShopObject(tester);	
		myPane.getChildren().add(myVBox);
		
		return myPane;
	}
	
	private ListCell<Map<String, String>> handleShopCreation(){
		ListCell<Map<String, String>> myVal = new ShopCell(myEngineView);
		return myVal;
	}
	
	public void createShop(String image, String type, double cost){
		Map<String, String> addMap = new HashMap();
		addMap.put("name", type);
		addMap.put("image", image);
		addMap.put("cost", Double.toString(cost));
		
		//myListView.getItems().add(addMap);
		//ShopView myTower = new ShopView(myEngineView, image, type, width, height);
		//myVBox.getChildren().add(myTower.getNode());	
	}

	public void addShopObject(ShopItem myShopItem){
		ShopView myShopView = new ShopView(myEngineView, myShopItem.getItemImage(), myShopItem.getItemName(), 40, 40);	
//		Map<String, String> addMap = new HashMap();
//		addMap.put("name", myShopItem.getItemName());
//		addMap.put("image", myShopItem.getItemImage());
//		addMap.put("cost", Double.toString(myShopItem.getItemValue()));
//		myListView.getItems().add(addMap);
		//myListView.setOnMouseClicked( e -> handleMouseClick(e, myListView.getItems().get(myListView.getItems().indexOf(addMap))));
		myVBox.getChildren().add(myShopView.getNode());
	}
//	
//	private void handleMouseClick(MouseEvent e, Map<String, String> myMap) {
//		myEngineView.getEngineController().shopClicked(myMap.get("name"));
//		myEngineView.getStage().getScene().setCursor(Cursor.NONE);
//		myEngineView.getDummyCursor().updateLocation(e.getSceneX(), e.getSceneY());
//		myEngineView.getDummyCursor().changePic(new Image(myMap.get("image")));	
//	}

	public void updateShop(List<ShopItem> myShopList){
		
	}
}
