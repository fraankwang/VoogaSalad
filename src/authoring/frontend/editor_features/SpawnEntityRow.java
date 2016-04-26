package authoring.frontend.editor_features;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * 
 * @author Frank
 *
 */
public class SpawnEntityRow {
	
	private static final int FONT_SIZE = 10;
	private String myTag;
	private TextField myPathID;
	private TextField myName;
	private Button myDecreaseButton;
	private TextField myNumber;
	private Button myIncreaseButton;
	private TextField myWaveOrder;
	private TextField myRate;
	private Button myDeleteButton;
	
	public SpawnEntityRow(String tag, String pathID, String name, String number, String wave, String rate) {
		myTag = tag;
		myPathID = new TextField(pathID);
		myPathID.setFont(new Font(FONT_SIZE));
		myPathID.setEditable(false);
		myName = new TextField(name);
		myName.setFont(new Font(FONT_SIZE));
		myName.setEditable(false);
		myNumber = new TextField(number);
		myNumber.setFont(new Font(FONT_SIZE));
		myWaveOrder = new TextField(wave);
		myWaveOrder.setFont(new Font(FONT_SIZE));
		myWaveOrder.setEditable(false);
		myRate= new TextField(rate);
		myRate.setFont(new Font(FONT_SIZE));
		initializeButtons();
	}

	public SpawnEntityRow(String tag, String name, String wave, String pathID) {
		myTag = tag;
		myName = new TextField(name);
		myName.setFont(new Font(FONT_SIZE));
		myName.setEditable(false);
		myWaveOrder = new TextField(wave);
		myWaveOrder.setFont(new Font(FONT_SIZE));
		myWaveOrder.setEditable(false);
		myPathID = new TextField(pathID);
		myPathID.setFont(new Font(FONT_SIZE));
		myPathID.setEditable(false);
		myNumber = new TextField("0");
		myNumber.setFont(new Font(FONT_SIZE));
		myRate= new TextField("0");
		myRate.setFont(new Font(FONT_SIZE));
		initializeButtons();
	}

	
	private void initializeButtons() {
		myIncreaseButton = new Button("+");
		
		myIncreaseButton.setOnAction(e -> {
			String currentNumber = myNumber.getText();
			if (currentNumber == null || currentNumber.equals("")) {
				myNumber.setText("1");
			}
			else {
				int currentInt = Integer.parseInt(currentNumber);
				myNumber.setText(Integer.toString(++currentInt));
			}
		});
		
		myDecreaseButton = new Button("-");
		myDecreaseButton.setOnAction(e -> {
			String currentNumber = myNumber.getText();
			if (currentNumber == null || currentNumber.equals("") || currentNumber.equals("0")) {
				myNumber.setText("0");
			}
			else {
				int currentInt = Integer.parseInt(currentNumber);
				myNumber.setText(Integer.toString(--currentInt));
			}
		});
		
		myDeleteButton = new Button("X");
	}

	public TextField getMyPathID() {
		return myPathID;
	}

	public void setMyPathID(String pathID) {
		myPathID.setText(pathID);
	}

	public TextField getMyName() {
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
	
	public TextField getMyWaveOrder() {
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

	public Button getMyDecreaseButton() {
		return myDecreaseButton;
	}

	public Button getMyIncreaseButton() {
		return myIncreaseButton;
	}

	public Button getMyDeleteButton() {
		return myDeleteButton;
	}
	
}
