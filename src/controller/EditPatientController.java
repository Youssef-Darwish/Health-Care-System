package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPatientController implements Initializable {


	@FXML // fx:id="editPatientId"
	private TextField editPatientId;

	@FXML // fx:id="editPatientName"
	private TextField editPatientName;

	@FXML // fx:id="editPatientTele"
	private TextField editPatientTele;

	@FXML // fx:id="editPatientDate"
	private DatePicker editPatientDate;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;
	
	@FXML //fx:id="editPatientButton"
	private Button editPatientButton;
	
	@FXML //fx:id="cancelPatientButton"
	private Button cancelPatientButton;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//get data from receptionist scene
		
	}

	@FXML
	public void editInDB(ActionEvent event) throws IOException {

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);

		} else {

			// validate input type
			try {

				// 
				
				show("/view/ReceptionistScene.fxml", event);
			} catch (NumberFormatException e) {
				warningLabel.setText("invalid input");
				warningLabel.setVisible(true);
			} catch (Exception e) {
				warningLabel.setText("Invalid Input");
				warningLabel.setVisible(true);

			}
		}
	}

	@FXML
	public void cancel(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/ReceptionistScene.fxml", event);
	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
	}

	private boolean validateInput() {

		// check that all fields are not empty for now

		boolean check = editPatientName.getText().trim().isEmpty() || editPatientId.getText().trim().isEmpty()
				||  editPatientTele.getText().trim().isEmpty();

		return check;
	}

}
