package controller;

import model.dto.ProductDto;
import model.entities.Product;
import service.ProductService;
import service.ProductServiceImpl;

import java.util.List;

public class ProductController {
    ProductService productService = new ProductServiceImpl();
    public void addNewProduct(ProductDto productDto){productService.addNewProduct(productDto);}
    public List<ProductDto> queryAllProduct(){return productService.queryAllProduct();}
    public void searchProductById(Integer id) {
         productService.searchProductById(id);
    }
    public void deleteProduct(Integer id){productService.deleteProductById(id);}
    public void updateProduct(Integer id){productService.updateProductById(id);}
}
