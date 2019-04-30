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
import model.users.Receptionist;

public class EditAppointmentController implements Initializable {

	@FXML // fx:id="editPatientId"
	private TextField editPatientId;

	@FXML // fx:id="editDoctortId"
	private TextField editDoctortId;

	@FXML // fx:id="editHour"
	private TextField editHour;

	@FXML // fx:id="editDate"
	private DatePicker editDate;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@FXML // fx:id="editAppointmentButton"
	private Button editAppointmentButton;

	@FXML // fx:id="cancelAppointment"
	private Button cancelAppointment;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("in init edit");
		// get data from receptionist scene
		System.out.println(ReceptionistController.selectedAppRecord == null);
		editPatientId.setText(String.valueOf(ReceptionistController.selectedAppRecord.getPatientId()));
		editDoctortId.setText(String.valueOf(ReceptionistController.selectedAppRecord.getDoctorId()));
		editHour.setText(String.valueOf(ReceptionistController.selectedAppRecord.getHour()));
		java.util.Date utilDate = new java.util.Date(
				ReceptionistController.selectedAppRecord.getAppointmentDate().getTime());
		LocalDate date = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		editDate.setValue(date);

	}

	@FXML
	public void editInDB(ActionEvent event) throws IOException {

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);

		} else {

			// validate input type
			try {

				LocalDate localDate = editDate.getValue();

				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				AppointmentRecord record = new AppointmentRecord(Integer.valueOf(editPatientId.getText()),
						Integer.valueOf(editDoctortId.getText()), editHour.getText(), sqlDate);

				int result = ((Receptionist) LoginController.loggedIn).editAppointment("ID",
						String.valueOf(ReceptionistController.selectedAppRecord.getAppointmentId()), record);
				if (result == -1) {
					throw new Exception();
				}

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				show("/view/ReceptionistScene.fxml", event);

			} catch (NumberFormatException e) {
				warningLabel.setText("invalid input");
				warningLabel.setVisible(true);
			} catch (Exception e) {
//				warningLabel.setText("Invalid Input");
//				warningLabel.setVisible(true);
				System.out.println(e.getMessage());

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

		boolean check = editDoctortId.getText().trim().isEmpty() || editPatientId.getText().trim().isEmpty()
				|| editHour.getText().trim().isEmpty();

		return check;
	}

}
