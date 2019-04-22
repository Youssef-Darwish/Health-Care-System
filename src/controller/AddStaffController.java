package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.SQLError;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;

public class AddStaffController implements Initializable {

	@FXML // fx:id="addStaffRole"
	private ComboBox<String> addStaffRole;

	@FXML // fx:id="addStaffName"
	private TextField addStaffName;

	@FXML // fx:id="addStaffSalary"
	private TextField addStaffSalary;

	@FXML // fx:id="addStaffTele"
	private TextField addStaffTele;

	@FXML // fx:id="addStaffPass"
	private PasswordField addStaffPass;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> searchAttributes = FXCollections.observableArrayList("Admin", "Manager", "Doctor",
				"Receptionist");
		addStaffRole.setItems(searchAttributes);
		addStaffRole.setValue("Receptionist");

	}

	@FXML
	public void addInDB(ActionEvent event) throws IOException {
		System.out.println("in add");

		if (validateInput()) {
			warningLabel.setVisible(true);

		} else {
			try {
				Record record = new StaffRecord(addStaffName.getText(), addStaffRole.getValue(), addStaffTele.getText(),
						Integer.parseInt(addStaffSalary.getText()), addStaffPass.getText());
				
				
				if (Double.parseDouble(addStaffSalary.getText()) < 0)
					throw new NumberFormatException();

				int result = ((Admin) LoginController.loggedIn).addStaffMember(record);
				
				if(result == -1){
					throw new Exception();
				}

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();

				

				show("/view/AdminScene.fxml", event);
			} catch (NumberFormatException e ) {
				warningLabel.setText("Invalid Input");
				warningLabel.setVisible(true);

			} catch(Exception e){
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
		boolean check = addStaffName.getText().trim().isEmpty() || addStaffSalary.getText().trim().isEmpty()
				|| addStaffPass.getText().trim().isEmpty() || addStaffTele.getText().trim().isEmpty();

		return check;
	}

}
