package dev.java10X.ItauJava10X.Service.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    public void validarTransacao(TransacaoRequest transacao) {
        if (transacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: transações devem ter valores maiores que zero");
        }

        if (transacao.getValor() == null) {
            throw new IllegalArgumentException("Erro: transações devem ter valores que não sejam nulos");
        }

        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: Erro na data da transação");
        }

        if (transacao.getDataHora() == null) {
            throw new IllegalArgumentException("Erro: Erro na data da transação vindo como nula");
        }
    }

}
