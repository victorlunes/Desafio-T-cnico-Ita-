package dev.java10X.ItauJava10X.Service.Estatistica;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.Repository.Transacao.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EstatisticaService {

    private final TransacaoRepository transacaoRepository;
    
    public EstatisticaService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<EstatisticaDTO> pegarEstatistica() {
        log.debug("Buscando estatisticas das transacoes");
        List<EstatisticaDTO> estatisticas = transacaoRepository.pegarEstatisticaTodasTransacoes();
        log.debug("Estatisticas calculadas. Total de registros: {}", estatisticas.size());
        return estatisticas;
    }
}
