package model.dao;

import model.entities.Customer;
import model.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name,order_description,cus_id,ordered_at)
                VALUES(?,?,?,?)
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                )
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        return 0;
    }

    @Override
    public int searchOrderById(Integer id) {
        return 0;
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
