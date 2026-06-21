package dev.java10X.ItauJava10X.Controller.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Service.Transacao.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity adicionarNovaTransacao(@RequestBody TransacaoRequest transacao) {
        try{
            transacaoService.novaTransacao(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity removerTransacao() {
        try{
            transacaoService.deletarTransacao();
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}