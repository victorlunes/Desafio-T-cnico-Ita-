package dev.java10X.ItauJava10X.DTO.Transacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
