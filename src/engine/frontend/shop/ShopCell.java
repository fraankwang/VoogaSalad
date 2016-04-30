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

	private ImageView myImageView;
	private Text myType;
	private Text myCost;
	public static final String DEFAULT_RESOURCE = "engine/frontend/resources/shop_cell";
	private ResourceBundle myResources;

	public ShopCell(ShopPane sp) {
		myShopPane = sp;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		
		itemImage = new String();
		myImageView = new ImageView();
		myType = new Text();
		myCost = new Text();
	}

	@Override
	public void updateItem(ShopItem item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			myItem = item;
			HBox hbox = new HBox();
			hbox.setOnKeyPressed(null);
		
			if(itemImage == null){ 
				myImageView = new ImageView(new Image(item.getItemImage()));
				itemImage = item.getItemImage();
			} else {
				if(itemImage != item.getItemImage()){
					itemImage = item.getItemImage();
				}
			}
			myImageView.fitHeightProperty().bind(super.getListView().heightProperty().multiply(getDoubleResource("TowerCellHeight")));
			myImageView.setPreserveRatio(true);
			
			if(myType == null){
				myType = new Text(item.getItemName());
			} else {
				if(myType.getText().equals(item.getItemName())){
					myType.setText(item.getItemName());
				}
			}
			
			if(myCost == null){
				myCost = new Text(myResources.getString("ShopCostPrompt")+ String.valueOf(item.getItemValue()));
			} else {
				String dummyString = myType.getText().substring(myResources.getString("ShopCostPrompt").length());
				if(dummyString.equals(String.valueOf(item.getItemValue()))){
					myCost.setText(myResources.getString("ShopCostPrompt")+ String.valueOf(item.getItemValue()));
				}
			}
						
//			if (myItem.isCanBuy()) {
			if(true){
				setOnDragDetected(e -> selectTower(e));
				setHBoxOpacity(getDoubleResource("YesOpacity"));
			} else {
				setHBoxOpacity(getDoubleResource("NoOpacity"));
				setOnDragDetected(null);
			}

			hbox.getChildren().addAll(myImageView, myType, myCost);
			hbox.setAlignment(Pos.CENTER_LEFT);
			setGraphic(hbox);
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
		myType.setOpacity(value);
		myCost.setOpacity(value);
	}
}
