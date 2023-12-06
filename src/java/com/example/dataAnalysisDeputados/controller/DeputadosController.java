package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.DAO.DeputadoDAO;
import com.example.dataAnalysisDeputados.DAO.DeputadoImpl;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseDeputado;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("deputados")
public class DeputadosController {

    private final PartidosController ptdController;
    public DeputadosController(PartidosController ptdController) {
        this.ptdController = ptdController;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Deputados> getDeputadosBanco(){
        DeputadoDAO deputadoDAO = new DeputadoImpl();
        List<Deputados> deputadosList = null;
        try {
            deputadosList = deputadoDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  deputadosList;
    }


    public List<Deputados> getDeputadosAPI() throws SQLException {
        List<Partidos> partidos = ptdController.getPartidosBanco(); // Lista de todos os partidos

        String url = "https://dadosabertos.camara.leg.br/api/v2/deputados?dataInicio=2019-01-01&dataFim=2022-12-31&ordem=ASC&ordenarPor=id";
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
    public List<Deputados>  saveDeputados() throws SQLException {
            List<Deputados> deputados = getDeputadosAPI();
            DeputadoDAO deputadoDAO= new DeputadoImpl();
            ArrayList<Integer> deputadosIds = new ArrayList<>();

            deputados.forEach(
                deputado->{

                    try {
                        boolean resp =  deputadosIds.contains(deputado.getId());
                        if(!resp){
                            int result = deputadoDAO.insert(deputado);
                            deputadosIds.add(deputado.getId());
                        }else{
                            System.out.println("Id já existe");
                            System.out.println(deputado.getId());
                        }

                    } catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }
            );

            return deputados;

    }

}
