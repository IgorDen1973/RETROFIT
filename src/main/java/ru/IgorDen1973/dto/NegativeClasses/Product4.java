package ru.IgorDen1973.dto.NegativeClasses;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product4 {


// СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА "title" ЭТО double
    Integer id;
    double title;
    Integer price;
    String categoryTitle;
}
