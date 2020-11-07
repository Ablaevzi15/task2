package com.tasks.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.repository.query.Param;

@Data
public class ProductRequest {
    Long id;

    String name;

    @JsonProperty("price")
    Long actualPrice;

    String date;
}
