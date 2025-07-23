package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cart")

public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rowcount;
    @UpdateTimestamp
    private LocalDateTime dateTime;
    private Integer cashier;
    private Integer OrderID;
    private String name;
    private Integer productID;
    private Integer qty;
    private Double selling_price;
}



