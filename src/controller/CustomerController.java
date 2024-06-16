package controller;

import model.dto.CustomerDto;
import model.dto.ProductDto;
import model.entities.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

import java.util.List;

public class CustomerController {
    CustomerService customerService = new CustomerServiceImpl();
    public void addNewCustomer(CustomerDto customerDto){customerService.addNewCustomer(customerDto);}
    public List<CustomerDto> queryAllCustomer(){return customerService.queryAllCustomer();}
    public void searchCustomerById(Integer id) {
        customerService.searchProductById(id);
    }
    public void deleteCustomer(Integer id){customerService.deleteCustomerById(id);}
    public void updateCustomer(Integer id){customerService.updateCustomerById(id);}
}
