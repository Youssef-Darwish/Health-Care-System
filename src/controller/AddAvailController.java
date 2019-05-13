package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import model.records.AvailablityRecord;
import model.records.PatientRecord;
import model.records.Record;
import model.users.Doctor;
import model.users.Receptionist;

public class AddAvailController implements Initializable {
	@FXML
	private Button addButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField addAvailTime;
	@FXML
	private DatePicker addAvailDate;
	@FXML
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		warningLabel.setVisible(false);
	}

	@FXML
	private void cancel(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		show("/view/DoctorScene.fxml", event);
	}

	private boolean validateInput() {
		// check that all fields are not empty for now
		boolean check = addAvailTime.getText().trim().isEmpty();
		return check;
	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene pageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(pageScene);
		stage.show();
	}

	@FXML
	public void addInDb(ActionEvent event) {

		if (validateInput()) {
			warningLabel.setVisible(true);
		} else {

			LocalDate localDate = addAvailDate.getValue();
			String time = addAvailTime.getText();
			int id = ((Doctor) LoginController.loggedIn).getId();
			// System.out.println(localDate + "\n");

			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			try {
				Record record = new AvailablityRecord(id, sqlDate, time);

				int result = ((Doctor) LoginController.loggedIn).addAvailableTime(record);

				if (result != -1)
					System.out.println("inserted");

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
				show("/view/DoctorScene.fxml", event);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				warningLabel.setText("Invalid input");
				warningLabel.setVisible(true);
			}

		}

	}

}
