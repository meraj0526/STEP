import java.util.ArrayList;
import java.util.List;

class Patient {
    String patientId, patientName, gender, contactInfo;
    int age;
    List<String> medicalHistory = new ArrayList<>();
    List<String> currentTreatments = new ArrayList<>();
    static int totalPatients = 0;

    Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        totalPatients++;
    }

    void updateTreatment(String treatment) {
        currentTreatments.add(treatment);
        medicalHistory.add(treatment);
    }

    void dischargePatient() {
        currentTreatments.clear();
    }
}

class Doctor {
    String doctorId, doctorName, specialization;
    List<String> availableSlots = new ArrayList<>();
    int patientsHandled;
    double consultationFee;

    Doctor(String doctorId, String doctorName, String specialization, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.patientsHandled = 0;
    }

    void addAvailableSlot(String slot) {
        availableSlots.add(slot);
    }

    void incrementPatientsHandled() {
        patientsHandled++;
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate, appointmentTime, status;
    static int totalAppointments = 0;
    static String hospitalName = "Meraj Hospital";
    static double totalRevenue = 0;

    Appointment(String appointmentId, Patient patient, Doctor doctor, String appointmentDate, String appointmentTime, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        totalAppointments++;
    }

    double generateBill(String appointmentType) {
        double rate = 0;
        switch (appointmentType.toLowerCase()) {
            case "consultation":
                rate = doctor.consultationFee;
                break;
            case "follow-up":
                rate = doctor.consultationFee * 0.7;
                break;
            case "emergency":
                rate = doctor.consultationFee * 1.5;
                break;
        }
        totalRevenue += rate;
        return rate;
    }

    void cancelAppointment() {
        status = "Cancelled";
    }
}

public class Assignment8_Meraj {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();

        Patient p1 = new Patient("P001", "John Doe", 30, "Male", "1234567890");
        Patient p2 = new Patient("P002", "Alice Smith", 25, "Female", "0987654321");

        Doctor d1 = new Doctor("D001", "Dr. Brown", "Cardiologist", 500);
        Doctor d2 = new Doctor("D002", "Dr. Green", "Neurologist", 600);

        d1.addAvailableSlot("10:00 AM");
        d1.addAvailableSlot("02:00 PM");
        d2.addAvailableSlot("11:00 AM");
        d2.addAvailableSlot("03:00 PM");

        Appointment a1 = new Appointment("A001", p1, d1, "2025-09-05", "10:00 AM", "Scheduled");
        Appointment a2 = new Appointment("A002", p2, d2, "2025-09-06", "11:00 AM", "Scheduled");

        appointments.add(a1);
        appointments.add(a2);
        patients.add(p1);
        patients.add(p2);
        doctors.add(d1);
        doctors.add(d2);

        a1.generateBill("Consultation");
        a2.generateBill("Emergency");

        p1.updateTreatment("Heart Checkup");
        p2.updateTreatment("Brain MRI");

        d1.incrementPatientsHandled();
        d2.incrementPatientsHandled();

        a2.cancelAppointment();

        System.out.println("Total Patients: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + Appointment.totalAppointments);
        System.out.println("Total Revenue: " + Appointment.totalRevenue);

        System.out.println("\nPatient Treatments:");
        for (Patient p : patients) {
            System.out.println(p.patientName + ": " + p.currentTreatments);
        }

        System.out.println("\nDoctor Utilization:");
        for (Doctor d : doctors) {
            System.out.println(d.doctorName + " handled " + d.patientsHandled + " patients");
        }

        System.out.println("\nAppointments Status:");
        for (Appointment a : appointments) {
            System.out.println(a.appointmentId + " - " + a.status);
        }
    }
}
