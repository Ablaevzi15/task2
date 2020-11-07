package com.tasks.services;

import com.tasks.models.entities.ProductEntity;
import com.tasks.models.requests.ProductRequest;
import com.tasks.models.entities.PriceProductEntity;
import com.tasks.models.requests.StatisticRequest;
import com.tasks.repositories.PriceProductRepository;
import com.tasks.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.tasks.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsServicesImpl implements ProductsServices {

    private final ProductRepository productRepository;
    private final PriceProductRepository priceProductRepository;
    private final DateUtils dateUtils;

    @Override
    public List<ProductRequest> getProductsByDate(String date) {
        List<PriceProductEntity> products = priceProductRepository.findAllPriceProductByDate(date);
        return products.stream().map(priceProductEntity -> {
            ProductRequest productRequest = new ProductRequest();
            productRequest.setId(priceProductEntity.getProduct().getId());
            productRequest.setName(priceProductEntity.getProduct().getName());
            productRequest.setActualPrice(priceProductEntity.getPrice());
            productRequest.setDate(priceProductEntity.getDateString());
            return productRequest;
        }).collect(Collectors.toList());
    }

    @Override
    public StatisticRequest getStatistic() {
        List<PriceProductEntity> priceProductEntities = priceProductRepository.getAllPriceProduct();
        List<ProductEntity> productEntityList = priceProductEntities.stream().map(PriceProductEntity::getProduct).collect(Collectors.toList());

        List<Double> count = new ArrayList<>();
        Map<String, Double> productChancePrices = new HashMap<>();

        Map<String, List<LocalDateTime>> allChangesForProduct = parseAllChangesForProduct(priceProductEntities);
        averageChangeByProduct(allChangesForProduct, count, productChancePrices);
        Double asDouble = count.stream().mapToDouble(a -> a).average().orElse(0.0);

        StatisticRequest statisticRequest = new StatisticRequest();
        statisticRequest.setCountProducts((long) productEntityList.size());
        statisticRequest.setNameProductChange(productChancePrices);
        statisticRequest.setChangePrice(asDouble);

        return statisticRequest;
    }

    private Map<String, List<LocalDateTime>> parseAllChangesForProduct(List<PriceProductEntity> priceProductEntities) {
        Map<String, List<LocalDateTime>> allChangesForProduct = new HashMap<>();
        priceProductEntities.forEach(priceProductEntity -> {
            String name = priceProductEntity.getProduct().getName();
            if (allChangesForProduct.get(name) == null) {
                List<LocalDateTime> dateTimes = new ArrayList<>();
                dateTimes.add(priceProductEntity.getDate());
                allChangesForProduct.put(name, dateTimes);
            } else {
                List<LocalDateTime> dateTimeList = allChangesForProduct.get(name);
                dateTimeList.add(priceProductEntity.getDate());
                allChangesForProduct.put(name, dateTimeList);
            }
        });
        return allChangesForProduct;
    }

    private void averageChangeByProduct(Map<String, List<LocalDateTime>> temp, List<Double> count, Map<String, Double> productChancePrices) {
        for (Map.Entry<String, List<LocalDateTime>> entry : temp.entrySet()) {
            String name = entry.getKey();
            List<LocalDateTime> localDateTimes = entry.getValue();
            Collections.sort(localDateTimes);
            LocalDateTime firstDate = localDateTimes.get(0);
            LocalDateTime lastDate = localDateTimes.get(localDateTimes.size() - 1);
            Long between = dateUtils.dateBetween(firstDate, lastDate);
            if (between == 0 && localDateTimes.size() == 1) {
                continue;
            }

            Double populate = (double) localDateTimes.size() / (double) (between + 1);
            count.add(populate);
            productChancePrices.put(name, populate);
        }
    }

}
