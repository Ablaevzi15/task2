package com.tasks.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Comparable<ProductEntity> {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name = "";

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<PriceProductEntity> priceProducts = new ArrayList<>();

    @Override
    public int compareTo(ProductEntity o) {
        return 0;
    }
}
