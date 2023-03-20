package com.eddoubled.cbrviewer.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Записи валют
 */
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Currency implements Serializable {
    String id;
    String name;
    String engName;
    Integer nominal;
    String parentCode;
}
