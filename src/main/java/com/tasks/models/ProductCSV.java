package com.tasks.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductCSV {

    @CsvBindByName
    public Long product_id;
    @CsvBindByName
    public String product_name;
    @CsvBindByName
    public Long price_id;
    @CsvBindByName
    public Long price;
    @CsvBindByName
    public String price_date;
}
