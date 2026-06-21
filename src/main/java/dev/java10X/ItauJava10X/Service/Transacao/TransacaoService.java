package dev.java10X.ItauJava10X.Service.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Repository.Transacao.TransacaoRepository;
import dev.java10X.ItauJava10X.Validar.ValidarTransacao.ValidarTransacao;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService extends ValidarTransacao {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void novaTransacao(TransacaoRequest transacao) {
        validarTransacao(transacao);
        transacaoRepository.salvarTransacao(transacao);
    }

    public void deletarTransacao() {
        transacaoRepository.removerTodasTransacoes();
    }

}
