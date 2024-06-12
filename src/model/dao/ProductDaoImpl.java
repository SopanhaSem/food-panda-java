package model.dao;

import model.entities.Customer;
import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao{
    @Override
    public List<Product> queryAllNewProduct() {
        String sql = """
            SELECT * FROM "product"
            """;
        try(
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda","postgres","asd");
            ResultSet resultSet = connection.createStatement().executeQuery(sql)
        ) {
            List<Product> productList  = new ArrayList<>();
            while(resultSet.next()){
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_code"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("imported_at"),
                        resultSet.getDate("expired_at"),
                        resultSet.getString("product_description")));
            }
            return productList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int updateProductById(Integer id) {
        String sql = """
               UPDATE "product" SET product_name = ?  WHERE id = ?
               """;
        try (  Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/food_panda",
                "postgres","asd"
        );
               PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            Product product = searchProductById(id);
            if (product != null){
                System.out.print("[+] Input product name: ");
                preparedStatement.setString(1,new Scanner(System.in).next());
                preparedStatement.setInt(2,id);
            }
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0 ? "Update Success" : "Update failed";
            System.out.println(message);
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }return  0;
    }

    @Override
    public int deleteProductById(Integer id) {
        String sql = """
                DELETE FROM "product" WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            Product product = searchProductById(id);
            if (product == null){
                System.out.println("[!] Customer not found");
            }else{
                preparedStatement.setInt(1,id);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("[+] Successfully Deleted");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return id;
    }

    @Override
    public void addNewProduct(Product product) {
        String sql = """
                INSERT INTO "product" (product_name,product_code,is_deleted,imported_at,expired_at,product_description)
                VALUES(?,?,?,?,?,?)
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda",
                        "postgres","asd"
                )
        ){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setString(2,product.getProduct_code());
            preparedStatement.setBoolean(3,product.is_deleted());
            preparedStatement.setDate(4,product.getImported_at());
            preparedStatement.setDate(5,product.getExpired_at());
            preparedStatement.setString(6,product.getProduct_description());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0){
                System.out.println("Row Affected..");
            }else{
                System.out.println("Failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product searchProductById(Integer id) {
        String sql = """
                SELECT * FROM "product" WHERE id = ?
                """;
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/food_panda","postgres","asd");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            while (resultSet.next()){
                product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .product_name(resultSet.getString("product_name"))
                        .product_code(resultSet.getString("product_code"))
                        .is_deleted(resultSet.getBoolean("is_deleted"))
                        .imported_at(resultSet.getDate("imported_at"))
                        .expired_at(resultSet.getDate("expired_at"))
                        .product_description(resultSet.getString("product_description"))
                        .build();
            }
            return product;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return  null;
    }
}
