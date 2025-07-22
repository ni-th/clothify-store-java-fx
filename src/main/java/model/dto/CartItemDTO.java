package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartItemDTO {
    private Integer OrderID;
    private String name;
    private Integer productID;
    private Integer qty;
    private Double selling_price;
}



