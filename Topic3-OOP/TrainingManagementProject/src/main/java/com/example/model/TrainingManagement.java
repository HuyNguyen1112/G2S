package com.example.model;

import java.util.Scanner;

public class TrainingManagement {
    private TraineeForm traineeForm;
    private Scanner scanner;
    private Trainee[] listOfTrainee;
    private byte count;

    public TrainingManagement() {
        this.scanner = new Scanner(System.in);
        this.listOfTrainee = new Trainee[100];
        this.count = 0;
        this.traineeForm = new TraineeForm(scanner);
    }

    public static void main(String[] args) {
        TrainingManagement tm = new TrainingManagement();
        String choice;
        final String ADD = "1";
        final String SHOW = "2";
        final String SEARCHBYID = "3";
        final String SEARCHBYNAME = "4";
        final String UPDATE = "5";
        final String QUIT = "6";
        do {
            System.out.println("\n===== Quản lý thực tập sinh =====");
            System.out.println("1. Thêm mới thực tập sinh");
            System.out.println("2. Hiển thị danh sách thực tập sinh");
            System.out.println("3. Tìm kiếm thực tập sinh bằng ID");
            System.out.println("4. Tìm kiếm thực tập sinh bằng Tên");
            System.out.println("5. Thay đổi thông tin thực tập sinh");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn: ");

            choice = tm.scanner.nextLine();
            switch (choice) {
                case ADD -> tm.addTrainee();
                case SHOW -> tm.displayAllTrainees();
                case SEARCHBYID -> {
                    String id;
                    System.out.print("Nhập ID thực tập sinh muốn tìm: ");
                    id = tm.scanner.nextLine();
                    tm.findTraineeByID(id);
                }
                case SEARCHBYNAME -> {
                    String name;
                    System.out.print("Nhập tên của thực tập sinh muốn tìm: ");
                    name = tm.scanner.nextLine();
                    Trainee[] tempList = tm.findTraineeByName(name);
                    if (tempList == null)
                        return;
                    for (Trainee t : tempList) {
                        if (t != null) {
                            System.out.println(t);
                        }
                    }
                }
                case UPDATE -> {
                    String id;
                    Trainee newTrainee = new Trainee();
                    System.out.print("Nhập ID thực tập sinh muốn thay đổi: ");
                    id = tm.scanner.nextLine();
                    tm.updateTrainee(id, newTrainee);
                }
                case QUIT -> System.out.println("Đang thoát ...");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
        while (!choice.equals("6"));
    }

    public void addTrainee() {
        Trainee trainee = new Trainee();
        trainee = traineeForm.getTrainee();
        System.out.println(trainee.toString());
        listOfTrainee[count++] = trainee;
    }

    public void displayAllTrainees() {
        if (count == 0) {
            System.out.println("Danh sách chưa có học sinh nào!");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(listOfTrainee[i]);
        }
    }

    public Trainee findTraineeByID(String id) {
        for (int i = 0; i < count; i++) {
            if (listOfTrainee[i].getId().equals(id)) {
                System.out.println(listOfTrainee[i]);
                return listOfTrainee[i];
            }
        }
        System.out.println("Không tìm thấy sinh viên!");
        return null;
    }

    public Trainee[] findTraineeByName(String name) {
        Trainee[] listTemp = new Trainee[count];
        int countTemp = 0;
        for (int i = 0; i < count; i++) {
            if (listOfTrainee[i].getName().toLowerCase().contains(name.toLowerCase()))
                listTemp[countTemp++] = listOfTrainee[i];
        }
        if (countTemp == 0) {
            System.out.println("không tìm thấy sinh viên nào có tên phù hợp");
            return null;
        }

        Trainee[] result = new Trainee[countTemp];
        System.arraycopy(listTemp, 0, result, 0, countTemp);
        return result;
    }

    public void updateTrainee(String id, Trainee newTrainee) {
        newTrainee = findTraineeByID(id);
        if (newTrainee == null)
            return;
        String choice;
        final String NAME = "1";
        final String GENDER = "2";
        final String AGE = "3";

        System.out.print("Bạn muốn sửa thông tin gì? (1 -> 3): ");
        choice = scanner.nextLine();

        switch (choice) {
            case NAME -> {
                String name;
                do {
                    System.out.print("Nhập tên mới: ");
                    name = scanner.nextLine();
                }
                while (!newTrainee.setName(name));
            }

            case GENDER -> {
                String gender;
                do {
                    System.out.print("Nhập giới tính (male/female): ");
                    gender = scanner.nextLine();
                }
                while (!newTrainee.setGender(gender));
            }

            case AGE -> {
                byte age;
                do {
                    System.out.print("Nhập tuổi mới: ");
                    age = Byte.parseByte(scanner.nextLine());
                }
                while (!newTrainee.setAge(age));
            }
        }
    }
}
