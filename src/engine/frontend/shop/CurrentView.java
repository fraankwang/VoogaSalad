package engine.frontend.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
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

public class CurrentView implements Observer {

	public static final String DEFAULT_RESOURCE = "engine/frontend/shop/statlabels";
	private ResourceBundle myResources;
	
	private ShopPane myShopPane;

	private HBox myHBox;
	private ImageView myImageView;
	private String myImageName;

	private ListView<String> myStatsList;
	private ObservableList<String> stats;

	private Map<String, Boolean> showMap;

	private boolean debug = true;
	
	public CurrentView(ShopPane sp) {
		myShopPane = sp;
		showMap = new HashMap<String, Boolean>(); 
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		if(!debug){
			addDefaultShows(showMap);
		}
	}
	
	private void addDefaultShows(Map<String, Boolean> showMap){
		String[] hides = myResources.getString("DefaultHides").split(",");
		String[] shows = myResources.getString("DefaultShows").split(",");
		for(String s: hides){
			showMap.put(s, false);
		}
		for(String s: shows){
			showMap.put(s, true);
		}
	}

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

	@Override
	public void update(Observable o, Object arg) {
		IEntity entity = (IEntity) o;
		Map<String, String> statMap = entity.getStats().getStatsMap();
		for(String s: statMap.keySet()){
			if(!showMap.containsKey(s))
				showMap.put(s, true);
		}
		stats.clear();
		for (String s : statMap.keySet()) {
			if (s.equals("Image") && !statMap.get("Image").equals(myImageName)) {
				myImageView.setImage(new Image(statMap.get("Image")));
				myImageName = statMap.get("Image");
			} else if (showMap.get(s)){
				stats.add(myResources.getString(s) + ": " + statMap.get(s));
			}
		}
	}
}
