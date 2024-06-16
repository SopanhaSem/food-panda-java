package service;

import mapper.Mapper;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entities.Order;
import model.entities.Product;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<OrderDto> queryAllNewOrder() {
        return orderDao.queryAllOrder().stream().map(Mapper::fromOrderToOrderDto).toList();
    }

    @Override
    public int updateOrderById(Integer id) {
        Order updatedOrder = orderDao.updateOrderById(id);
        return updatedOrder != null ? 1 : 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        Order deletedOrder = orderDao.deleteOrderById(id);
        return deletedOrder != null ? 1 : 0;
    }

    @Override
    public void addNewOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .order_name(orderDto.order_name())
                .order_description(orderDto.order_description())
                .ordered_at(orderDto.ordered_at())
                .cus_id(orderDto.customer())
                .build();
        orderDao.addNewOrder(order);
    }

    @Override
    public OrderDto searchOrderById(Integer id) {
        Order order = orderDao.searchOrderById(id);
        if (order != null) {
            OrderDto orderDto = Mapper.fromOrderToOrderDto(order);
            System.out.println("Order Name: " + orderDto.order_name());
            System.out.println("Order Description: " + orderDto.order_description());
            System.out.println("Customer ID: " + orderDto.customer().getId());
            System.out.println("Order At: " + orderDto.ordered_at());
            return orderDto;
        } else {
            System.out.println("[!] Order not found");
            return null;
        }
    }
}
