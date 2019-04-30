package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class AddPatienCaseController implements Initializable {

	@FXML
	private Button newCaseOkButton;

	@FXML
	private Button newCaseCancelButton;

	@FXML
	private TextField diseaseField;

	@FXML
	private TextField medicationField;

	@FXML
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void ok(ActionEvent event) throws IOException {
		
		if (validateInput()) {
			warningLabel.setVisible(true);
			System.out.println("hna tyb?");
		} else {

			System.out.println("entered");
			try {
				int id = DoctorController.selectedRecord.getId();
				Record record = new PatientCaseRecord(diseaseField.getText(), id, medicationField.getText());

				int result = ((Doctor) LoginController.loggedIn).addPatientCase(record);
				System.out.println("called b2a");
				System.out.println("patientcase insertion result : " + String.valueOf(result));
				// check result val
				if (result == -1 || result == 0)
					throw new SQLException();
				
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				show("/view/PatientCase.fxml", event);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
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

	private boolean validateInput() {
		// check that all fields are not empty for now
		boolean check = diseaseField.getText().trim().isEmpty() || medicationField.getText().trim().isEmpty();
		return check;
	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene pageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(pageScene);
		stage.show();
	}

}
