import java.time.LocalDate;
import java.util.*;

final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
        if (recordId == null || patientDNA == null || birthDate == null || bloodType == null) throw new IllegalArgumentException("Invalid medical record data");
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = Arrays.copyOf(allergies, allergies.length);
        this.medicalHistory = Arrays.copyOf(medicalHistory, medicalHistory.length);
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
    public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }

    public final boolean isAllergicTo(String substance) {
        for (String allergy : allergies) if (allergy.equalsIgnoreCase(substance)) return true;
        return false;
    }

    public String toString() { return "MedicalRecord{" + "recordId='" + recordId + '\'' + ", bloodType='" + bloodType + '\'' + ", birthDate=" + birthDate + '}'; }
}

class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    private int roomNumber;
    private String attendingPhysician;

    Patient(String tempId) {
        this.patientId = tempId;
        this.medicalRecord = null;
    }

    Patient(String patientId, MedicalRecord record, String name, String contact, String insurance, int room, String physician) {
        this.patientId = patientId;
        this.medicalRecord = record;
        this.currentName = name;
        this.emergencyContact = contact;
        this.insuranceInfo = insurance;
        this.roomNumber = room;
        this.attendingPhysician = physician;
    }

    Patient(String patientId, MedicalRecord record) {
        this.patientId = patientId;
        this.medicalRecord = record;
    }

    String getBasicInfo() { return "Patient ID: " + patientId + ", Name: " + currentName; }
    public String getPublicInfo() { return "Name: " + currentName + ", Room: " + roomNumber; }

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String name) { if (name != null && !name.isBlank()) this.currentName = name; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String contact) { if (contact != null && !contact.isBlank()) this.emergencyContact = contact; }
    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insurance) { if (insurance != null && !insurance.isBlank()) this.insuranceInfo = insurance; }
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int room) { if (room > 0) this.roomNumber = room; }
    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String physician) { if (physician != null && !physician.isBlank()) this.attendingPhysician = physician; }

    public String toString() { return "Patient{" + "patientId='" + patientId + '\'' + ", currentName='" + currentName + '\'' + ", roomNumber=" + roomNumber + ", attendingPhysician='" + attendingPhysician + '\'' + '}'; }
}

class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certifications);
    }

    public String toString() { return "Doctor{" + "licenseNumber='" + licenseNumber + '\'' + ", specialty='" + specialty + '\'' + '}'; }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    Nurse(String nurseId, String shift, List<String> qualifications) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(qualifications);
    }

    public String toString() { return "Nurse{" + "nurseId='" + nurseId + '\'' + ", shift='" + shift + '\'' + '}'; }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    Administrator(String adminId, List<String> accessPermissions) {
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(accessPermissions);
    }

    public String toString() { return "Administrator{" + "adminId='" + adminId + '\'' + '}'; }
}

public class HospitalManagementSystem {
    private final Map<String, Object> patientRegistry = new HashMap<>();
    static final String POLICY_PRIVACY = "STRICT";
    static final String POLICY_AUDIT = "ENABLED";

    public boolean admitPatient(Object patient, Object staff) {
        if (validateStaffAccess(staff, patient)) {
            if (patient instanceof Patient p) {
                patientRegistry.put(p.getPatientId(), p);
                return true;
            }
        }
        return false;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        if (staff instanceof Doctor) return true;
        if (staff instanceof Nurse) return patient instanceof Patient;
        if (staff instanceof Administrator) return true;
        return false;
    }

    void internalAudit() { System.out.println("Audit running. Policy: " + POLICY_AUDIT); }
    public String toString() { return "HospitalSystem{" + "patients=" + patientRegistry.keySet() + '}'; }
}

class Demo {
    public static void main(String[] args) {
        MedicalRecord record = new MedicalRecord("R123", "DNA_SEQ_01", new String[]{"Peanuts"}, new String[]{"Asthma"}, LocalDate.of(1990, 5, 12), "O+");
        Patient patient = new Patient("P123", record, "John Doe", "123-456", "InsuranceX", 101, "Dr. Smith");
        Doctor doctor = new Doctor("LIC456", "Cardiology", Set.of("Board Certified"));
        HospitalManagementSystem hospital = new HospitalManagementSystem();
        boolean admitted = hospital.admitPatient(patient, doctor);
        System.out.println("Admitted: " + admitted);
        System.out.println(hospital);
        System.out.println(patient.getPublicInfo());
        System.out.println(record.isAllergicTo("Peanuts"));
    }
}