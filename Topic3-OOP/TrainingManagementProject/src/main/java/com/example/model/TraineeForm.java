package com.example.model;

import java.util.Scanner;

public class TraineeForm {
    private Scanner scanner;

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getId() {
        System.out.print("Nhập ID: ");
        return scanner.nextLine();
    }


    public Trainee getTrainee() {
        Trainee trainee = new Trainee();
        String id;
        String name;
        String gender;
        byte age;

        do {
            id = getId();
        } while (!trainee.setId(id));

        do {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine();
        }
        while (!trainee.setName(name));

        do {
            System.out.print("Nhập giới tính (male/female): ");
            gender = scanner.nextLine();
        }
        while (!trainee.setGender(gender));

        do {
            System.out.print("Nhập tuổi: ");
            age = Byte.parseByte(scanner.nextLine());
        }
        while (!trainee.setAge(age));
        return trainee;
    }


}
