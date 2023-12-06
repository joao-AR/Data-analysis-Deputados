package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.DAO.PartidoDAO;
import com.example.dataAnalysisDeputados.DAO.PartidoImpl;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("partidos")
public class PartidosController {
    @GetMapping
    public List<Partidos> getPartidosBanco() throws SQLException {
        PartidoDAO partidoDAO = new PartidoImpl();
        List<Partidos> partidoList = partidoDAO.getAll();
        return  partidoList;
    }

    public List<Partidos> getParidosAPI(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/partidos?dataInicio=2019-01-01&itens=1000&ordem=asc&ordenarPor=sigla";
        RestTemplate template = new RestTemplate();
        ResponsePartidos response = template.getForObject(url, ResponsePartidos.class);
        List<Partidos> partidos = response.getDados();
        return partidos;
    }

    @PostMapping
    public List<Partidos> savePartidos(){
        List<Partidos> partidos =  getParidosAPI();
        PartidoDAO partidoDAO = new PartidoImpl();
        partidos.forEach(
                partido->{
                    try {
                        int result = partidoDAO.insert(partido);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return partidos;
    }


}
