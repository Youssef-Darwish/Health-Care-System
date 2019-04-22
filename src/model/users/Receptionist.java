package model.users;

import com.mysql.jdbc.ResultSet;

import model.functionalities.Appointment;
import model.functionalities.Functionality;
import model.functionalities.PatientRegistry;
import model.records.Record;

public class Receptionist implements User {
	Functionality patientF = new PatientRegistry();
	Functionality AppointmentF = new Appointment();

	public int editPatient(String key, String value, Record record) {
		return patientF.edit(key, value, record);
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

}
