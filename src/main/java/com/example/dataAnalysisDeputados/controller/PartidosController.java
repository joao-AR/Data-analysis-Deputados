package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.Interface.PartidoRepository;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("partidos")
public class PartidosController {

    @Autowired
    private PartidoRepository repository;
    @GetMapping
    public List<Partidos> getPartidosBanco(){
        List<Partidos> partidoList = repository.findAll().stream().toList();
        return  partidoList;
    }


    public List<Partidos> getParidosAPI(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/partidos?itens=1000&ordem=ASC&ordenarPor=id";
        RestTemplate template = new RestTemplate();
        ResponsePartidos response = template.getForObject(url, ResponsePartidos.class);
        List<Partidos> partidos = response.getDados();
        return partidos;
    }

    @PostMapping
    public List<Partidos> savePartidos(){
        List<Partidos> partidos =  getParidosAPI();
        repository.saveAll(partidos);
        return partidos;
    }


}
