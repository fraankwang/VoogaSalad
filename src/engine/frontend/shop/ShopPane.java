package engine.frontend.shop;


import java.util.HashMap;


/**
 * @author HaydenBader
 */
import java.util.List;
import java.util.Map;
import engine.backend.game_features.ShopItem;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import engine.frontend.shop.ShopCell;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.beans.binding.DoubleExpression;


public class ShopPane extends AbstractPane {
	private VBox myVBox;
	private ListView<Map<String, String>> myListView;
	
	private ListCell<Map<String, String>> handleShopCreation(){
		ListCell<Map<String, String>> myVal = new ShopCell(myEngineView);
		return myVal;
	}
	
	
	public ShopPane(EngineView ev) {
		super(ev);
		myListView = new ListView<Map<String, String>>();
	}

	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		Pane myPane = super.getPane();
		myPane.setStyle("-fx-border-color: black");

		myListView = new ListView<Map<String, String>>();
	    VBox.setVgrow(myListView, Priority.ALWAYS);
	    myListView.setCellFactory( e-> handleShopCreation());

	    ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		myVBox = new VBox();
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		
		myVBox.setSpacing(getEngineView().loadDoubleResource("ShopSpacing"));

		myPane.getChildren().add(myVBox);
		myVBox.getChildren().add(myListView);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		addShopObject(tester);
		return myPane;
	}

	public void createShop(String image, String type, double cost) {

		
			Map<String, String> addMap = new HashMap();
			addMap.put("name", type);
			addMap.put("image", image);
			addMap.put("cost", Double.toString(cost));
			
			myListView.getItems().add(addMap);
		
	}

	public void addShopObject(ShopItem myShopItem) {
		ShopView myShopView = new ShopView(getEngineView());
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

	public void updateShop(List<ShopItem> myShopList){
		

	}

}
