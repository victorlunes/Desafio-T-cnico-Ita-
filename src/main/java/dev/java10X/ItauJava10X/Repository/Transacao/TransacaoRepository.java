package dev.java10X.ItauJava10X.Repository.Transacao;

import dev.java10X.ItauJava10X.DTO.Transacao.TransacaoRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    List<TransacaoRequest> transacoesLista = new ArrayList<>();

    public void salvarDados(TransacaoRequest transacaoRequest) {
        transacoesLista.add(transacaoRequest);
    }

    public void limparDados() {

    }

    public void deletarDados() {
        transacoesLista.clear();
    }
}
