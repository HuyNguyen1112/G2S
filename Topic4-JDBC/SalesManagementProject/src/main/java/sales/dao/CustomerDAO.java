package sales.dao;

import sales.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    private final Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Customer> selectAll() throws SQLException {
        if (conn == null) {
            return null;
        }

        String select = "select * from customers";
        ArrayList<Customer> customers = new ArrayList<>();

        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(select)) {
            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setContact(rs.getString("contact_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setPostCode(rs.getString("post_code"));
                customer.setCountry(rs.getString("country"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new SQLException("Can not display customers " + e.getMessage());
        }

        return customers;
    }

    public boolean insert(Customer customer) throws SQLException {
        if (conn == null) {
            return false;
        }

        String insert = "insert into customers(customer_name, contact_name, address, " +
                "city, post_code, country) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Can not insert customer" + e.getMessage());
        }
        return false;
    }

    public boolean update(int id, Customer customer) throws SQLException {
        if (conn == null)
            return false;

        String update = "update customers set customer_name = ?, contact_name = ?," +
                " address = ?, city = ?, post_code = ?, country = ? where customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, id);

            if (ps.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            throw new SQLException("Can not update customer " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) throws SQLException {
        if (conn == null)
            return false;

        String delete = "delete from customers where customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Can not delete customer " + e.getMessage());
        }
        return false;
    }
}
