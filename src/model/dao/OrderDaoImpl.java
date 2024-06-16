package model.dao;

import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao{
    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name,order_description,cus_id,ordered_at)
                VALUES(?,?,?,?)
                """;
        String sql1 = """
                INSERT INTO "product_order"
                VALUES(?,?)
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        ){
            preparedStatement.setString(1, order.getOrder_name());
            preparedStatement.setString(2,order.getOrder_description());
            preparedStatement.setInt(3,order.getCus_id().getId());
            preparedStatement.setDate(4,order.getOrdered_at());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0){
                System.out.println("Row Affected..");
            }else{
                System.out.println("Failed");
            }
            for (Product product : order.getProducts()){
                preparedStatement1.setInt(1,product.getId());
                preparedStatement1.setInt(2,order.getId());
            }
            int rowAffected1 = preparedStatement1.executeUpdate();
            String message = rowAffected1>0 ? "Row Affected" : "Failed";
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order deleteOrderById(Integer id) {
        String sql = """
                DELETE FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda",
                        "postgres",
                        "asd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            Order order =  searchOrderById(id);
            if(order != null){
                preparedStatement.setInt(1,id);
                int rowAffected = preparedStatement.executeUpdate();
                String message = rowAffected>0 ? "Row Affected" : "Failed";
                System.out.println(message);
            }else {
                System.out.println("[!] User not found..");
            }
            return order;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order updateOrderById(Integer id) {
        String sql = """
                UPDATE "order" 
                    SET "order_name" = ?, "order_description" = ?
                    WHERE id = ? 
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            Order order = searchOrderById(id);
            if (order != null){
                System.out.print("[+] Input new order  name: ");
                preparedStatement.setString(1,new Scanner(System.in).next());
                System.out.print("[+] Input new order description: ");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3,id);
                int rowAffected=preparedStatement.executeUpdate();
                String message = rowAffected>0 ? "😁😁😁😁Row Affected" : "😭😭😭Failed";
                System.out.println(message);
            }
            else{
                System.out.println("🤣🤣🤣 User not found");
            }
            return order;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                SELECT * FROM "order" WHERE id = ?
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                        );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Order order = null;
        while(resultSet.next()){
            order = Order.builder()
                    .id(resultSet.getInt("id"))
                    .order_name(resultSet.getString("order_name"))
                    .order_description(resultSet.getString("order_description"))
                    .cus_id(Customer.builder()
                            .id(resultSet.getInt("id"))
                            .build())
                    .ordered_at(resultSet.getDate("ordered_at"))
                    .build();
        }
        return order;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;

    }

    @Override
    public List<Order> queryAllOrder() {
      String sql = """
              SELECT * FROM "order"
              INNER JOIN "customer" c on "order".cus_id = c.id
              """;
      try(
              Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                      "postgres","asd"
              );
              Statement statement = connection.createStatement()
              ) {
          ResultSet resultSet = statement.executeQuery(sql);
          List<Order> orders = new ArrayList<>();
          while (resultSet.next()){
              orders.add(Order.builder()
                              .id(resultSet.getInt("id"))
                              .order_name(resultSet.getString("order_name"))
                              .order_description(resultSet.getString("order_description"))
                              .ordered_at(resultSet.getDate("ordered_at"))
                              .cus_id(Customer.builder()
                                      .id(resultSet.getInt("id"))
                                      .name(resultSet.getString("name"))
                                      .email(resultSet.getString("email"))
                                      .password(resultSet.getString("password"))
                                      .is_deleted(resultSet.getBoolean("is_deleted"))
                                      .created_date(resultSet.getDate("created_date"))
                                      .build())
                      .build());
          }
          return orders;
      }catch (SQLException sqlException){
          System.out.println(sqlException.getMessage());
      }
    return new ArrayList<>();
    }
}
