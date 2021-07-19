package ru.IgorDen1973.dto.NegativeClasses;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product7 {
    // СОЗДАЛИ КЛАСС ДЛЯ НЕГАТИВНОГО ТЕСТИРОВАНИЯ, КОГДА НЕТ id
    String title;
    Integer price;
    String categoryTitle;
}
