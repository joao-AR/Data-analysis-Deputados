package com.example.dataAnalysisDeputados.controller;

import DAO.PartidoDAO;
import DAO.PartidoImpl;
import DAO.TipoPropDAO;
import DAO.TipoPropImpl;
import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Despesas;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseTipoProposicao;
import com.example.dataAnalysisDeputados.entity.TipoProposicao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("tipoProposicao")
public class TipoProposicaoController {

    @GetMapping
    public List<TipoProposicao> getTipoPropBanco() throws SQLException {
        TipoPropDAO tipoPropDAO = new TipoPropImpl();
        List<TipoProposicao> TipoPropList = tipoPropDAO.getAll();
        return  TipoPropList;
    }

    public List<TipoProposicao> getTipoPropAPI(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/referencias/tiposProposicao";
        RestTemplate template = new RestTemplate();
        ResponseTipoProposicao response = template.getForObject(url, ResponseTipoProposicao.class);
        List<TipoProposicao> tipoProp = response.getDados();
        return tipoProp;
    }

    @PostMapping
    public List<TipoProposicao> saveTipoProp(){
        List<TipoProposicao> tipoProposicaosList =  getTipoPropAPI();
        TipoPropDAO tipoPropDAO = new TipoPropImpl();
        tipoProposicaosList.forEach(
                tipoProposicao->{
                    try {
                        int result = tipoPropDAO.insert(tipoProposicao);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return tipoProposicaosList;
    }

}
