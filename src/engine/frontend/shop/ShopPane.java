package engine.frontend.shop;


/**
 * @author HaydenBader
 */
import java.util.List;
import engine.backend.game_features.ShopItem;
import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;



public class ShopPane {
	private EngineView myEngineView;
	private ScrollPane myScrollPane = new ScrollPane();
	private VBox myVBox = new VBox();
	//private ListView<Map<String, String>> myListView = new ListView<Map<String, String>>()
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myScrollPane.setStyle("-fx-border-color: black;");
		//we can make this a superclass
		myScrollPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myScrollPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		myScrollPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myScrollPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		
		myVBox.minWidthProperty().bind(myScrollPane.widthProperty());
		myVBox.minHeightProperty().bind(myScrollPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
		myScrollPane.setContent(myVBox);
		
		//VBox.setVgrow(myVBox, Priority.ALWAYS);
		VBox.setVgrow(myScrollPane, Priority.ALWAYS);
		
	    ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		
		addShopObject(tester);
		return myScrollPane;
	}

	
	public void createShop(String image, String type, double cost){

	}

	public void addShopObject(ShopItem myShopItem){
		ShopView myShopView = new ShopView(myEngineView, myShopItem.getItemImage(), myShopItem.getItemName(), myShopItem.getItemValue(), 40, 40);			
		myVBox.getChildren().add(myShopView.getNode());
	}


	public void updateShop(List<ShopItem> myShopList){
		
	}

}
