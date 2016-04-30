package engine.frontend.shop;

import java.util.HashMap;
/**
 * @author HaydenBader
 */
import java.util.List;

import engine.backend.entities.IEntity;
import engine.backend.game_features.ShopItem;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShopPane extends AbstractPane {
	public static final String RESOURCE_NAME = "shop";
	
	private VBox myVBox;
	private ListView<ShopItem> myShopList;
	private CurrentView myCurrentView;
	private ListView<ShopItem> myUpgradeList;
	

	public ShopPane(EngineView ev) {
		super(ev, RESOURCE_NAME);
	}

	/**
	 * Instantiates the node binding and attaching all underlying children
	 */
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		Pane myPane = super.getPane();
		myPane.setStyle("-fx-border-color: black");

		myVBox = new VBox();
		bindWidth(myVBox, myPane.widthProperty());
		bindHeight(myVBox, myPane.heightProperty());
		myVBox.setSpacing(loadDoubleResource("ShopSpacing"));
		
		setupShopList();		
		setupUpgradeList();
		myCurrentView = new CurrentView(this);
		myVBox.getChildren().add(myCurrentView.buildCurrentView(myVBox.widthProperty(), myVBox.heightProperty().multiply(.1)));
		
		myPane.getChildren().add(myVBox);
		myPane.setOnKeyPressed(null);
		
		return myPane;
	}
	
	/**
	 * instantiates the shop listView
	 */
	private void setupShopList(){
		myShopList = new ListView<ShopItem>();
		myShopList = new ListView<ShopItem>();
		myShopList.setCellFactory(e -> {return new ShopCell(this);});
		myShopList.setOnKeyPressed(null);
		bindWidth(myShopList, myVBox.widthProperty());
		bindHeight(myShopList, myVBox.heightProperty().multiply(.6));
		myVBox.getChildren().add(myShopList);
	}
	
	/**
	 * instantiates the upgrade listView
	 */
	private void setupUpgradeList(){
		myUpgradeList = new ListView<ShopItem>();
		myUpgradeList = new ListView<ShopItem>();
		myUpgradeList.setCellFactory(e -> {return new UpgradeCell(this);});
		bindWidth(myUpgradeList, myVBox.widthProperty());
		bindHeight(myUpgradeList, myVBox.heightProperty().multiply(.3));
		myVBox.getChildren().add(myUpgradeList);
	}
	
	/**
	 * Allows others to add a shop item to the shop listView
	 * @param myShopItem - contains shop name, image, cost, and if it's interactable
	 */
	public void addShopObject(ShopItem myShopItem) {
		myShopList.getItems().add(myShopItem);
	}

	/**
	 * Updates the shop given a list of shopItems
	 * @param list - list of shopItems - each shopItem contains shon name, image, cost, and if it's interactable
	 */
	public void updateShop(List<ShopItem> list) {
		myShopList.getItems().clear();
		myShopList.getItems().addAll(list);
	}
	
	public void updateUpgrade(List<ShopItem> upgradelist) {
		myUpgradeList.getItems().clear();
		myUpgradeList.getItems().addAll(upgradelist);
	}
	
	public CurrentView getCurrentView(){
		return myCurrentView;
	}
	
}
