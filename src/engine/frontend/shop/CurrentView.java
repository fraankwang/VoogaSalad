package engine.frontend.shop;

import engine.backend.game_features.ShopItem;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CurrentView {
	
	private ShopPane myShopPane;
	
	private ImageView myImageView;
	private Text nameText;
	private Text costText;
	private HBox myHBox; 
	
	public CurrentView(ShopPane sp){
		myShopPane = sp;		
	}
	
	public Node buildCurrentView(ShopItem sp, DoubleExpression widthBinding, DoubleExpression heightBinding){
		myHBox = new HBox();
		myShopPane.bindHeight(myHBox, heightBinding);
		myShopPane.bindWidth(myHBox, widthBinding);
		
		myImageView = new ImageView(new Image(sp.getItemImage()));
		myImageView.fitHeightProperty().bind(heightBinding);
		myImageView.setPreserveRatio(true);
		nameText = new Text(sp.getItemName());	
		costText = new Text(Double.toString(sp.getItemValue()));
		
		myHBox.getChildren().addAll(myImageView, nameText, costText);
		return myHBox;
	}

	public void updateCurrentView(Image image, String type, double cost){
		myImageView = new ImageView(image);
		nameText.setText(type);
		costText.setText(Double.toString(cost));
	}
	
	public void updateCurrentView(ShopItem sp){
		myImageView = new ImageView(new Image(sp.getItemImage()));
		nameText.setText(sp.getItemName());
		costText.setText(Double.toString(sp.getItemValue()));
	}
	
	public Node getNode(){
		return myHBox;
	}
}
