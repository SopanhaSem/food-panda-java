package model.dao;

import model.entities.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    int deleteOrderById(Integer id);
    int updateOrderById(Integer id);
    int searchOrderById(Integer id);
    List<Order> queryAllOrder();

}
