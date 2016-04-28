package engine.frontend.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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

public class CurrentView extends ResourceUser implements Observer{

	public static final String RESOURCE_NAME = "statlabels";
	
	private ShopPane myShopPane;

	private HBox myHBox;
	private ImageView myImageView;
	private String myImageName;

	private ListView<String> myStatsList;
	private ObservableList<String> stats;

	private Map<String, Boolean> showMap;

	private boolean debug = true;
	
	public CurrentView(ShopPane sp) {
		super(RESOURCE_NAME);
		myShopPane = sp;
		showMap = new HashMap<String, Boolean>(); 
		if(!debug){
			addDefaultShows(showMap);
		}
	}
	
	private void addDefaultShows(Map<String, Boolean> showMap){
		String[] hides = loadStringArrayResource("DefaultHides", ",");
		String[] shows = loadStringArrayResource("DefaultShows", ",");
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
				stats.add(loadStringResource(s) + ": " + statMap.get(s));
			}
		}
	}
}
