package ru.IgorDen1973.dto.NegativeClasses;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
// СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА "title" ЭТО Boolean
public class Product3 {
    Integer id;
    Boolean title;
    Integer price;
    String categoryTitle;

}