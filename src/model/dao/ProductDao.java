package model.dao;

import model.entities.Customer;
import model.entities.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllNewProduct();
    int updateProductById(Integer id);
    int deleteProductById(Integer id);
    void addNewProduct(Product product);
    Product searchProductById(Integer id);
}
