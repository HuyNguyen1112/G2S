package sales.client;

import sales.dao.CustomerDAO;
import sales.dao.EmployeeDAO;
import sales.entities.Customer;
import sales.entities.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesManagement {
    private static Scanner sc;
    private CustomerDAO customerDAO;
    private CustomerForm customerForm;
    private EmployeeDAO employeeDAO;
    private EmployeeForm employeeForm;

    static final String CHOOSE_CUSTOMERS = "1";
    static final String CHOOSE_EMPLOYEE = "2";
    static final String QUIT = "0";

    static final String GET_ALL = "1";
    static final String ADD_NEW = "2";
    static final String UPDATE = "3";
    static final String REMOVE = "4";
    static final String RETURN = "0";

    private void initialize() throws SQLException {
        sc = new Scanner(System.in);
        Connection conn = getConnection();

        customerForm = new CustomerForm(sc);
        customerDAO = new CustomerDAO(conn);

        employeeForm = new EmployeeForm(sc);
        employeeDAO = new EmployeeDAO(conn);
    }

    public SalesManagement() throws SQLException {
        initialize();
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sales",
                    "root",
                    "meoconsieucapvutruhuydietsieutantinhchivoi1caibungtay"
            );

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Can not open connection " + e.getMessage());
        }
    }

    private void displayALlEmployee() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.selectAll();

        if (employees == null || employees.isEmpty()) {
            System.out.println("Not found");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addEmployee() throws SQLException {
        Employee employee = employeeForm.getEmployee();

        if (employeeDAO.insert(employee)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void updateEmployee() throws SQLException {
        int id = employeeForm.getId();
        Employee employee = employeeForm.getEmployee();

        if (employeeDAO.update(id, employee)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void removeEmployee() throws SQLException {
        int id = employeeForm.getId();

        if (employeeDAO.delete(id)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void displayALlCustomer() throws SQLException {
        ArrayList<Customer> customers = customerDAO.selectAll();

        if (customers == null || customers.isEmpty()) {
            System.out.println("Not found");
            return;
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void addCustomer() throws SQLException {
        Customer customer = customerForm.getCustomer();

        if (customerDAO.insert(customer)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void updateCustomer() throws SQLException {
        int id = customerForm.getId();
        Customer customer = customerForm.getCustomer();

        if (customerDAO.update(id, customer)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private void removeCustomer() throws SQLException {
        int id = customerForm.getId();

        if (customerDAO.delete(id)) {
            System.out.println("Successful");
        } else {
            System.out.println("Unsuccessful");
        }
    }

    private static void createTableMenu() {
        System.out.println("\n1.Choose customers");
        System.out.println("2.Choose employee");
        System.out.println("0.Quit");
        System.out.print("Your choice: ");
    }

    private static void createCRUDMenu() {
        System.out.println("\n1.Get all in list");
        System.out.println("2.Add new");
        System.out.println("3.Change infomation");
        System.out.println("4.Remove");
        System.out.println("0.Return");
        System.out.print("Your choice: ");
    }

    private void employeeCRUD() {
        String choice = "";
        do {
            try {
                createCRUDMenu();
                choice = sc.nextLine();
                switch (choice) {
                    case GET_ALL -> displayALlEmployee();
                    case ADD_NEW -> addEmployee();
                    case UPDATE-> updateEmployee();
                    case REMOVE -> removeEmployee();
                    case QUIT -> System.out.println("Returning ...");
                    default -> System.out.println("Wrong choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!choice.equals(RETURN));
    }

    private void customerCRUD() {
        String choice = "";
        do {
            try {
                createCRUDMenu();
                choice = sc.nextLine();
                switch (choice) {
                    case GET_ALL -> displayALlCustomer();
                    case ADD_NEW -> addCustomer();
                    case UPDATE -> updateCustomer();
                    case REMOVE -> removeCustomer();
                    case QUIT -> System.out.println("Returning ...");
                    default -> System.out.println("Wrong choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!choice.equals(RETURN));
    }

    public static void main(String[] args) {
        String choice = "";
        SalesManagement salesManagement = null;

        do {
            try {
                salesManagement = new SalesManagement();
                createTableMenu();
                choice = sc.nextLine();
                switch (choice) {
                    case CHOOSE_CUSTOMERS -> salesManagement.customerCRUD();
                    case CHOOSE_EMPLOYEE ->  salesManagement.employeeCRUD();
                    case QUIT -> System.out.println("Exiting ...");
                    default -> System.out.println("wrong choice!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (!choice.equals((QUIT)));
    }

}
