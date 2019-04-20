package model.records;

public class PatientCaseRecord implements Record {

	private int patientId;
	private int caseId;
	private String disease;
	private String medication;
	private int amount;

	public PatientCaseRecord(int patientId, String disease, String medication, int amount) {
		this.patientId = patientId;
		this.disease = disease;
		this.medication = medication;
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}

	public void setGetAmount(int aAmount) {
		this.amount = amount;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
}
