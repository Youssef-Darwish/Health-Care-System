package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPatientController implements Initializable {

	@FXML // fx:id="addPatientName"
	private TextField addPatientName;

	@FXML // fx:id="addPatientId"
	private TextField addPatientId;

	@FXML // fx:id="addPatientTele"
	private TextField addPatientTele;

	@FXML
	ToggleGroup grp;

	@FXML // fx:id="maleButton"
	private RadioButton maleButton;

	@FXML // fx:id="addPatientDate"
	private DatePicker addPatientDate;

	@FXML // fx:id="femaleButton"
	private RadioButton femaleButton;

	@FXML // fx:id="addPatientButton"
	private Button addPatientButton;

	@FXML // fx:id="cancelPatientButton"
	private Button cancelPatientButton;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene patientPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(patientPageScene);

		stage.show();
	}

	private boolean validateInput() {

		// check that all fields are not empty for now
		boolean check = addPatientName.getText().trim().isEmpty() || addPatientTele.getText().trim().isEmpty()
				|| addPatientId.getText().trim().isEmpty();

		return check;
	}

	public void cancel(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/ReceptionistScene.fxml", event);
	}

	@FXML
	public void addInDB(ActionEvent event) throws IOException {

	}

}
