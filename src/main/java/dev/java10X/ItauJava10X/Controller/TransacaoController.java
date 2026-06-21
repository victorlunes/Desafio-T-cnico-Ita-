package dev.java10X.ItauJava10X.Controller;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import dev.java10X.ItauJava10X.Repository.Transacao.TransacaoRepository;
import dev.java10X.ItauJava10X.Service.Transacao.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;
    private final TransacaoRepository transacaoRepository;

    public TransacaoController(TransacaoService transacaoService,  TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    public ResponseEntity adicionar(@RequestBody TransacaoRequest transacao) {
        try{
            transacaoService.validarTransacao(transacao);
            transacaoRepository.salvarDados(transacao);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletar() {
        try{
            transacaoRepository.deletarDados();

            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
