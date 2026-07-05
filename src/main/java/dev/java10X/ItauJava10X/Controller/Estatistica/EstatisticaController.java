package dev.java10X.ItauJava10X.Controller.Estatistica;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.Model.Estatistica.EstatisticaProperties.EstatisticaProperties;
import dev.java10X.ItauJava10X.Service.Estatistica.EstatisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public ResponseEntity<List<EstatisticaDTO>> todasEstatisticas() {
        List<EstatisticaDTO> listaEstatistica = estatisticaService.pegarEstatistica();
        return ResponseEntity.status(HttpStatus.OK).body(listaEstatistica);
    }
}
