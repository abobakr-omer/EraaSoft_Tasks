package question_5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ----- Person -----
        System.out.println("Enter Person data (id name):");
        Integer personId = scanner.nextInt();
        String personName = scanner.next();

        Person person = new Person(personName,personId);

        // ----- Player -----
        System.out.println("Enter Player data (id name number phone):");
        Integer playerId = scanner.nextInt();
        String playerName = scanner.next();
        Integer playerNumber = scanner.nextInt();
        String playerPhone = scanner.next();

        Player player = new Player(playerName,playerId,playerPhone,playerNumber);

        // ----- Student -----
        System.out.println("Enter Student data (id name age phone):");
        Integer studentId = scanner.nextInt();
        String studentName = scanner.next();
        Integer studentAge = scanner.nextInt();
        String studentPhone = scanner.next();

        Student student = new Student(studentName,studentId,studentPhone,studentAge);

        // ----- Print all -----
        System.out.println();
        person.printPersonData();
        System.out.println();
        player.printPlayerData();
        System.out.println();
        student.printStudentData();
    }

}
