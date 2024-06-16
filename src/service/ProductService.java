package service;

import model.dto.ProductDto;
import model.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> queryAllProduct();
    Product updateProductById(Integer id);
    Product deleteProductById(Integer id);
    void addNewProduct(ProductDto productDto);
    ProductDto searchProductById(Integer id);
}
