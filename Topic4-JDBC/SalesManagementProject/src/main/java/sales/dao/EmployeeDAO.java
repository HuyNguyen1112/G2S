package sales.dao;

import sales.entities.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Employee> selectAll() throws SQLException {
        if (conn == null) {
            return null;
        }

        String select = "select * from employees";
        ArrayList<Employee> employees = new ArrayList<>();

        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(select)) {
            while (rs.next()) {
                Employee employee = new Employee();

                employee.setId(rs.getInt("employee_id"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setBirthdate(rs.getString("birth_date"));
                employee.setSupervisor(rs.getInt("supervisor_id"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new SQLException("Can not display employee " + e.getMessage());
        }

        return employees;
    }

    public boolean insert(Employee employee) throws SQLException {
        if (conn == null) {
            return false;
        }

        String insert = "insert into employees(last_name, first_name, " +
                "birth_date, supervisor_id) values (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getBirthdate());
            ps.setInt(4, employee.getSupervisor());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Can not insert employee" + e.getMessage());
        }
        return false;
    }

    public boolean update(int id, Employee employee) throws SQLException {
        if (conn == null)
            return false;

        String update = "update employees set last_name = ?, first_name = ?," +
                " birth_date = ?, supervisor_id = ? where employee_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getBirthdate());
            ps.setInt(4, employee.getSupervisor());
            ps.setInt(5, id);

            if (ps.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            throw new SQLException("Can not update employee " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) throws SQLException {
        if (conn == null)
            return false;

        String delete = "delete from employees where employee_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Can not delete employee!" + e.getMessage());
        }
        return false;
    }
}
