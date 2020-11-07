package com.tasks.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter userDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter userDateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDateTime parseDate(String date) {
        return LocalDate.parse(date, userDateFormatter).atStartOfDay().atOffset(ZoneOffset.UTC).toLocalDateTime();
    }

    public Long dateBetween(LocalDateTime startDate, LocalDateTime finishDate) {
        Duration duration = Duration.between(startDate, finishDate);
        return Math.abs(duration.toDays());
    }

}