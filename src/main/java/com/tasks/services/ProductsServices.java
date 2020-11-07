package com.tasks.services;

import com.tasks.models.requests.ProductRequest;
import com.tasks.models.requests.StatisticRequest;

import java.util.List;

public interface ProductsServices {
    List<ProductRequest> getProductsByDate(String date);

    StatisticRequest getStatistic();
}
