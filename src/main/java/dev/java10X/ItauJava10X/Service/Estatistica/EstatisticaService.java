package dev.java10X.ItauJava10X.Service.Estatistica;

import dev.java10X.ItauJava10X.DTO.Estatistica.EstatisticaDTO;
import dev.java10X.ItauJava10X.Repository.Transacao.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {

    private final TransacaoRepository transacaoRepository;

    public EstatisticaService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<EstatisticaDTO> pegarEstatistica() {
         return transacaoRepository.pegarEstatisticaTodasTransacoes();
    }
}
