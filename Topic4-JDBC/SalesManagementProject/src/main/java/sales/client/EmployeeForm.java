package sales.client;

import sales.entities.Customer;
import sales.entities.Employee;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeForm {
    private final Scanner sc;

    public EmployeeForm(Scanner sc) {
        this.sc = sc;
    }

    public int getId() {
        int id;

        System.out.print("Enter ID: ");
        id = Integer.parseInt(sc.nextLine());

        return id;
    }

    public Employee getEmployee() {
        Employee employee = new Employee();

        System.out.print("Enter last name: ");
        employee.setLastName(sc.nextLine());

        System.out.print("Enter first name: ");
        employee.setFirstName(sc.nextLine());

        try {
            System.out.print("Enter day of birth date: ");
            int day = sc.nextInt();

            System.out.print("Enter month of birth date: ");
            int month = sc.nextInt();

            System.out.print("Enter year of birth date: ");
            int year = sc.nextInt();

            LocalDate date = LocalDate.of(year, month, day);
            String birthdate = date.toString();
            employee.setBirthdate(birthdate);

        } catch (DateTimeException e) {
            System.out.println("Ngày tháng không hợp lệ!");
        }

        System.out.print("Enter supervisor ID: ");
        employee.setSupervisor(Integer.parseInt(sc.nextLine()));

        return employee;
    }
}
