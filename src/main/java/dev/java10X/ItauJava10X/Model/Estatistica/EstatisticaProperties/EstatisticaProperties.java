package dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "estatistica")
public record EstatisticaProperties(
        @NotNull
        Integer segundos
) {
        public EstatisticaProperties {
                if (segundos < 60) {
                        throw new IllegalArgumentException("Segundos deve ser maior que 60");
                }
        }
}