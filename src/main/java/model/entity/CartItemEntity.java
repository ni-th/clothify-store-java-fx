package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cart")
public class CartItemEntity {

    private String name;
    @Id
    private Integer id;
    private Integer qty;
    private Double selling_price;
}



