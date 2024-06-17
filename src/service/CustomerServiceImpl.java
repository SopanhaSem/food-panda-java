package service;

import mapper.Mapper;
import model.dao.CustomerDao;
import model.dao.CustomerDapImpl;
import model.dto.CustomerDto;
import model.dto.ProductDto;
import model.entities.Customer;
import model.entities.Product;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao = new CustomerDapImpl();
    @Override
    public List<CustomerDto> queryAllCustomer() {
        return customerDao.queryAllNewCustomer()
                .stream()
                .map(Mapper::fromCustomerDtoToCustomerDto)
                .toList();
    }

    @Override
    public Customer updateCustomerById(Integer id) {
        Customer existingCustomer = customerDao.searchCusById(id);
        if (existingCustomer != null) {
            customerDao.updateCustomerById(existingCustomer.getId());
            return existingCustomer;
        } else {
            System.out.println("[!] Customer not found");
            return null;
        }
    }

    @Override
    public Customer deleteCustomerById(Integer id) {
        Customer existingCustomer = customerDao.searchCusById(id);
        if (existingCustomer != null) {
            customerDao.deleteCustomerById(id);
            return existingCustomer;
        } else {
            System.out.println("[!] Customer not found");
            return null;
        }
    }

    @Override
    public void addNewCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.id(),
                customerDto.name(),
                customerDto.email(),
                customerDto.pass(),
                customerDto.is_deleted(),
                customerDto.created_date());
       customerDao.addNewCustomer(customer);
    }

    @Override
    public CustomerDto searchProductById(Integer id) {
        Customer customer = customerDao.searchCusById(id);
        if (customer != null) {
            CustomerDto customerDto = Mapper.fromCustomerDtoToCustomerDto(customer);
            System.out.println("Customer ID: " +customerDto.id());
            System.out.println("Customer Name: " + customerDto.name());
            System.out.println("Customer Email: " + customerDto.email());
            System.out.println("Is Deleted: " + customerDto.is_deleted());
            System.out.println("Created At: " + customerDto.created_date());
            return customerDto;
        } else {
            System.out.println("[!] Customer not found");
            return null;
        }
    }
}
