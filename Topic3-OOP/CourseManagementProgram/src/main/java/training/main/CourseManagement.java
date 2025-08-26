package training.main;

import training.entities.Course;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseManagement {
    ArrayList<Course> courses;

    public CourseManagement() {
         courses = new ArrayList<>();
    }

    private void input (Scanner sc) {
        Course course = new Course();

        course.input(sc,courses);

        courses.add(course);

        System.out.println("Course added successfully!");
    }

    private ArrayList<Course> search(String type, Object data) {
        return courses.stream()
                .filter(c -> {
                    switch (type.toLowerCase()) {
                        case "code": return c.getCode().equalsIgnoreCase(data.toString());
                        case "name": return c.getName().toLowerCase().contains(data.toString().toLowerCase());
                        case "status": return c.isStatus() == Boolean.parseBoolean(data.toString());
                        case "flag": return c.getFlag().equalsIgnoreCase(data.toString());
                        default: return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private void displayAll(String flag) {
        courses.stream()
                .filter(c -> flag.equalsIgnoreCase(c.getFlag()))
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseManagement cm = new CourseManagement();

        while (true) {
            System.out.println("\n1. Add course");
            System.out.println("2. Search course");
            System.out.println("3. Display by flag");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> cm.input(sc);
                case 2 -> {
                    System.out.print("Search by (code/name/status/flag): ");
                    String type = sc.nextLine();
                    System.out.print("Enter value: ");
                    String val = sc.nextLine();
                    ArrayList<Course> results = cm.search(type, val);
                    results.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter flag (optional/prerequisite/N/A): ");
                    String flag = sc.nextLine();
                    cm.displayAll(flag);
                }
                case 4 -> {
                    return;
                }
            }
        }

    }
}
