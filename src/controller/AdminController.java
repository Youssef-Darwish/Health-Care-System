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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;

public class AdminController implements Initializable {

	@FXML // fx:id="addStaffRole"
	private ComboBox<String> role;

	@FXML // fx:id="addStaffName"
	private TextField name;

	@FXML // fx:id="addStaffSalary"
	private TextField salary;

	@FXML // fx:id="addStaffTele"
	private TextField telephone;

	@FXML // fx:id="addStaffPass"
	private TextField password;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	ObservableList<String> choices = FXCollections.observableArrayList("Doctor", "Receptionist", "Manager");

	//	role.setItems(choices);

	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);
		stage.show();
	}

//	public int editStaffMember (String key, String value) {
//		//form new record before sending
//		Record r;
//		return ((Admin) LoginController.loggedIn).editStaffMember(key, value, r);
//	}
//	
//	public int deleteStaffMember (String key, String value) {
//		System.out.println();
//		return ((Admin) LoginController.loggedIn).deleteStaffMember(key, value);
//	}

	@FXML
	public void addStaffMember(ActionEvent event) throws IOException {
		System.out.println("in controller");
		show("/view/AddStaffMember.fxml", event);
//		Record record = new StaffRecord(name.getText(), role.getValue(), telephone.getText(), Integer.parseInt(salary.getText()),
//				password.getText());
//
//		return ((Admin) LoginController.loggedIn).addStaffMember(record);
	}

}
