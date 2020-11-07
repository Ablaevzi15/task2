package com.tasks.repositories;

import com.tasks.models.entities.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository  extends JpaRepository<ProductEntity, Long>  {

    @Query( value = "select  p from ProductEntity p join fetch p.priceProducts pp where pp.dateString = :date"
    )
    List<ProductEntity> findAllProductByDate(String date);
}
