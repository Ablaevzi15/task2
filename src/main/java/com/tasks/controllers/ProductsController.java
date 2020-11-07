package com.tasks.controllers;

import com.tasks.models.requests.ProductRequest;
import com.tasks.models.requests.StatisticRequest;
import com.tasks.services.ProductsServices;
import com.tasks.services.parser.ParseCsvServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsServices productsServices;
    private final ParseCsvServicesImpl parseCsvServices;

    @GetMapping
    public List<ProductRequest> getProducts(@RequestBody String dateString) {
        return productsServices.getProductsByDate(dateString);
    }

    @PostMapping("/parse")
    public String parse() {
        parseCsvServices.readAllDataAtOnce();
        return "parse success";
    }

    @GetMapping("/statistic")
    public StatisticRequest getStatistic(){
        return productsServices.getStatistic();
    }

}
