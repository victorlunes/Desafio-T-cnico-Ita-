package dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "estatistica")
public record EstatisticaProperties(Integer segundos) {}