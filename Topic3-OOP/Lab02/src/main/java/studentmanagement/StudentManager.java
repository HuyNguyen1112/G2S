package studentmanagement;

import java.util.Scanner;

public class StudentManager {
    static Student[] students = new Student[100];
    static int count;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String choice;
        final String ADD = "1";
        final String SHOW = "2";
        final String SEARCH = "3";
        final String UPDATE = "4";
        final String QUIT = "5";
        do {
            System.out.println("\n===== Quản lý sinh viên =====");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên bằng ID");
            System.out.println("4. Thay đổi thông tin sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn: ");
            choice = sc.nextLine();

            switch (choice) {
                case ADD:
                    createStudent();
                    break;
                case SHOW:
                    displayAll();
                    break;
                case SEARCH:
                    findStudentById();
                    break;
                case UPDATE:
                    updateStudentById();
                    break;
                case QUIT:
                    System.out.println("Đang thoát ...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        }
        while (!choice.equals("5"));
    }

    //Method tạo sinh viên
    static void createStudent() {
        if (!checkSlot())
            return;

        String id;
        String name;
        int age;
        String address;
        int genderNum;
        String gender;
        String email;

        do {
            System.out.print("Nhập ID: ");
            id = sc.nextLine();
        }
        while (!checkIdValid(id));


        do {
            System.out.print("Nhập tên: ");
            name = sc.nextLine();
        }
        while (!checkNameValid(name));

        do {
            System.out.print("Nhập tuổi: ");
            age = Integer.parseInt(sc.nextLine());
        }
        while (!checkAgeValid(age));


        System.out.print("Nhập địa chỉ: ");
        address = sc.nextLine();

        do {
            System.out.print("Nhập giới tính (1: Nam/ 2: Nữ): ");
            genderNum = Integer.parseInt(sc.nextLine());
        }
        while (!checkGenderValid(genderNum));
        if (genderNum == 1)
            gender = "Nam";
        else
            gender = "Nữ";


        System.out.print("Nhập email: ");
        email = sc.nextLine();

        students[count++] = new Student(id, name, age, address, gender, email);
        System.out.println("Sinh viên được thêm vào danh sách thành công!");
    }

    //Method in ra toàn bộ sinh viên
    static void displayAll() {
        if (count == 0) {
            System.out.println("Danh sách chưa có học sinh nào!");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    //Method in tìm ra sinh viên bằng ID
    static Student findStudentById() {
        System.out.print("Nhập ID muốn tìm: ");
        String id = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                System.out.println(students[i]);
                return students[i];
            }
        }
        System.out.println("Không tìm thấy sinh viên!");
        return null;
    }

    //Method thay đổi thông tin sinh viên
    static void updateStudentById() {
        int choice;
        do {
            Student student = findStudentById();
            if(student == null)
                return;
            System.out.println("Nhập thông tin mà bạn muốn thay đổi(1 -> 5): ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    String name;
                    do {
                        System.out.print("Nhập tên mới: ");
                        name = sc.nextLine();
                    }
                    while (!checkNameValid(name));
                    student.setName(name);
                }
                case 2 -> {
                    int age;
                    do {
                        System.out.print("Nhập tuổi mới: ");
                        age = Integer.parseInt(sc.nextLine());
                    }
                    while (!checkAgeValid(age));
                    student.setAge(age);
                }
                case 3 -> {
                    String address;
                    System.out.print("Nhập địa chỉ mới: ");
                    address = sc.nextLine();
                    student.setAddress(address);
                }
                case 4 -> {
                    int genderNum;
                    do {
                        System.out.print("Nhập giới tính khác (1: Nam/ 2: Nữ): ");
                        genderNum = Integer.parseInt(sc.nextLine());
                    }
                    while (!checkGenderValid(genderNum));
                    if (genderNum == 1)
                        student.setGender("Nam");
                    else
                        student.setGender("Nữ");

                }
                case 5 -> {
                    String email;
                    System.out.print("Nhập Email mới: ");
                    email = sc.nextLine();
                    student.setEmail(email);
                }
            }
            System.out.println("Bạn có muốn tiếp tục thay đổi thông tin? (1 có/ số khác: không)");
            choice = Integer.parseInt(sc.nextLine());
        }
        while (choice == 1);
    }

    //Method kiểm tra danh sách đã đấy hay chưa
    static boolean checkSlot() {
        if (count >= 100) {
            System.out.println("Đã đạt giới hạn 100 sinh viên, không thể thêm mới! ");
            return false;
        }
        return true;
    }

    //Method kiểm tra ID có bị trùng với ID đã tồn tại không
    static boolean checkIdValid(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                System.out.println("ID này đã tồn tại, mời nhập ID khác! ");
                return false;
            }
        }
        return true;
    }

    //Method kiểm tra tên nhập vào không được để trống
    static boolean checkNameValid(String name) {
        if (name.trim().isEmpty()) {
            System.out.println("Tên không thể để trống!");
            return false;
        }
        return true;
    }

    //Method kiểm tra tuổi phải >=18
    static boolean checkAgeValid(int age) {
        if (age < 18) {
            System.out.println("Tuổi phải >= 18!");
            return false;
        }
        return true;
    }

    //Method kiểm tra giới tính
    static boolean checkGenderValid(int genderNum) {
        if (!(genderNum == 1) && !(genderNum == 2)) {
            System.out.println("Giới tính không hợp lệ!");
            return false;
        }
        return true;
    }
}
