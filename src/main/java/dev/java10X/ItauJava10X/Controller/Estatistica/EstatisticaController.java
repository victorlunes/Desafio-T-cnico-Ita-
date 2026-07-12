package dev.java10X.ItauJava10X.Controller.Estatistica;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.Docs.EstatisticaControllerDoc;
import dev.java10X.ItauJava10X.Service.Estatistica.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController implements EstatisticaControllerDoc {

    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public ResponseEntity<List<EstatisticaDTO>> todasEstatisticas() {
        try {
            log.info("Recebida requisicao para buscar estatisticas");
            List<EstatisticaDTO> listaEstatistica = estatisticaService.pegarEstatistica();
            log.info("Estatisticas retornadas com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(listaEstatistica);
        }catch (Exception ex){
            log.error("Erro inesperado ao buscar estatisticas", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
