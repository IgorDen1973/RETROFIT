package ru.IgorDen1973.dto.NegativeClasses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product6 {
    // СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА "price" ЭТО String (пробел)
    Integer id;
    String title;
    String price;
    String categoryTitle;
}
