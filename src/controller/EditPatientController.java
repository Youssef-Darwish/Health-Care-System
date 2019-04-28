package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import model.records.PatientRecord;
import model.records.StaffRecord;
import model.users.Admin;
import model.users.Receptionist;

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
		editPatientName.setText(ReceptionistController.selectedRecord.getName());
		editPatientId.setText(String.valueOf(ReceptionistController.selectedRecord.getId()));
		editPatientTele.setText(String.valueOf(ReceptionistController.selectedRecord.getTelephone()));
        java.util.Date utilDate = new java.util.Date(ReceptionistController.selectedRecord.getRegistrationDate().getTime());
		LocalDate date = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


		editPatientDate.setValue(date);
		
	}

	@FXML
	public void editInDB(ActionEvent event) throws IOException {

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);

		} else {

			// validate input type
			try {
				LocalDate localDate = editPatientDate.getValue();
//				System.out.println(localDate + "\n");
					
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				System.out.println(ReceptionistController.selectedRecord.getGender());
				PatientRecord record = new PatientRecord(Integer.valueOf(editPatientId.getText()), editPatientName.getText(),
						editPatientTele.getText(),ReceptionistController.selectedRecord.getGender(), sqlDate);

				
				int result = ((Receptionist) LoginController.loggedIn).editPatient("id",
						String.valueOf(ReceptionistController.selectedRecord.getId()), record);
				if (result == -1) {
					throw new Exception();
				}

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				show("/view/ReceptionistScene.fxml", event);
			} catch (Exception e) {
				 System.out.println(e.getMessage());
//				warningLabel.setVisible(true);

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
