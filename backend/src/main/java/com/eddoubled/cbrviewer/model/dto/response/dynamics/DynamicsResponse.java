package com.eddoubled.cbrviewer.model.dto.response.dynamics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ответ для динамики курсов, отрисовка библиотекой hightchart
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DynamicsResponse {
    /**
     * Код валюты
     */
    String name;

    /**
     * В качестве ключа указывается время (ms), в качестве значения курс относительно рубля
     */
    List<Number[]> data = new ArrayList<>();

    public DynamicsResponse(String name) {
        this.name = name;
    }

    public void addTimeRate(Long time, BigDecimal rate) {
        data.add(new Number[] {time, rate});
    }
}
