package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.DAO.*;
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


    public List<Despesas> getDespesasBanco() throws SQLException {
        DespesasDepDAO despesasDepDAO = new DespesaDepImpl();
        List<Despesas> despesaList = despesasDepDAO.getAll();
        return  despesaList;
    }
    @GetMapping
    public List<Despesas> getDespesasAPI(){
        List<Deputados> deputados = deputadosController.getDeputadosBanco();
        List<Despesas> despesas = new ArrayList<>();
        deputados.forEach(deputado -> {
            String url = String.format("https://dadosabertos.camara.leg.br/api/v2/deputados/%d/despesas?ano=2019&ano=2020&ano=2021&ano=2022&ordem=ASC", deputado.getId());
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
                despesa -> {
                    boolean resp =  codsProp.contains(despesa.getCodDocumento());
                    if (despesa.getValorLiquido() < 0){
                        despesa.setValorLiquido(0);
                    }
                    if(!resp){
                        codsProp.add(despesa.getCodDocumento());
                    }else{
                        System.out.println("cod documento já existe");
                    }
                }
        );
        despesaList.forEach(
                despesa->{
                    try {
                        boolean resp = codsProp.contains(despesa.getCodDocumento());
                        if(resp){
                            int result = despesasDepDAO.insert(despesa);
                        }
                    } catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }
        );

        return despesaList;
    }

}
