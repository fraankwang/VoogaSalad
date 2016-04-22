package engine.frontend.shop;


import java.util.List;

import engine.backend.game_features.ShopItem;
import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;



public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane = new Pane();
	private VBox myVBox = new VBox();
	
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
		
		ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		
		addShopObject(tester);
		
		myPane.getChildren().add(myVBox);
		return myPane;
	}
	
	public void createShop(double xCoord, double yCoord, String image, String type, double width, double height){
		
		ShopView myTower = new ShopView(myEngineView, image, type, width, height);
		myVBox.getChildren().add(myTower.getNode());	
	}

	public void addShopObject(ShopItem myShopItem){
		double DEFAULT_W = 40;
		double DEFAULT_H = 40;
		ShopView myTower = new ShopView(myEngineView, myShopItem.getItemImage(), myShopItem.getItemName(), DEFAULT_W, DEFAULT_H);
		myVBox.getChildren().add(myTower.getNode());
	}
	
	public void updateShop(List<ShopItem> myShopList){

	}
}
