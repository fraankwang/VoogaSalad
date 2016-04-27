package engine.frontend.shop;

import java.util.HashMap;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShopPane extends AbstractPane {
	private VBox myVBox;
	private ListView<ShopItem> myShopList;
	private CurrentView myCurrentView;
	private ListView<ShopItem> myUpgradeList;
	private Map<Integer, Map<String, String>> myStatsObjectMap;

	public ShopPane(EngineView ev) {
		super(ev);
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
		myVBox.setSpacing(getEngineView().loadDoubleResource("ShopSpacing"));
		
		ShopItem tester = new ShopItem("tempEntity2", "DrumpfVader.png", 10);
		
		myShopList = new ListView<ShopItem>();
		myUpgradeList = new ListView<ShopItem>();
		
		setupShopList();
		myCurrentView = new CurrentView(this);
		myVBox.getChildren().add(myCurrentView.buildCurrentView(new HashMap<String,String>(), myVBox.widthProperty(), myVBox.heightProperty().multiply(.1)));
		setupUpgradeList();
		myPane.getChildren().add(myVBox);
		
		addShopObject(tester);
		return myPane;
	}
	
/*	private void updateStatsObject(int id, StatsObject myStats, boolean hasChanged){
		if(hasChanged || !myStatsObjectMap.contains(id)){
 			// much simpler if stats object is just a map of strings to strings
 
			Map myPropertiesMap<String, String> = new HashMap<String, String>();
			for( String s: myStats.getKeySet() ){
				myPropertiesMap.put(s, myStats.getStatistic(s));
			}
			myStatsObjectMap.put(id, myPropertiesMap);
		}	
	}
*/
	
	/**
	 * instantiates the shop listView
	 */
	private void setupShopList(){
		myShopList = new ListView<ShopItem>();
		myShopList.setCellFactory(e -> {return new ShopCell(this);});
		bindWidth(myShopList, myVBox.widthProperty());
		bindHeight(myShopList, myVBox.heightProperty().multiply(.6));
		myVBox.getChildren().add(myShopList);
	}
	
	/**
	 * instantiates the upgrade listView
	 */
	private void setupUpgradeList(){
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
	
	/**
	 * Updates the most recently clicked entity to be displayed in the shopView.
	 * @param id - used as an index into private map that obtains a map of properties to values and forwards that to a currentView object 
	 */
	public void updateCurrentView(int id){
		myCurrentView.updateCurrentView(myStatsObjectMap.get(id));
	}
	
	public void addStatsObject(int id, Map<String, String> statsObject, boolean hasChanged){
		if(!myStatsObjectMap.containsKey(id) ||hasChanged){
			myStatsObjectMap.put(id, statsObject);
		}
	}
	
	public void removeStatsObject(int id, Map<String, String> statsObject){
		myStatsObjectMap.remove(id);
	}

	public void updateUpgrade(List<ShopItem> upgradelist) {
		myUpgradeList.getItems().clear();
		myUpgradeList.getItems().addAll(upgradelist);
	}
	
}
