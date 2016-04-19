package engine.frontend;


import java.util.List;

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

		myPane.setMinSize(myEngineView.loadUIIntResource("ShopWidth")/2, myEngineView.loadUIIntResource("ShopHeight"));
		myPane.setPrefSize(myEngineView.loadUIIntResource("ShopWidth"), myEngineView.loadUIIntResource("ShopHeight"));
		myPane.setMaxSize(myEngineView.loadUIIntResource("ShopWidth"), myEngineView.loadUIIntResource("ShopHeight"));
		myPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		
		ShopView testShopView = new ShopView(myEngineView);
		myPane.getChildren().add(testShopView.getNode());
			
		return myPane;
	}

	public void updateShop(List<ShopView> myShopList){

	}
}
