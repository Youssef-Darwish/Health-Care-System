package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;

public class AddStaffController implements Initializable{

	@FXML // fx:id="addStaffRole"
	private ComboBox<String> addStaffRole;

	@FXML // fx:id="addStaffName"
	private TextField addStaffName;

	@FXML // fx:id="addStaffSalary"
	private TextField addStaffSalary;

	@FXML // fx:id="addStaffTele"
	private TextField addStaffTele;

	@FXML // fx:id="addStaffPass"
	private TextField addStaffPass;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void addInDB(ActionEvent event){
		System.out.println("in add");
		Record record = new StaffRecord(addStaffName.getText(), 1, addStaffTele.getText(), Integer.parseInt(addStaffSalary.getText()),
				addStaffPass.getText());

		((Admin) LoginController.loggedIn).addStaffMember(record);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	

}
