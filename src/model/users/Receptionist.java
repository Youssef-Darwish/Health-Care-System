package model.users;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import model.functionalities.Appointment;
import model.functionalities.DoctorAvailablities;
import model.functionalities.Functionality;
import model.functionalities.PatientRegistry;
import model.functionalities.StaffMember;
import model.records.Record;

public class Receptionist implements User {
	Functionality patientF = new PatientRegistry();
	Functionality AppointmentF = new Appointment();
	DoctorAvailablities availablityF = new DoctorAvailablities();
	Functionality staffF = new StaffMember();

	public int editPatient(String key, String value, Record record) {
		return patientF.edit(key, value, record);
	}

	public ArrayList<String> getPatientsNames() throws SQLException {
		ResultSet rs = patientF.getAll();
		ArrayList<String> names = new ArrayList<>();

		while (rs.next())
			names.add(rs.getString(2) + " (id: " + rs.getInt(1) + ")");

		return names;
	}

	public int deletePatient(String key, String value) {
		return patientF.delete(key, value);
	}

	public int addPatient(Record record) {
		return patientF.add(record);
	}

	public ResultSet getAllPatients() {
		return patientF.getAll();
	}

	public ResultSet getAllDoctorsAvails() {
		return availablityF.getAll();
	}

	public ResultSet searchPatient(String key, String value) {
		return patientF.search(key, value);
	}

	public int editAppointment(String key, String value, Record record) {
		return AppointmentF.edit(key, value, record);
	}

	public int deleteAppointment(String key, String value) {
		return AppointmentF.delete(key, value);
	}

	public int addAppointment(Record record) {
		return AppointmentF.add(record);
	}

	public ResultSet getAllAppointments() {

		return AppointmentF.getAll();
	}

	public ResultSet searchAppointment(String key, String value) {

		return AppointmentF.search(key, value);
	}

	public ResultSet getAvailability(String id) {

		return availablityF.search("", id);

	}

	public int deleteAvailability(String key1,String value1,String key2,String value2) {

		return availablityF.deleteComposite(key1, value1, key2, value2);

	}

	public ArrayList<String> getDoctorNames() throws SQLException {
		ResultSet rs = staffF.search("ROLE", "Doctor");
		ArrayList<String> names = new ArrayList<>();

		while (rs.next())
			names.add(rs.getString(2) + " (id: " + rs.getInt(1) + ")");
		return names;
	}

}
