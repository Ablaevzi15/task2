package com.tasks.models.requests;

import lombok.Data;

import java.util.Map;

@Data
public class StatisticRequest {
    Long countProducts;
    Double changePrice;
    Map<String, Double> nameProductChange;
}
