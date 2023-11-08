package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.Interface.PartidoRepository;
import com.example.dataAnalysisDeputados.Interface.ProposicaoRepository;
import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Proposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseAutoresProposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseProposicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("proposicao")
public class ProposicaoController {

    @Autowired
    private ProposicaoRepository repository;
    private final AutorProposicaoController autorProposicaoController;
    private final DeputadosController deputadosController;

    public ProposicaoController(AutorProposicaoController autorProposicaoController,DeputadosController deputadosController) {
        this.autorProposicaoController = autorProposicaoController;
        this.deputadosController = deputadosController;
    }



    public List<Proposicao> getProposicaoBanco(){
        List<Proposicao> proposicaoList = repository.findAll().stream().toList();
        return  proposicaoList;
    }

    public List<Proposicao> getProposicaoAPI(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/proposicoes?ano=2019&ano=2020&ano=2021&ano=2022&itens=100000&ordem=ASC&ordenarPor=id";
        RestTemplate template = new RestTemplate();
        ResponseProposicao response = template.getForObject(url, ResponseProposicao.class);
        List<Proposicao> proposicao = response.getDados();
        return proposicao;
    }

    @PostMapping
    public List<Proposicao> saveProposicao(){
        List<Proposicao> proposicoes = getProposicaoAPI();

        List<Deputados> deputados = deputadosController.getDeputadosAPI();

        //colocando os IDS dos deputados em cada proposicao
        proposicoes.forEach(prop ->{
            List<AutorProposicao> autores = autorProposicaoController.getAutoresP(prop.getId());
            autores.forEach(aut ->{
                deputados.forEach(deputado ->{
                    String nomeDeputado = deputado.getNome();
                    String nomeAutor = aut.getNome();
                    if( nomeAutor.equals(nomeDeputado)){
                        prop.setId_deputado(deputado.getId());
                    }
                });
            });
        });

        // Remover proposições que não foram feitas por um Deputado, ex: proposição fetia pelo poder Executivo
        proposicoes.removeIf(prop -> prop.getId_deputado() == 0);
        repository.saveAll(proposicoes);
        return proposicoes;
    }


}
