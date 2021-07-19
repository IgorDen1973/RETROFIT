package ru.IgorDen1973.dto.NegativeClasses;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
// СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА "title" ЭТО ЧИСЛО
public class Product2 {
    Integer id;
    Integer title;
    Integer price;
    String categoryTitle;
    }
