package engine.frontend.shop;


/**
 * @author HaydenBader
 */
import java.util.List;
import engine.backend.game_features.ShopItem;
import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;



public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane = new Pane();
	private VBox myVBox = new VBox();
	//private ListView<Map<String, String>> myListView = new ListView<Map<String, String>>()
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){

		myPane.setStyle("-fx-border-color: black;");
		//we can make this a superclass
		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("ShopHeight")));
		
		myVBox.minWidthProperty().bind(myPane.widthProperty());
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.setSpacing(myEngineView.loadDoubleResource("ShopSpacing"));
		
	    
	    ShopItem tester = new ShopItem("Trumpf", "DrumpfVader.png", 10);
		
		addShopObject(tester);	
		myPane.getChildren().add(myVBox);
		
		return myPane;
	}

	
	public void createShop(String image, String type, double cost){

	}

	public void addShopObject(ShopItem myShopItem){
		ShopView myShopView = new ShopView(myEngineView, myShopItem.getItemImage(), myShopItem.getItemName(), myShopItem.getValue(), 40, 40);	
		myVBox.getChildren().add(myShopView.getNode());
	}


	public void updateShop(List<ShopItem> myShopList){
		
	}
}
