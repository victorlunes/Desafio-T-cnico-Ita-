package dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "estatistica")
public record EstatisticaProperties(
        @NotNull
        @Positive
        Integer segundos
) {
}