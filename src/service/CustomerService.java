package service;

import model.dto.CustomerDto;
import model.dto.ProductDto;
import model.entities.Customer;
import model.entities.Product;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> queryAllCustomer();
    Customer updateCustomerById(Integer id);
    Customer deleteCustomerById(Integer id);
    void addNewCustomer(CustomerDto customerDto);
    CustomerDto searchProductById(Integer id);
}
