package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.Interface.DeputadoRepository;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseDeputado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("deputados")
public class DeputadosController {

    private final PartidosController ptdController;
    public DeputadosController(PartidosController ptdController) {
        this.ptdController = ptdController;
    }

    @Autowired
    DeputadoRepository repository;

    @GetMapping
    public List<Deputados> getDeputadosBanco(){
        List<Deputados> deputadoList = repository.findAll().stream().toList();
        return  deputadoList;
    }


    public List<Deputados> getDeputadosAPI(){
        List<Partidos> partidos = ptdController.getPartidosBanco(); // Lista de todos os partidos

        String url = "https://dadosabertos.camara.leg.br/api/v2/deputados?itens=10000&dataInicio=2019-01-01&ordem=ASC&ordenarPor=id";
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

    @PostMapping
    public List<Deputados>  saveDeputados(){
            List<Deputados> deputados =  getDeputadosAPI();
            repository.saveAll(deputados);
            return deputados;
    }

}
