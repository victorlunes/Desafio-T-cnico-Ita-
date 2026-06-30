package dev.java10X.ItauJava10X.Repository.Transacao;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Model.Estatistica.Estatistica;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    List<TransacaoRequest> transacoesLista = new ArrayList<>();

    public void salvarTransacao(TransacaoRequest transacaoRequest) {
        transacoesLista.add(transacaoRequest);
    }

    public void removerTodasTransacoes() {
        transacoesLista.clear();
    }

    public List<EstatisticaDTO> pegarEstatisticaTodasTransacoes() {

        List<EstatisticaDTO> listaEstatistica = new ArrayList<>();

        OffsetDateTime inicio = OffsetDateTime.now(ZoneOffset.UTC);

        OffsetDateTime fim = OffsetDateTime.now(ZoneOffset.UTC).minusMinutes(5);

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

        listaEstatistica.add(new EstatisticaDTO(count, sum, avg, min, max));

        return listaEstatistica;
    }
}
