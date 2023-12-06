package com.example.dataAnalysisDeputados.controller;

import DAO.*;
import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Despesas;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseDespesas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("despesas")
public class DespesasDeputadoController {
    private final DeputadosController deputadosController;
    public DespesasDeputadoController(DeputadosController deputadosController) {
        this.deputadosController = deputadosController;
    }

    @GetMapping
    public List<Despesas> getDespesasBanco() throws SQLException {
        DespesasDepDAO despesasDepDAO = new DespesaDepImpl();
        List<Despesas> despesaList = despesasDepDAO.getAll();
        return  despesaList;
    }

    public List<Despesas> getDespesasAPI(){
        List<Deputados> deputados = deputadosController.getDeputadosBanco();
        List<Despesas> despesas = new ArrayList<>();
        deputados.forEach(deputado -> {
            String url = String.format("https://dadosabertos.camara.leg.br/api/v2/deputados/%d/despesas?ano=2023&ordem=ASC", deputado.getId());
            RestTemplate template = new RestTemplate();
            ResponseDespesas response = template.getForObject(url, ResponseDespesas.class);

            if (response != null) {
                List<Despesas> despesasDoDeputado = response.getDados();
                despesasDoDeputado.forEach(despesa -> {
                    despesa.setId_deputado(deputado.getId());
                    despesas.add(despesa);
                });
            }

        });

        return despesas;
    }

    @PostMapping
    public List<Despesas> saveDespesas(){
        List<Despesas> despesaList =  getDespesasAPI();
        DespesasDepDAO despesasDepDAO = new DespesaDepImpl();
        ArrayList<Integer> codsProp = new ArrayList<>();

        despesaList.forEach(
                despesa->{

                    try {
                        boolean resp =  codsProp.contains(despesa.getCodDocumento());
                        if(!resp){
                            int result = despesasDepDAO.insert(despesa);
                            codsProp.add(despesa.getCodDocumento());
                        }else{
                            System.out.println("Id já existe");
                        }

                    } catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }
        );
        return despesaList;
    }

}
