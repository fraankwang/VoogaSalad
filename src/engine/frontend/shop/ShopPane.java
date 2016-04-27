package engine.frontend.shop;

/**
 * @author HaydenBader
 */
import java.util.List;

import engine.backend.game_features.ShopItem;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShopPane extends AbstractPane {
	private VBox myVBox;
	private ListView<ShopItem> myShopList;
	private CurrentView myCurrentView;
	private ListView<ShopItem> myUpgradeList;

	public ShopPane(EngineView ev) {
		super(ev);
	}

	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		Pane myPane = super.getPane();
		myPane.setStyle("-fx-border-color: black");

		myVBox = new VBox();
		bindWidth(myVBox, myPane.widthProperty());
		bindHeight(myVBox, myPane.heightProperty());
		myVBox.setSpacing(getEngineView().loadDoubleResource("ShopSpacing"));
		
		ShopItem tester = new ShopItem("tempEntity2", "DrumpfVader.png", 10);
		
		myShopList = new ListView<ShopItem>();
		myUpgradeList = new ListView<ShopItem>();
		
		setupShopList();
		myCurrentView = new CurrentView(this);
		myVBox.getChildren().add(myCurrentView.buildCurrentView(tester, myVBox.widthProperty(), myVBox.heightProperty().multiply(.1)));
		setupUpgradeList();
		myPane.getChildren().add(myVBox);
		
		addShopObject(tester);
		return myPane;
	}
	
	private void setupShopList(){
		myShopList = new ListView<ShopItem>();
		myShopList.setCellFactory(e -> {return new ShopCell(this);});
		bindWidth(myShopList, myVBox.widthProperty());
		bindHeight(myShopList, myVBox.heightProperty().multiply(.6));
		myVBox.getChildren().add(myShopList);
	}
	
	private void setupUpgradeList(){
		myUpgradeList = new ListView<ShopItem>();
		myUpgradeList.setCellFactory(e -> {return new UpgradeCell(this);});
		bindWidth(myUpgradeList, myVBox.widthProperty());
		bindHeight(myUpgradeList, myVBox.heightProperty().multiply(.3));
		myVBox.getChildren().add(myUpgradeList);
	}
	
	public void addShopObject(ShopItem myShopItem) {
		myShopList.getItems().add(myShopItem);
	}

	public void updateShop(List<ShopItem> list) {
		myShopList.getItems().clear();
		myShopList.getItems().addAll(list);
	}
	
	public void updateCurrentView(ShopItem sp){
		myCurrentView.updateCurrentView(sp);
	}
	
	public CurrentView getCurrentView(){
		return myCurrentView;
	}
}
