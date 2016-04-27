package engine.frontend.shop;

import java.util.List;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.backend.game_features.ShopItem;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CurrentView {
	
	private ShopPane myShopPane;
	private HBox myHBox; 
	private ImageView myImageView;
	private String myImageLoc;
	private Map<String, Text> myComponents;
	
	public CurrentView(ShopPane sp){
		myShopPane = sp;		
	}
	
	public Node buildCurrentView(Map<String, String> myStats, DoubleExpression widthBinding, DoubleExpression heightBinding){
		myHBox = new HBox();
		myShopPane.bindHeight(myHBox, heightBinding);
		myShopPane.bindWidth(myHBox, widthBinding);
		
		
		myHBox.getChildren().clear();
		if(myStats.containsKey("image")){
			myImageView = new ImageView(new Image(myStats.get("image")));
			myHBox.getChildren().add(myImageView);
			myImageView.fitHeightProperty().bind(heightBinding);
			myImageView.setPreserveRatio(true);
			myImageLoc = myStats.get("image");
		}
		for( String s : myStats.keySet()){
			if( !s.equals("image")){
				Text newText = new Text(myStats.get(s));
				myHBox.getChildren().add(newText);
				myComponents.put(s, newText);				
			}
		}

		
		myHBox.setAlignment(Pos.CENTER_LEFT);
		return myHBox;
	}

	
	public void updateCurrentView(Map<String, String> myStats){
		
		if(myStats.containsKey("image")){
			if(myImageLoc != myStats.get("image")){
				myImageView.setImage(new Image(myStats.get("image")));
				myHBox.getChildren().add(myImageView);
			}
		}
		for( String s : myStats.keySet()){
			if( !s.equals("image")){
				if(!myComponents.containsKey(s) || !myComponents.get(s).equals(myStats.get(s))){
					Text newText = new Text(myStats.get(s));
					myHBox.getChildren().add(newText);
					myComponents.put(s, newText);
				}else{
					myComponents.get(s).setText(myStats.get(s));
				}
			}
		}
	}
	
	public Node getNode(){
		return myHBox;
	}
}
