package model.dao;

import model.dto.OrderDto;
import model.entities.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    Order deleteOrderById(Integer id);
    Order updateOrderById(Integer id);
    Order searchOrderById(Integer id);
    List<Order> queryAllOrder();

}
