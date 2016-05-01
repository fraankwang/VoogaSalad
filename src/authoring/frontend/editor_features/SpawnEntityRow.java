package authoring.frontend.editor_features;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * SpawnEntityRow acts as an extension of a Map of String key to value Control.
 * Because Multiple controls must be on one row (buttons and textfields), this
 * is a custom class that stores all this information.
 * 
 * @author Frank
 *
 */
public class SpawnEntityRow {

	private static final int FONT_SIZE = 15;
	private String myTag;
	private Label myPathID;
	private Label myName;
	private ImageView myImage;
	private Button myDecreaseButton;
	private TextField myNumber;
	private Button myIncreaseButton;
	private Label myWaveOrder;
	private TextField myRate;
	private Button myDeleteButton;

	public SpawnEntityRow(String tag, String pathID, String name, ImageView image, String number, String wave,
			String rate) {
		myTag = tag;

		myPathID = new Label(pathID);
		myPathID.setFont(new Font(FONT_SIZE));

		myName = new Label(name);
		myName.setFont(new Font(FONT_SIZE));
		myImage = image;
		myImage.setFitHeight(25);
		myImage.setFitWidth(25);
		myName.setGraphic(myImage);

		myNumber = new TextField(number);
		myNumber.setFont(new Font(FONT_SIZE));
		myNumber.setEditable(true);

		myWaveOrder = new Label(wave);
		myWaveOrder.setFont(new Font(FONT_SIZE));

		myRate = new TextField(rate);
		myRate.setFont(new Font(FONT_SIZE));
		myRate.setEditable(true);

		initializeButtons();
	}

	public SpawnEntityRow(String tag, String name, ImageView image, String wave, String pathID) {
		myTag = tag;

		myName = new Label(name);
		myName.setFont(new Font(FONT_SIZE));
		myName.toFront();
		myImage = image;
		myImage.setFitHeight(25);
		myImage.setFitWidth(25);
		myName.setGraphic(myImage);

		myWaveOrder = new Label(wave);
		myWaveOrder.setFont(new Font(FONT_SIZE));

		myPathID = new Label(pathID);
		myPathID.setFont(new Font(FONT_SIZE));
		myPathID.setTextAlignment(TextAlignment.CENTER);

		myNumber = new TextField("0");
		myNumber.setFont(new Font(FONT_SIZE));
		myNumber.setEditable(true);

		myRate = new TextField("0");
		myRate.setFont(new Font(FONT_SIZE));
		myRate.setEditable(true);
		initializeButtons();
	}

	/**
	 * Connects the increase/decrease buttons with myNumber.
	 */
	private void initializeButtons() {
		myIncreaseButton = new Button("+");

		myIncreaseButton.setOnAction(e -> {
			String currentNumber = myNumber.getText();
			if (currentNumber == null || currentNumber.equals("")) {
				myNumber.setText("1");
			} else {
				int currentInt = Integer.parseInt(currentNumber);
				myNumber.setText(Integer.toString(++currentInt));
			}
		});

		myDecreaseButton = new Button("-");
		myDecreaseButton.setOnAction(e -> {
			String currentNumber = myNumber.getText();
			if (currentNumber == null || currentNumber.equals("") || currentNumber.equals("0")) {
				myNumber.setText("0");
			} else {
				int currentInt = Integer.parseInt(currentNumber);
				myNumber.setText(Integer.toString(--currentInt));
			}
		});

		myDeleteButton = new Button("X");
	}

	public Label getMyPathID() {
		return myPathID;
	}

	public void setMyPathID(String pathID) {
		myPathID.setText(pathID);
	}

	public Label getMyName() {
		return myName;
	}

	public void setMyName(String name) {
		myName.setText(name);
	}

	public TextField getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String number) {
		myNumber.setText(number);
	}

	public Label getMyWaveOrder() {
		return myWaveOrder;
	}

	public void setMyWaveOrder(String waveOrder) {
		myWaveOrder.setText(waveOrder);
	}

	public TextField getMyRate() {
		return myRate;
	}

	public void setMyRate(String rate) {
		myRate.setText(rate);
	}

	public String getMyTag() {
		return myTag;
	}

	public Button getMyDecreaseButton() {
		return myDecreaseButton;
	}

	public Button getMyIncreaseButton() {
		return myIncreaseButton;
	}

	public Button getMyDeleteButton() {
		return myDeleteButton;
	}

	public ImageView getMyImage() {
		return myImage;
	}

}
