package ru.IgorDen1973.dto.NegativeClasses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product5 {
    // СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА "price" ЭТО double
    Integer id;
    String title;
    Double price;
    String categoryTitle;
}
