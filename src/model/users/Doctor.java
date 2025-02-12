package model.users;

import com.mysql.jdbc.ResultSet;

import model.functionalities.DoctorAvailablities;
import model.functionalities.Functionality;
import model.functionalities.Medication;
import model.functionalities.PatientCase;
import model.records.AvailablityRecord;
import model.records.Record;

public class Doctor implements User {
	private int id;
	Functionality f = new PatientCase();
	Medication m = new Medication();
	PatientCase patientCase = new PatientCase();
	DoctorAvailablities availablityF = new DoctorAvailablities();

	public int editPatientCase(String key, String value, Record record) {
		return f.edit(key, value, record);
	}

	public int deletePatientCase(String key, String value) {
		System.out.println("in doctor");
		return f.delete(key, value);
	}

	public int addPatientCase(Record record) {
		System.out.println("in doctor");
		return f.add(record);
	}

	public int addAvailableTime(Record record) {
		return availablityF.add(record);
	}

	public ResultSet getAllMedicines() {
		return m.getAll();
	}

	public ResultSet getDoctorPatients(int id) {
		return patientCase.getDoctorsPatient(id);
	}

	public ResultSet searchPatientCase(String key, String value) {

		return f.search(key, value);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public ResultSet getAvailability(String id) {

		return availablityF.search("", id);

	}

	public int deleteAvail(AvailablityRecord r) {
		return availablityF.deleteComposite("DATE", r.getDate().toString(), "HOUR", r.getTime());

	}

}
