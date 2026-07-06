package task_2;

import task_3.Teacher;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Doctor doctor1 = new Doctor(1, "Ahmed", 35000);
        Doctor doctor2 = new Doctor(2, "Mohamed", 42000);
        Doctor doctor3 = new Doctor(3, "Sara", 50000);

        Patient patient1 = new Patient(1, "Ali", 20);
        Patient patient2 = new Patient(2, "Mona", 25);
        Patient patient3 = new Patient(3, "Omar", 30);

        List<Doctor> doctors= Arrays.asList(doctor1,doctor2,doctor3);
        List<Patient> patients=Arrays.asList(patient1,patient2,patient3);

        doctor1.setPatients(patients);
        doctor2.setPatients(patients);
        doctor3.setPatients(patients);

        patient1.setDoctors(doctors);
        patient2.setDoctors(doctors);
        patient3.setDoctors(doctors);

        doctors.stream().forEach(doctor -> System.out.println(doctor.getPatients()));
        patients.stream().forEach(patient -> System.out.println(patient.getDoctors()));












    }



}
