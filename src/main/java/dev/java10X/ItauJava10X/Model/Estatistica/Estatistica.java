package dev.java10X.ItauJava10X.Model.Estatistica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estatistica {
    private double count;
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal min;
    private BigDecimal max;
}
