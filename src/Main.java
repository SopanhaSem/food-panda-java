import model.dao.CustomerDapImpl;
import model.dao.OrderDaoImpl;
import model.dao.ProductDaoImpl;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;

import java.sql.Date;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        new CustomerDapImpl().addNewCustomer(new Customer(2,"Panha","nha@gmail.com","slfjsd@#r2r",false, Date.valueOf(LocalDate.now())));
//        new ProductDaoImpl().addNewProduct(new Product(2,"Sting","1002",false,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()),"Best drinks"));
//        new CustomerDapImpl().deleteCustomerById(2);
//        new ProductDaoImpl().deleteProductById(3);
//        new CustomerDapImpl().queryAllNewCustomer().forEach(System.out::println);
//        new ProductDaoImpl().queryAllNewProduct().forEach(System.out::println);
//        new CustomerDapImpl().updateCustomerById(3);
//        new ProductDaoImpl().updateProductById(3);
//        new OrderDaoImpl().addNewOrder(Order.builder()
//                        .id(2)
//                        .order_name("Mi hell")
//                         .order_description("hot pot tul kork")
//                        .cus_id(Customer.builder().id(3).build())
//                        .ordered_at(Date.valueOf(LocalDate.now()))
//                .build());
            new OrderDaoImpl().queryAllOrder().forEach(System.out::println);
    }
}
