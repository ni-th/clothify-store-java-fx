package model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {
    private Integer id;
    private String itemCode;
    private String name;
    private String category;
    private String color;
    private String size;
    private String image;
    private Integer qty;
    private Double cost_price;
    private Double selling_price;
    private String supplier;
    private LocalDate added_date;
    private String description;



}
