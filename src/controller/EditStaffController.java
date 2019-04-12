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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;

public class EditStaffController implements Initializable {

	@FXML // fx:id="editStaffRole"
	private ComboBox<String> editStaffRole;

	@FXML // fx:id="editStaffName"
	private TextField editStaffName;

	@FXML // fx:id="editStaffSalary"
	private TextField editStaffSalary;

	@FXML // fx:id="editStaffTele"
	private TextField editStaffTele;

	@FXML // fx:id="editStaffPass"
	private TextField editStaffPass;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> searchAttributes = FXCollections.observableArrayList("Admin", "Manager", "Doctor",
				"Receptionist");
		editStaffRole.setItems(searchAttributes);

		editStaffName.setText(AdminController.selectedRecord.getName());
		editStaffRole.setValue(String.valueOf(AdminController.selectedRecord.getRole()));
		editStaffSalary.setText(String.valueOf(AdminController.selectedRecord.getSalary()));
		editStaffTele.setText(AdminController.selectedRecord.getTelephone());

	}

	@FXML
	public void editInDB(ActionEvent event) throws IOException {

		if (validateInput()) {
			warningLabel.setText("Enter all required fields");
			warningLabel.setVisible(true);

		} else {

			// validate input type
			try {
				StaffRecord record = new StaffRecord(editStaffName.getText(), editStaffRole.getValue(),
						editStaffTele.getText(), Double.parseDouble(editStaffSalary.getText()),
						editStaffPass.getText());

				if (Double.parseDouble(editStaffSalary.getText()) < 0)
					throw new NumberFormatException();
				
				int result = ((Admin) LoginController.loggedIn).editStaffMember("id",
						String.valueOf(AdminController.selectedRecord.getId()), record);
				if (result == -1) {
					throw new Exception();
				}

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				show("/view/AdminScene.fxml", event);
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
		show("/view/AdminScene.fxml", event);
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

		boolean check = editStaffName.getText().trim().isEmpty() || editStaffSalary.getText().trim().isEmpty()
				|| editStaffPass.getText().trim().isEmpty() || editStaffTele.getText().trim().isEmpty();

		return check;
	}

}
