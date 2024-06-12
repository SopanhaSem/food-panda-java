package model.dao;
import model.entities.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDapImpl implements CustomerDao {
    @Override
    public List<Customer> queryAllNewCustomer() {
    String sql = """
            SELECT * FROM "customer"
            """;
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda","postgres","asd");
        ResultSet resultSet = connection.createStatement().executeQuery(sql)
    ) {
        List<Customer> customerList = new ArrayList<>();
        while(resultSet.next()){
                customerList.add(new Customer(
                        resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getBoolean("is_deleted"),
            resultSet.getDate("created_date")));
        }
        return customerList;
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    return new ArrayList<>();
}
    @Override
    public int updateCustomerById(Integer id) {
       String sql = """
               UPDATE "customer" SET name = ? ,email = ? WHERE id = ?
               """;
       try (  Connection connection = DriverManager.getConnection(
               "jdbc:postgresql://localhost:5432/food_panda",
               "postgres","asd"
       );
              PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            Customer customer = searchCusById(id);
            if (customer != null){
                System.out.print("[+] Input name: ");
                preparedStatement.setString(1,new Scanner(System.in).next());
                System.out.print("[+] Input email: ");
                preparedStatement.setString(2,new Scanner(System.in).next());
                preparedStatement.setInt(3,id);
            }
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0 ? "Update Success" : "Update failed";
           System.out.println(message);
       }catch (SQLException sqlException){
           System.out.println(sqlException.getMessage());
       }return  0;
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = """
                DELETE FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
        Customer customer = searchCusById(id);
        if (customer == null){
            System.out.println("[!] Customer not found");
        }else{
            preparedStatement.setInt(1,id);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("[+] Successfully Deleted");
        }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return id;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        String sql = """
                INSERT INTO "customer" (name,email,password,is_deleted,created_date)
                VALUES(?,?,?,?,?)
                """;
        try (
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                    )
                ){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getPassword());
            preparedStatement.setBoolean(4,customer.is_deleted());
            preparedStatement.setDate(5,customer.getCreated_date());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0){
                System.out.println("Row Affected..");
            }else{
                System.out.println("Failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer searchCusById(Integer id) {
        String sql = """
                SELECT * FROM "customer" WHERE id = ?
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda","postgres","asd");
           PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = null;
          while (resultSet.next()){
               customer = Customer.builder()
                      .id(resultSet.getInt("id"))
                      .name(resultSet.getString("name"))
                      .email(resultSet.getString("email"))
                      .password(resultSet.getString("password"))
                      .is_deleted(resultSet.getBoolean("is_deleted"))
                      .created_date(resultSet.getDate("created_date"))
                    .build();
          }
          return customer;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return  null;
    }
}
