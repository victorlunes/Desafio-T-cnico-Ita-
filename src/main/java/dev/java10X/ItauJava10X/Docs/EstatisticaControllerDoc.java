package dev.java10X.ItauJava10X.Docs;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name="Estatisticas",
        description = "Endpoint responsável em retornar as estatistícas das transações feitas"
)
public interface EstatisticaControllerDoc {

    @Operation(
            summary = "Retornar Estatistíca",
            description = "Recebe a estatistíca das transações que foram feitas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Estatistica retornada com sucesso",
            content = @Content(
                    examples = @ExampleObject(
                            name = "Exemplo de sucesso",
                            value = """
                                    [
                                      {
                                        "count": 150,
                                        "sum": 15320.75,
                                        "avg": 102.14,
                                        "min": 5.00,
                                        "max": 980.30
                                      }
                                    ]
                                    """
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno ao buscar estatisticas"
    )
    ResponseEntity<List<EstatisticaDTO>> todasEstatisticas();
}
