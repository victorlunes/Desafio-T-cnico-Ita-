package dev.java10X.ItauJava10X.Repository.Transacao;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Model.Estatistica.Estatistica;
import dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties.EstatisticaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class TransacaoRepository {
    private final EstatisticaProperties estatisticaProperties;

    public TransacaoRepository(EstatisticaProperties estatisticaProperties) {
        this.estatisticaProperties = estatisticaProperties;
    }

    List<TransacaoRequest> transacoesLista = new ArrayList<>();

    public void salvarTransacao(TransacaoRequest transacaoRequest) {
        transacoesLista.add(transacaoRequest);
        log.debug("Transacao armazenada. Total em memoria: {}", transacoesLista.size());
    }

    public void removerTodasTransacoes() {
        int totalTransacoes = transacoesLista.size();
        transacoesLista.clear();
        log.debug("Transacoes removidas do repositorio. Total removido: {}", totalTransacoes);
    }

    public List<EstatisticaDTO> pegarEstatisticaTodasTransacoes() {
        log.trace("Iniciando processamento de retorno das estatisticas");

        OffsetDateTime inicio = OffsetDateTime.now(ZoneOffset.UTC);

        OffsetDateTime fim = inicio
                .minusSeconds(estatisticaProperties.segundos());

        log.debug("A busca vai ser entre {} e {}, frame de {} segundos.", inicio, fim, estatisticaProperties.segundos());

        double count = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .count();

        if (count <= 0) {
            log.warn("Não temos transações efetuadas nos ultimos {} segundos", estatisticaProperties.segundos());
            return List.of(new EstatisticaDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
        }

        log.debug("Quantidade de transacoes: {}", count);

        BigDecimal sum = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.debug("Soma de transacoes: {}", sum);

        BigDecimal avg = count == 0
                ? BigDecimal.ZERO.setScale(3)
                : sum.divide(
                BigDecimal.valueOf(count),
                3,
                RoundingMode.DOWN
        );

        log.debug("Média das transações: {}", avg);

        BigDecimal min = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        log.debug("Valor minimo de transacoes: {}", min);

        BigDecimal max = transacoesLista.stream()
                .filter(data -> data.getDataHora().isAfter(fim) && data.getDataHora().isBefore(inicio))
                .map(transacao -> transacao.getValor())
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        log.debug("Valor maximo das transações: {}", max);


        log.info("Estatistica criada: | Quantidade: {} | Soma: {} | Média: {} | Minimo: {} | Maximo: {}",
                count, sum, avg, min, max);

        return List.of(new EstatisticaDTO(count, sum, avg, min, max));
    }
}
