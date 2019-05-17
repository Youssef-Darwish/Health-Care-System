package controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

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
import model.records.AppointmentRecord;
import model.records.Record;
import model.users.Receptionist;

public class AddAppointmentController implements Initializable {

	@FXML // fx:id="addPatientId"
	private TextField addPatientId;

	@FXML // fx:id="addDoctorId"
	private TextField addDoctorId;

	@FXML // fx:id="addHour"
	private Label addHour;

	@FXML // fx:id="addDate"
	private Label addDate;

	@FXML // fx:id="addPatientButton"
	private Button addAppointmentButton;

	@FXML // fx:id="cancelPatientButton"
	private Button cancelAppointmentButton;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@FXML // fx:id="checkAvailablityButton"
	private Button checkAvailablityButton;

	public static String doctorID = "";

	public static String patientID = "";

	public static String appointmentDate = "";

	public static String appointmentTime = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addDate.setText(appointmentDate);
		addDoctorId.setText(doctorID);
		addHour.setText(appointmentTime);
		addPatientId.setText(patientID);

		warningLabel.setVisible(false);
		ArrayList<String> patientNames = null;
		ArrayList<String> doctorNames = null;

		try {
			patientNames = ((Receptionist) LoginController.loggedIn).getPatientsNames();
			doctorNames = ((Receptionist) LoginController.loggedIn).getDoctorNames();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextFields.bindAutoCompletion(addPatientId, patientNames);
		TextFields.bindAutoCompletion(addDoctorId, doctorNames);
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
		doctorID = "";
		patientID = "";
		appointmentDate = "";
		appointmentTime = "";
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/ReceptionistScene.fxml", event);
	}

	@FXML
	public void openAvailabilityDates(ActionEvent event) throws IOException {

		doctorID = addDoctorId.getText();
		patientID = addPatientId.getText();

		String[] splitDocId = doctorID.split(" ");
		String[] splitPatId = patientID.split(" ");
		if (splitDocId.length < 2 || splitPatId.length < 2) {
			warningLabel.setVisible(true);
		} else {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
			show("/view/Availability.fxml", event);

		}

	}

	@FXML
	public void addInDB(ActionEvent event) throws IOException {

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);
		} else {
			String patientID = addPatientId.getText();
			String doctorId = addDoctorId.getText();

			String[] splittedPatient = patientID.split("id: ");
			splittedPatient[1] = splittedPatient[1].substring(0, splittedPatient[1].length() - 1);

			String[] splittedDoctor = doctorId.split("id: ");
			splittedDoctor[1] = splittedDoctor[1].substring(0, splittedDoctor[1].length() - 1);

			String time = addHour.getText();

			try {
				// LocalDate localDate = addDate.getValue();
				// Date date =
				// Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(addDate.getText());
				java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
				Record record = new AppointmentRecord(Integer.valueOf(splittedPatient[1]),
						Integer.valueOf(splittedDoctor[1]), time, sqlDate);

				int result = ((Receptionist) LoginController.loggedIn).addAppointment(record);

				if (result != -1) {
					System.out.println("inserted");

					int res2 = ((Receptionist) LoginController.loggedIn).deleteAvailability("DATE", sqlDate.toString(),
							"HOUR", time);
					if (res2 != -1)
						System.out.println("avail deleted");
				}

				doctorID = "";
				patientID = "";
				appointmentDate = "";
				appointmentTime = "";

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
