package com.tasks.repositories;

import com.tasks.models.entities.PriceProductEntity;
import com.tasks.models.entities.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceProductRepository extends JpaRepository<PriceProductEntity, Long> {


    @Query( value = "select  pp from PriceProductEntity pp join fetch pp.product p where pp.dateString = :date"
    )
    List<PriceProductEntity> findAllPriceProductByDate(String date);

    @Query(value = "select pp from PriceProductEntity pp")
    List<PriceProductEntity> getAllPriceProduct();
}
