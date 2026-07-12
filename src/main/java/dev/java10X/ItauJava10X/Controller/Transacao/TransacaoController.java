package dev.java10X.ItauJava10X.Controller.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Docs.TransacaoControllerDoc;
import dev.java10X.ItauJava10X.Service.Transacao.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacaoController implements TransacaoControllerDoc {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity adicionarNovaTransacao(@RequestBody TransacaoRequest transacao) {
        log.info("Recebida requisicao para criar transacao");
        try{
            transacaoService.novaTransacao(transacao);
            log.info("Transacao criada com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException ex){
            log.warn("Transacao rejeitada: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }catch (Exception ex){
            log.error("Erro inesperado ao criar transacao", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity removerTransacao() {
        log.info("Recebida requisicao para remover todas as transacoes");
        try{
            transacaoService.deletarTransacao();
            log.info("Transacoes removidas com sucesso");
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException ex){
            log.warn("Remocao de transacoes rejeitada: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
