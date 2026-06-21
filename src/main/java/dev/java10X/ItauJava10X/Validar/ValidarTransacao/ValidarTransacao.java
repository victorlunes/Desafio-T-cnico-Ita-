package dev.java10X.ItauJava10X.Validar.ValidarTransacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ValidarTransacao {
    public void validarTransacao(TransacaoRequest transacao) {
        if (transacao == null) {
            throw new IllegalArgumentException("Erro: a transação não pode ser nula");
        }

        if (transacao.getValor() == null) {
            throw new IllegalArgumentException("Erro: o valor da transação não pode ser nulo");
        }

        if (transacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: a transação não pode ter valor negativo");
        }

        if (transacao.getDataHora() == null) {
            throw new IllegalArgumentException("Erro: a data da transação não pode ser nula");
        }

        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: a data da transação não pode estar no futuro");
        }
    }
}
