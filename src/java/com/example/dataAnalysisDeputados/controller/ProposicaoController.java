package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.DAO.ProposicaoDAO;
import com.example.dataAnalysisDeputados.DAO.ProposicaoImpl;

import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Proposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseProposicao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("proposicao")
public class ProposicaoController {

    private final AutorProposicaoController autorProposicaoController;
    private final DeputadosController deputadosController;

    public ProposicaoController(AutorProposicaoController autorProposicaoController,DeputadosController deputadosController) {
        this.autorProposicaoController = autorProposicaoController;
        this.deputadosController = deputadosController;
    }


    @GetMapping
    public List<Proposicao> getProposicaoBanco(){

        ProposicaoDAO proposicaoDAO = new ProposicaoImpl();
        List<Proposicao> proposicaoList = null;
        try {
            proposicaoList = proposicaoDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public List<Proposicao> saveProposicao() throws SQLException {
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

        ProposicaoDAO proposicaoDAO = new ProposicaoImpl();

        proposicoes.forEach(
                prop->{
                    try {
                        if(prop.getId_deputado() != 0 ){
                            int result = proposicaoDAO.insert(prop);
                        }
                    } catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }

        );
        return proposicoes;
    }


}
