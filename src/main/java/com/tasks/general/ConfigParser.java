package com.tasks.general;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:parser.properties", encoding = "UTF-8")
public class ConfigParser {

    @Value("${file.path}")
    public String CSV_PATH;


}
