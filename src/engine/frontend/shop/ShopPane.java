package engine.frontend.shop;

/**
 * @author HaydenBader
 */
import java.util.List;

import engine.backend.game_features.ShopItem;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ShopPane extends AbstractPane {
	public static final String RESOURCE_NAME = "shop";
	
	private VBox myVBox;
	private ListView<ShopItem> shopListView;
	private CurrentView myCurrentView;
	private ListView<ShopItem> upgradeListView;

	private final ObservableList<ShopItem> myShopItems = FXCollections.observableArrayList();
	private final ObservableList<ShopItem> myUpgradeItems = FXCollections.observableArrayList();
	
	private ShopPane raghav;
	
	public ShopPane(EngineView ev) {
		super(ev, RESOURCE_NAME);
		raghav = this;
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
		shopListView = new ListView<ShopItem>(myShopItems);
//		shopListView.setCellFactory(e -> {return new ShopCell(this);});
		shopListView.setCellFactory(new Callback<ListView<ShopItem>, 
	            ListCell<ShopItem>>() {
	                @Override 
	                public ListCell<ShopItem> call(ListView<ShopItem> list) {
	                    return new ShopCell(raghav);
	                }
	            }
	        );
		
		bindWidth(shopListView, myVBox.widthProperty());
		bindHeight(shopListView, myVBox.heightProperty().multiply(.6));
		myVBox.getChildren().add(shopListView);
	}
	
	/**
	 * instantiates the upgrade listView
	 */
	private void setupUpgradeList(){
		upgradeListView = new ListView<ShopItem>(myUpgradeItems);
		upgradeListView.setCellFactory(e -> {return new UpgradeCell(this);});
		bindWidth(upgradeListView, myVBox.widthProperty());
		bindHeight(upgradeListView, myVBox.heightProperty().multiply(.3));
		myVBox.getChildren().add(upgradeListView);
	}
	
	/**
	 * Updates the shop given a list of shopItems
	 * @param list - list of shopItems - each shopItem contains shon name, image, cost, and if it's interactable
	 */
	public void updateShop(List<ShopItem> shoplist) {
		myShopItems.clear();
		myShopItems.addAll(shoplist);
	}
	
	public void updateUpgrade(List<ShopItem> upgradelist) {
		myUpgradeItems.clear();
		myUpgradeItems.addAll(upgradelist);
	}
	
	public CurrentView getCurrentView(){
		return myCurrentView;
	}
	
}
