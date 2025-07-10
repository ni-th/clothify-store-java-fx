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
    private String name;
    private Integer id;
    private Integer qty;
    private Double selling_price;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}



