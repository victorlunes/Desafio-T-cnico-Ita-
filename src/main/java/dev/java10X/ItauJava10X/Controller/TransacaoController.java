package dev.java10X.ItauJava10X.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @PostMapping
    public ResponseEntity adicionar() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
