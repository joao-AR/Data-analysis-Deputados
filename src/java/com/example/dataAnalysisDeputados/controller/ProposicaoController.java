package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.DAO.ProposicaoDAO;
import com.example.dataAnalysisDeputados.DAO.ProposicaoImpl;

import com.example.dataAnalysisDeputados.entity.*;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseProposicao;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProposicaoController {

    private final AutorProposicaoController autorProposicaoController;
    private final DeputadosController deputadosController;

    public ProposicaoController(AutorProposicaoController autorProposicaoController,DeputadosController deputadosController) {
        this.autorProposicaoController = autorProposicaoController;
        this.deputadosController = deputadosController;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("proposicao-deputado")
    public List<ProposicaoDeputado> getProposicaoDeputadosView(){

        ProposicaoDAO proposicaoDAO = new ProposicaoImpl();
        List<ProposicaoDeputado> proposicaoList = null;
        try {
            proposicaoList = proposicaoDAO.getProposicaoDeputadoView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  proposicaoList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("proposicao-partido")
    public List<ProposicaoPartido> getProposicaoPartidoView(){

        ProposicaoDAO proposicaoDAO = new ProposicaoImpl();
        List<ProposicaoPartido> proposicaoList = null;
        try {
            proposicaoList = proposicaoDAO.getProposicaoPartidoView();
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("proposicao")
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
