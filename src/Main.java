import java.sql.SQLException;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.functionalities.Functionality;
import model.functionalities.StaffMember;
import model.records.StaffRecord;

public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// launch(args);

		Controller c = new Controller();
//
		StaffRecord r = new StaffRecord(2, "a", 1, "01555", 200, "pass");
//		System.out.println(r.getPassword());
//		System.out.println(c.editStaffMember("ID","1",r));

//		c.deleteStaffMember("NAME", "youssef");

		c.addStaffMember(r);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/MyScene.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
