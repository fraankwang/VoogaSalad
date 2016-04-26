package engine.frontend.shop;

/**
 * @author HaydenBader
 */
import java.util.List;
import java.util.Map;

import engine.backend.game_features.ShopItem;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class ShopPane extends AbstractPane {
	private VBox myVBox;
	private ListView<Map<String, String>> myListView;

	public ShopPane(EngineView ev) {
		super(ev);
		myListView = new ListView<Map<String, String>>();
	}

	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		myPane.setStyle("-fx-border-color: black;");

		myVBox = new VBox();
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));

		myPane.getChildren().add(myVBox);

//		 VBox.setVgrow(myVBox, Priority.ALWAYS);
		VBox.setVgrow(myPane, Priority.ALWAYS);

		ShopItem tester = new ShopItem("tempEntity2", "DrumpfVader.png", 10);

		addShopObject(tester);
		return myPane;
	}

	public void createShop(String image, String type, double cost) {

	}

	public void addShopObject(ShopItem myShopItem) {
		ShopView myShopView = new ShopView(myEngineView);
		myVBox.getChildren().add(myShopView.buildShopView(myShopItem.getItemImage(), myShopItem.getItemName(),
				myShopItem.getItemValue(), 40, 40));
	}

	public void updateShop(List<ShopItem> myShopList) {

	}

}
