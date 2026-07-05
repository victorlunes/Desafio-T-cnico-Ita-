package dev.java10X.ItauJava10X.Service.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Repository.Transacao.TransacaoRepository;
import dev.java10X.ItauJava10X.Validar.ValidarTransacao.ValidarTransacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransacaoService extends ValidarTransacao {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void novaTransacao(TransacaoRequest transacao) {
        log.debug("Validando nova transacao");
        validarTransacao(transacao);
        log.debug("Transacao validada. Salvando no repositorio");
        transacaoRepository.salvarTransacao(transacao);
        log.info("Transacao salva com sucesso");
    }

    public void deletarTransacao() {
        log.debug("Removendo todas as transacoes do repositorio");
        transacaoRepository.removerTodasTransacoes();
        log.info("Todas as transacoes foram removidas");
    }

}
