package engine.frontend.shop;

import java.util.ResourceBundle;

import engine.backend.game_features.ShopItem;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ShopCell extends ListCell<ShopItem> {

	private ShopPane myShopPane;
	private ShopItem myItem;
	private String itemImage;
	private double myCost;
	
	private HBox myHBox;
	private ImageView myImageView;
	private Text myName;
	private Text myCostText;
	public static final String DEFAULT_RESOURCE = "engine/frontend/resources/shop_cell";
	private ResourceBundle myResources;

	public ShopCell(ShopPane sp) {
		super();
		myShopPane = sp;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		
		myHBox = new HBox();
		
		itemImage = new String();
		myImageView = new ImageView();
		myImageView.setPreserveRatio(true);
		
		myName = new Text();
		myCostText = new Text();
		
		myHBox.getChildren().addAll(myImageView, myName, myCostText);
		myHBox.setAlignment(Pos.CENTER_LEFT);
	}

	@Override
	public void updateItem(ShopItem item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			myItem = item;
			myImageView.fitHeightProperty().bind(super.getListView().heightProperty().divide(5));
			if(!itemImage.equals(item.getItemImage())){
				itemImage = item.getItemImage();
				myImageView.setImage(new Image(itemImage));
			}
			
			if(!myName.getText().equals(item.getItemName())){
				myName.setText(item.getItemName());
			}
			
			if(myCost != item.getItemValue()){
				myCost = item.getItemValue();
				myCostText.setText(myResources.getString("ShopCostPrompt") + myCost);
			}
			
//			if (myItem.isCanBuy()) {
			if(true){
				setOnDragDetected(e -> selectTower(e));
				setHBoxOpacity(getDoubleResource("YesOpacity"));
			} else {
				setHBoxOpacity(getDoubleResource("NoOpacity"));
			}

			setGraphic(myHBox);
		}
	}

	private void selectTower(MouseEvent e) {

		myShopPane.getEngineView().getDummyCursor().changePic(myImageView.getImage());
		myShopPane.getEngineView().getStage().getScene().setCursor(Cursor.NONE);
		Dragboard db = this.startDragAndDrop(TransferMode.ANY);
		/* Put a string on a dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(myItem.getItemName());
		db.setContent(content);
		e.consume();
	}

	private double getDoubleResource(String myString) {
		return Double.parseDouble(myResources.getString(myString));
	}

	private void setHBoxOpacity(double value) {
		myImageView.setOpacity(value);
		myName.setOpacity(value);
		myCostText.setOpacity(value);
	}
}
