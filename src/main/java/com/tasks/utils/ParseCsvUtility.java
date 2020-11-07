package com.tasks.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.tasks.general.ConfigParser;
import com.tasks.models.ProductCSV;
import com.tasks.models.entities.PriceProductEntity;
import com.tasks.models.entities.ProductEntity;
import com.tasks.repositories.PriceProductRepository;
import com.tasks.repositories.ProductRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class ParseCsvUtility {

    private final ConfigParser configParser;

    public List<ProductCSV> parseCSV() {
        Path myPath = Paths.get(configParser.CSV_PATH);
        List<ProductCSV> productCSV = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(myPath, StandardCharsets.UTF_8)) {
            log.info("Parse product from csv file by path= " + configParser.CSV_PATH);
            HeaderColumnNameMappingStrategy<ProductCSV> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ProductCSV.class);
            CsvToBean<ProductCSV> csvToBean = new CsvToBeanBuilder<ProductCSV>(bufferedReader)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true).build();

            productCSV = csvToBean.parse();
            log.info("Parse successfully! Count of uploaded products = " + productCSV.size());
        } catch (IOException e) {
            log.error("error in csv parser, cant read csv file = ", e);
        }
        return productCSV;
    }

}
