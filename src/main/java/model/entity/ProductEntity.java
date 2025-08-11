package model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
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
    @UpdateTimestamp
    private LocalDate added_date;
    private String description;



}
