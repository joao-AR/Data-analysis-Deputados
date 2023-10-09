package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseDeputado;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeputadosController {

    private final PartidosController ptdController;
    public DeputadosController(PartidosController ptdController) {
        this.ptdController = ptdController;
    }

    @GetMapping("/deputados")
    public List<Deputados> getDeputadosList(){
        List<Partidos> partidos = ptdController.getPartidos(); // Lista de todos os partidos

        String url = "https://dadosabertos.camara.leg.br/api/v2/deputados?itens=700&dataInicio=2023-01-01&ordem=ASC&ordenarPor=id";
        RestTemplate template = new RestTemplate();
        ResponseDeputado response = template.getForObject(url, ResponseDeputado.class);
        List<Deputados> deputados = response.getDados(); // Lista de deputados

        // obter o ID do partido e adicionar ao deputado
        deputados.forEach(deputado ->{
                partidos.forEach(partido->{
                    String siglaPtdDeputado = deputado.getSiglaPartido();
                    String siglaPTD = partido.getSigla();
                    if( siglaPtdDeputado.equals(siglaPTD)){
                        deputado.setId_partido(partido.getId());
                    }
                });
        });

        return deputados;
    }
}
