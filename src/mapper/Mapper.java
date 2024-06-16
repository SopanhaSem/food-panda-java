package mapper;

import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;

public class Mapper {
    public static CustomerDto fromCustomerDtoToCustomerDto(Customer customer){
        if (customer == null){
            return  null;
        }
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .is_deleted(customer.is_deleted())
                .created_date(customer.getCreated_date())
                .build();
    }
    public static ProductDto fromProductDtoToProductDto(Product product){
        if (product == null){
            return null;
        }
        return ProductDto.builder()
                .product_code(product.getProduct_code())
                .pro_name(product.getProduct_name())
                .is_deleted(product.is_deleted())
                .imported_at(product.getImported_at())
                .expired_at(product.getExpired_at())
                .product_description(product.getProduct_description())
                .build();
    }
    public static OrderDto fromOrderToOrderDto(Order order){
        if (order == null){
            return null;
        }
        return OrderDto.builder()
                .order_name(order.getOrder_name())
                .order_description(order.getOrder_description())
                .customer(order.getCus_id())
                .ordered_at(order.getOrdered_at())
                .build();
    }
}
