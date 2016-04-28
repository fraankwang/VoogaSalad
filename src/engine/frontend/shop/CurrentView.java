package engine.frontend.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import engine.backend.entities.IEntity;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CurrentView implements Observer {

	private ShopPane myShopPane;

	private HBox myHBox;
	private ImageView myImageView;
	private String myImageName;
	
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
		myHBox.getChildren().add(myImageView);
		myImageView.fitHeightProperty().bind(heightBinding);
		myImageView.setPreserveRatio(true);
		
		
		myHBox.setAlignment(Pos.CENTER_LEFT);
		return myHBox;
	}

	@Override
	public void update(Observable o, Object arg) {
		IEntity entity = (IEntity) o;
		Map<String, String> statMap = entity.getStats().getMap();
		showMap.keySet().retainAll(statMap.keySet());
		
		for (String s : statMap.keySet()) {
			if (s.equals("Image") && !statMap.get("Image").equals(myImageName)) {
				myImageView.setImage(new Image(statMap.get("Image")));
				myImageName = statMap.get("Image");
			}
			
			for (String s : myStats.keySet()) {
				if (!s.equals("image")) {
					Text newText = new Text(myStats.get(s));
					myHBox.getChildren().add(newText);
					myComponents.put(s, newText);
				}
			}
			
			
		}

	}
	
	private void updateShowMap(Map<String, String> statMap){
		
		
	}
}
