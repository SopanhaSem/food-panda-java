package service;

import model.dto.OrderDto;
import model.entities.Order;
import model.entities.Product;

import java.util.List;

public interface OrderService {
    List<OrderDto> queryAllNewOrder();
    int updateOrderById(Integer id);
    int deleteOrderById(Integer id);
    void addNewOrder(OrderDto orderDto);
    OrderDto searchOrderById(Integer id);
}
