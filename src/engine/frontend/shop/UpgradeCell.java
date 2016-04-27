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

public class UpgradeCell extends ListCell<ShopItem> {

	private ShopPane myShopPane;
	private ShopItem myItem;
	private Image myImage;
	public static final String DEFAULT_RESOURCE = "engine/frontend/shop/shop_cell";
	private ResourceBundle myResources;

	public UpgradeCell(ShopPane sp) {
		myShopPane = sp;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
	}

	@Override
	public void updateItem(ShopItem item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			myItem = item;
			HBox hbox = new HBox();
			myShopPane.bindHeight(hbox, super.getListView().heightProperty().multiply(.1));
			myShopPane.bindHeight(hbox, super.getListView().widthProperty());
			
			myImage = new Image(item.getItemImage());
			ImageView myImageView = new ImageView(myImage);
			myImageView.fitHeightProperty().bind(hbox.heightProperty());
			myImageView.setPreserveRatio(true);
			Text myType = new Text(item.getItemName());
			Text myCost = new Text(myResources.getString("ShopCostPrompt") + String.valueOf(item.getItemValue()));
			
			if (item.isCanBuy()) {
				setOnDragDetected(e -> selectTower(e));
			} else {
				setOnDragDetected(null);
			}

			hbox.getChildren().addAll(myImageView, myType, myCost);
			hbox.setAlignment(Pos.CENTER_LEFT);
			setGraphic(hbox);
		}
	}

	private void selectTower(MouseEvent e) {
		myShopPane.getEngineView().getDummyCursor().changePic(myImage);
		myShopPane.getEngineView().getStage().getScene().setCursor(Cursor.NONE);
		Dragboard db = this.startDragAndDrop(TransferMode.ANY);
		/* Put a string on a dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(myItem.getItemName());
		db.setContent(content);
		e.consume();
	}
}
