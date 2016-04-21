package engine.frontend.shop;


import java.util.List;

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
		myPane.setMinSize(myEngineView.loadDoubleResource("ShopWidth")/2, myEngineView.loadDoubleResource("ShopHeight"));
		myPane.setPrefSize(myEngineView.loadDoubleResource("ShopWidth"), myEngineView.loadDoubleResource("ShopHeight"));
		myPane.setMaxSize(myEngineView.loadDoubleResource("ShopWidth"), myEngineView.loadDoubleResource("ShopHeight"));
		
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);

		//ShopView testShopView = new ShopView(myEngineView);
		//myPane.getChildren().add(testShopView.getNode());	
		
		myPane.getChildren().add(myVBox);
		return myPane;
	}
	
	public void createShop(double xCoord, double yCoord, String image, String type, double width, double height){
		
		ShopView myTower = new ShopView(myEngineView, xCoord, yCoord, image, type, width, height);
		myVBox.getChildren().add(myTower.getNode());	
	}

	public void updateShop(List<ShopView> myShopList){

	}
}
