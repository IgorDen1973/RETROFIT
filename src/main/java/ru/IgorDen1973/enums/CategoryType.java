package ru.IgorDen1973.enums;

import lombok.Getter;

public enum CategoryType {
    Food("Food", 1),
    Electronic("Electronic", 2),
    Furniture("Furniture", 3);

    @Getter
    private final String title;
    @Getter
    private final Integer id;

    CategoryType(String title, Integer id) {
        this.title = title;
        this.id = id;
    }
}