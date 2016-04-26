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
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;




public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane;
	private VBox myVBox = new VBox();
	private ListView<Map<String, String>> myListView; 
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		
		myListView = new ListView<Map<String, String>>();
				
		myPane = new Pane();
		myPane.setStyle("-fx-border-color: black;");
		
		bindWidth(widthBinding);
		bindHeight(heightBinding);
		
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
		myVBox.getChildren().add(myListView);
	    VBox.setVgrow(myListView, Priority.ALWAYS);
	    myListView.setCellFactory( e-> handleShopCreation());
		
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
		ShopView myShopView = new ShopView(myEngineView);
		Map<String, String> addMap = new HashMap();
		addMap.put("name", myShopItem.getItemName());
		addMap.put("image", myShopItem.getItemImage());
		addMap.put("cost",  Double.toString(myShopItem.getItemValue()));
		myListView.getItems().add(addMap);
		myVBox.getChildren().add(myShopView.buildShopView(myShopItem.getItemImage(), myShopItem.getItemName(), myShopItem.getItemValue(), 40.0, 40.0));
	}
//	
//	private void handleMouseClick(MouseEvent e, Map<String, String> myMap) {
//		myEngineView.getEngineController().shopClicked(myMap.get("name"));
//		myEngineView.getStage().getScene().setCursor(Cursor.NONE);
//		myEngineView.getDummyCursor().updateLocation(e.getSceneX(), e.getSceneY());
//		myEngineView.getDummyCursor().changePic(new Image(myMap.get("image")));	
//	}

	private void bindWidth(DoubleExpression db){
		myPane.minWidthProperty().bind(db);
		myPane.maxWidthProperty().bind(db);
	}
	
	private void bindHeight(DoubleExpression db){
		myPane.minHeightProperty().bind(db);
		myPane.maxHeightProperty().bind(db);
	}
	
	public void updateShop(List<ShopItem> myShopList){
		
	}

}
