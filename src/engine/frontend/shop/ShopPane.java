package engine.frontend.shop;


/**
 * @author HaydenBader
 */
import java.util.List;

import engine.backend.game_features.ShopItem;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;



public class ShopPane {
	private EngineView myEngineView;
	private ScrollPane myScrollPane;
	private VBox myVBox = new VBox();
	//private ListView<Map<String, String>> myListView = new ListView<Map<String, String>>()
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		myScrollPane = new ScrollPane();
		myScrollPane.setStyle("-fx-border-color: black;");
		
		bindWidth(widthBinding);
		bindHeight(heightBinding);
		
		myVBox.minWidthProperty().bind(myScrollPane.widthProperty());
		myVBox.minHeightProperty().bind(myScrollPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
		myScrollPane.setContent(myVBox);
		
		//VBox.setVgrow(myVBox, Priority.ALWAYS);
		VBox.setVgrow(myScrollPane, Priority.ALWAYS);
		
	    ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		
		addShopObject(tester);
		return myScrollPane;
	}
	
	private void bindWidth(DoubleExpression db){
		myScrollPane.minWidthProperty().bind(db);
		myScrollPane.maxWidthProperty().bind(db);
	}
	
	private void bindHeight(DoubleExpression db){
		myScrollPane.minHeightProperty().bind(db);
		myScrollPane.maxHeightProperty().bind(db);
	}
	
	public void createShop(String image, String type, double cost){

	}

	public void addShopObject(ShopItem myShopItem){
		ShopView myShopView = new ShopView(myEngineView, myShopItem.getItemImage(), myShopItem.getItemName(), myShopItem.getItemValue(), 40, 40);			
		myVBox.getChildren().add(myShopView.getNode());
	}


	public void updateShop(List<ShopItem> myShopList){
		
	}

}
