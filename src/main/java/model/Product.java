package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    private String product_id;
    private String product_name;
    private String category;
    private String color;
    private String size;
    private String image;
    private Integer qty;
    private Double cost_price;
    private Double selling_price;
    private String supplier;
    private String added_date;
    private String description;



}
