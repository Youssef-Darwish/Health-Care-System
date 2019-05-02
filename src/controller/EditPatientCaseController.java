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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.PatientCaseRecord;
import model.records.Record;
import model.users.Doctor;

public class EditPatientCaseController implements Initializable {

	@FXML
	private Button editPatientCaseButton;

	@FXML
	private Button cancelPatientCaseButton;

	@FXML
	private TextField diseaseField;

	@FXML
	private TextField medicationField;

	@FXML
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		diseaseField.setText(ShowCaseController.selectedRecord.getDisease());
		medicationField.setText(ShowCaseController.selectedRecord.getMedication());
	}

	@FXML
	private void edit(ActionEvent event) {
		if (validateInput()) {
			warningLabel.setVisible(true);

		} else {
			try {

				int id = ShowCaseController.selectedRecord.getPatientId();
				Record record = new PatientCaseRecord(diseaseField.getText(), id, medicationField.getText());
				System.out.println("patient id : " + String.valueOf(id));
				int result = ((Doctor) LoginController.loggedIn).editPatientCase("id",
						String.valueOf(ShowCaseController.selectedRecord.getCaseId()), record);
				if (result == -1) {
					throw new Exception();
				}

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				show("/view/PatientCase.fxml", event);

			} catch (Exception e) {
				warningLabel.setText("Invalid Input");
				warningLabel.setVisible(true);
			}
		}
	}

	@FXML
	private void cancel(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		show("/view/PatientCase.fxml", event);
	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene pageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(pageScene);
		stage.show();
	}

	private boolean validateInput() {

		// check that all fields are not empty for now
		boolean check = diseaseField.getText().trim().isEmpty() || medicationField.getText().trim().isEmpty();
		return check;
	}

}
