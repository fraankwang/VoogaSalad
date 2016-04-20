package engine.frontend;


import java.util.List;

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
		myPane.setMinSize(myEngineView.loadUIIntResource("ShopWidth")/2, myEngineView.loadUIIntResource("ShopHeight"));
		myPane.setPrefSize(myEngineView.loadUIIntResource("ShopWidth"), myEngineView.loadUIIntResource("ShopHeight"));
		myPane.setMaxSize(myEngineView.loadUIIntResource("ShopWidth"), myEngineView.loadUIIntResource("ShopHeight"));
		
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadUIIntResource("ShopSpacing"));
		
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);
		createShop(0,0,"DrumpfVader.png", "Drumpf", 20, 20);
		
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
