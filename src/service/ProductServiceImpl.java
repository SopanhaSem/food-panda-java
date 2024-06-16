package service;

import mapper.Mapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entities.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<ProductDto> queryAllProduct() {
        return productDao.queryAllNewProduct()
                .stream()
                .map(Mapper::fromProductDtoToProductDto)
                .toList();
    }

    @Override
    public Product updateProductById(Integer id) {
        Product existingProduct = productDao.searchProductById(id);
        if (existingProduct != null) {
            productDao.updateProductById(existingProduct.getId());
            return existingProduct;
        } else {
            System.out.println("[!] Product not found");
            return null;
        }
    }

    @Override
    public Product deleteProductById(Integer id)
    {
        Product existingProduct = productDao.searchProductById(id);
        if (existingProduct != null) {
            productDao.deleteProductById(id);
            return existingProduct;
        } else {
            System.out.println("[!] Product not found");
            return null;
        }
    }

    @Override
    public void addNewProduct(ProductDto productDto) {
        int id = 4;
        Product product = new Product(++id, productDto.pro_name(),
                productDto.product_code(), productDto.is_deleted(),
                productDto.imported_at(), productDto.expired_at(),
                productDto.product_description());
        productDao.addNewProduct(product);
    }

    @Override
    public ProductDto searchProductById(Integer id) {
        Product product = productDao.searchProductById(id);
        if (product != null) {
            ProductDto productDto = Mapper.fromProductDtoToProductDto(product);
            System.out.println("Product Name: " + productDto.pro_name());
            System.out.println("Product Code: " + productDto.product_code());
            System.out.println("Is Deleted: " + productDto.is_deleted());
            System.out.println("Imported At: " + productDto.imported_at());
            System.out.println("Expired At: " + productDto.expired_at());
            System.out.println("Product Description: " + productDto.product_description());
            return productDto;
        } else {
            System.out.println("[!] Product not found");
            return null;
        }
    }
}
