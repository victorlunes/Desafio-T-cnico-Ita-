package dev.java10X.ItauJava10X.Repository.Transacao;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Model.Estatistica.Estatistica;
import dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties.EstatisticaProperties;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;

@Repository
public class TransacaoRepository {
    private final EstatisticaProperties estatisticaProperties;

    public TransacaoRepository(EstatisticaProperties estatisticaProperties) {
        this.estatisticaProperties = estatisticaProperties;
    }

    List<TransacaoRequest> transacoesLista = new ArrayList<>();

    public void salvarTransacao(TransacaoRequest transacaoRequest) {
        transacoesLista.add(transacaoRequest);
    }

    public void removerTodasTransacoes() {
        transacoesLista.clear();
    }

    public List<EstatisticaDTO> pegarEstatisticaTodasTransacoes() {

        OffsetDateTime inicio = OffsetDateTime.now(ZoneOffset.UTC);

        OffsetDateTime fim = inicio
                .minusSeconds(estatisticaProperties.segundos());

        double count = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .count();

        BigDecimal sum = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avg = count == 0
                ? BigDecimal.ZERO.setScale(3)
                : sum.divide(
                BigDecimal.valueOf(count),
                3,
                RoundingMode.DOWN
        );

        BigDecimal min = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return List.of(new EstatisticaDTO(count, sum, avg, min, max));
    }
}
