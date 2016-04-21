package engine.frontend.shop;


import java.util.List;

import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane = new Pane();

	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){

		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		myPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		
		ShopView testShopView = new ShopView(myEngineView);
		myPane.getChildren().add(testShopView.getNode());	
		
		return myPane;
	}

	public void updateShop(List<ShopView> myShopList){

	}
}
