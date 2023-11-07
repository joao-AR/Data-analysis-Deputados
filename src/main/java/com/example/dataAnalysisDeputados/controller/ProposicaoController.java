package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Proposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseAutoresProposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseProposicao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProposicaoController {
//    private final AutorProposicaoController autorProposicaoController;
//    private final DeputadosController deputadosController;
//
//    public ProposicaoController(AutorProposicaoController autorProposicaoController,DeputadosController deputadosController) {
//        this.autorProposicaoController = autorProposicaoController;
//        this.deputadosController = deputadosController;
//    }


//    @GetMapping("/proposicao")
//    public List<Proposicao> getProposicao(){
//
//        List<Deputados> deputados = deputadosController.getDeputadosList();
//
//        String url = "https://dadosabertos.camara.leg.br/api/v2/proposicoes?ano=2023&ordem=ASC&ordenarPor=ano";
//        RestTemplate template = new RestTemplate();
//        ResponseProposicao response = template.getForObject(url, ResponseProposicao.class);
//        List<Proposicao> proposicao = response.getDados();
//        // colocando os IDS dos deputados em cada proposicao
//        proposicao.forEach(prop ->{
//            List<AutorProposicao> autores = autorProposicaoController.getAutoresP(prop.getId());
//            autores.forEach(aut ->{
//                deputados.forEach(deputado ->{
//                    String nomeDeputado = deputado.getNome();
//                    String nomeAutor = aut.getNome();
//                    if( nomeAutor.equals(nomeDeputado)){
//                        prop.setId_deputado(deputado.getId());
//                    }
//                });
//            });
//        });
//
//        // Remover proposições que não foram feitas por um Deputado, ex: proposição fetia pelo poder Executivo
//        proposicao.removeIf(prop -> prop.getId_deputado() == 0);
//        return proposicao;
//    }
}
