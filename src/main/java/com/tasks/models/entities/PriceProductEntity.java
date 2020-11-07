package com.tasks.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "price_product")
public class PriceProductEntity {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "price")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long price;

    @Column(name = "date_string")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String dateString;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "date")
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalDateTime date;

}
