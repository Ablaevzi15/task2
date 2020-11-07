package com.tasks.services.parser;

import com.tasks.models.ProductCSV;
import com.tasks.models.entities.PriceProductEntity;
import com.tasks.models.entities.ProductEntity;
import com.tasks.repositories.PriceProductRepository;
import com.tasks.repositories.ProductRepository;
import com.tasks.utils.DateUtils;
import com.tasks.utils.ParseCsvUtility;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class ParseCsvServicesImpl  implements ParseCsvServices {

    private final PriceProductRepository priceProductRepository;
    private final ProductRepository productRepository;
    private final ParseCsvUtility parseCSV;
    private final DateUtils dateUtils;

    @Transactional
    @Override
    public void readAllDataAtOnce() {
        List<ProductCSV> productCSVs = parseCSV.parseCSV();
        List<PriceProductEntity> priceProductEntities = new ArrayList<>();
        for (ProductCSV productCSV : productCSVs) {
            ProductEntity productEntity = productRepository.findById(productCSV.getProduct_id()).orElseGet(ProductEntity::new);
            PriceProductEntity priceProductEntity = new PriceProductEntity();

            if(productEntity.getId() == null ) {
                productEntity.setId(productCSV.getProduct_id());
                productEntity.setName(productCSV.getProduct_name());
                productEntity = productRepository.save(productEntity);
            }
            priceProductEntity.setId(productCSV.getPrice_id());
            priceProductEntity.setPrice(productCSV.getPrice());
            priceProductEntity.setDateString(productCSV.getPrice_date());
            priceProductEntity.setDate(dateUtils.parseDate(productCSV.getPrice_date()));
            priceProductEntity.setProduct(productEntity);
            priceProductEntities.add(priceProductEntity);
        }
        priceProductRepository.saveAll(priceProductEntities);
    }
}
