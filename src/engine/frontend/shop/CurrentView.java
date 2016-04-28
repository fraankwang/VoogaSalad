package engine.frontend.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

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

public class CurrentView implements Observer {

	private ShopPane myShopPane;

	private HBox myHBox;
	private ImageView myImageView;
	private String myImageName;

	private ListView<String> myStatsList;
	private ObservableList<String> stats;

	private Map<String, Boolean> showMap;

	public CurrentView(ShopPane sp) {
		myShopPane = sp;
		showMap = new HashMap<String, Boolean>();
	}

	public Node buildCurrentView(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		myHBox = new HBox();
		myShopPane.bindHeight(myHBox, heightBinding);
		myShopPane.bindWidth(myHBox, widthBinding);

		myImageView = new ImageView();
		myImageView.fitHeightProperty().bind(heightBinding);
		myImageView.setPreserveRatio(true);

		
		myStatsList = new ListView<String>();
		myStatsList.setOrientation(Orientation.HORIZONTAL);
		stats = FXCollections.observableArrayList();
		myStatsList.setItems(stats);

		myHBox.getChildren().addAll(myImageView, myStatsList);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		return myHBox;
	}

	@Override
	public void update(Observable o, Object arg) {
		IEntity entity = (IEntity) o;
		Map<String, String> statMap = entity.getStats().getStatsMap();
		for(String s: statMap.keySet()){
			showMap.put(s, new Boolean(true));
		}
		showMap.keySet().retainAll(statMap.keySet());
		
		stats.clear();
		for (String s : statMap.keySet()) {
			if (s.equals("Image") && !statMap.get("Image").equals(myImageName)) {
				myImageView.setImage(new Image(statMap.get("Image")));
				myImageName = statMap.get("Image");
			} else if (showMap.get(s)){
				stats.add(s + ": " + statMap.get(s));
			}
		}
	}
}
