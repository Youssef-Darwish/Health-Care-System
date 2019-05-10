package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.AppointmentRecord;
import model.records.PatientRecord;
import model.records.Record;
import model.users.Receptionist;

public class AddAppointmentController implements Initializable {

	@FXML // fx:id="addPatientId"
	private TextField addPatientId;

	@FXML // fx:id="addDoctorId"
	private TextField addDoctorId;

	@FXML // fx:id="addHour"
	private TextField addHour;

	@FXML // fx:id="addDate"
	private DatePicker addDate;

	@FXML // fx:id="addPatientButton"
	private Button addAppointmentButton;

	@FXML // fx:id="cancelPatientButton"
	private Button cancelAppointmentButton;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		warningLabel.setVisible(false);
		
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
		boolean check = addHour.getText().trim().isEmpty() || addDoctorId.getText().trim().isEmpty()
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

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);
		} else {
			String patientID = addPatientId.getText();

			String doctorId = addDoctorId.getText();
			String time = addHour.getText();


			try {
				LocalDate localDate = addDate.getValue();
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				Record record = new AppointmentRecord(Integer.valueOf(patientID), Integer.valueOf(doctorId), time,
						sqlDate);

				int result = ((Receptionist) LoginController.loggedIn).addAppointment(record);

				if (result != -1)
					System.out.println("inserted");

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
				show("/view/ReceptionistScene.fxml", event);

			} catch (NumberFormatException e) {

				System.out.println(e.getMessage());
				warningLabel.setText("invalid input");
				warningLabel.setVisible(true);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				warningLabel.setText("invalid input");
				warningLabel.setVisible(true);

			}

		}

	}

}
