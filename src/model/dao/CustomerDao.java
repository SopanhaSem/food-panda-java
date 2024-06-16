package model.dao;

import model.entities.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllNewCustomer();
    int updateCustomerById(Integer id);
    int deleteCustomerById(Integer id);
    void addNewCustomer(Customer customer);
    Customer searchCusById(Integer id);
}
