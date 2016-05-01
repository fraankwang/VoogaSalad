/**
 * @author austinwu
 */
package engine.frontend.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import engine.backend.components.CollisionComponent;
import engine.backend.components.DisplayComponent;
import engine.backend.components.FiringComponent;
import engine.backend.components.HealthComponent;
import engine.backend.components.IComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.MultiDirectionalFiringComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.PurchaseComponent;
import engine.backend.components.SizeComponent;
import engine.backend.components.SpawnerComponent;
import engine.backend.components.TrackingMovementComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.frontend.overall.ResourceUser;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CurrentView extends ResourceUser implements Observer {

	public static final String RESOURCE_NAME = "statlabels";

	private ShopPane myShopPane;

	private HBox myHBox;
	private ImageView myImageView;
	private String myImageName;

	private ListView<String> myStatsList;
	private ObservableList<String> stats;

	private Map<String, Boolean> showMap;

	private boolean debug;
	
	/**
	 * Instantiates current view node - displays most recently clicked entity and its attributes
	 * @param sp - shop pane - this class's parent node
	 */
	public CurrentView(ShopPane sp) {
		super(RESOURCE_NAME);
		myShopPane = sp;
		showMap = new HashMap<String, Boolean>();
		if (!debug) {
			addDefaultShows(showMap);
		}
	}
	
	/**
	 * Setup default values shown in view
	 * @param showMap - map of string values to booleans of whether they should be shown
	 */
	private void addDefaultShows(Map<String, Boolean> showMap){
		String[] hides = loadStringArrayResource("DefaultHides", ",");
		String[] shows = loadStringArrayResource("DefaultShows", ",");
		for (String s : hides) {
			showMap.put(s, false);
		}
		for (String s : shows) {
			showMap.put(s, true);
		}
	}

	/**
	 * Instantiates current view node - adds bindings and attaches child nodes
	 * @param widthBinding - DoubleExpression binding value connecting current node to parent node's width property
	 * @param heightBinding - DoubleExpression binding value connecting current node to parent node's height property
	 * @return - returns Node to be added to parent pane
	 */
	public Node buildCurrentView(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		myHBox = new HBox();
		myShopPane.bindHeight(myHBox, heightBinding);
		myShopPane.bindWidth(myHBox, widthBinding);

		myImageView = new ImageView();
		myImageView.fitHeightProperty().bind(myHBox.heightProperty());
		myImageView.setPreserveRatio(true);

		myStatsList = new ListView<String>();
		myStatsList.setOrientation(Orientation.HORIZONTAL);
		stats = FXCollections.observableArrayList();
		myStatsList.setItems(stats);
		myStatsList.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(myStatsList, Priority.ALWAYS);
		myShopPane.bindHeight(myStatsList, myHBox.heightProperty());

		myHBox.getChildren().addAll(myImageView, myStatsList);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		return myHBox;
	}


	/**
	 * @Override
	 * 
	 * Updates the entries displayed in the CurrentView's display
	 * @param o  - observed object
	 * @param arg - argument to be observed
	 */
	public void update(Observable o, Object arg) {
		IEntity entity = (IEntity) o;
		Map<String, String> statMap = entity.getStats().getStatsMap();
		stats.clear();
		for (String s : statMap.keySet()) {
			if (!showMap.containsKey(s)) {
				showMap.put(s, true);
			}
			if (s.equals("Image") && !statMap.get("Image").equals(myImageName)) {
				myImageView.setImage(new Image(statMap.get("Image")));
				myImageName = statMap.get("Image");
			} else if (showMap.get(s) || debug) {
				stats.add(loadStringResource(s) + ": " + statMap.get(s));
			}
		}
	}

	/**
	 * Enables debug mode
	 * @param b - boolean of whether debug should be completed
	 */
	
	public void setDebug(boolean b) {
		debug = b;
	}
}
