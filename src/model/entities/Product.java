package model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String product_name;
    private String product_code;
    private boolean is_deleted;
    private Date imported_at;
    private Date expired_at;
    private String product_description;
}
