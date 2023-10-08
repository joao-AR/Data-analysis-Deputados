package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Despesas;
import com.example.dataAnalysisDeputados.entity.ResponseDespesas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class DespesasDeputadoController {
    private final DeputadosController deputadosController;
    public DespesasDeputadoController(DeputadosController deputadosController) {
        this.deputadosController = deputadosController;
    }

    @GetMapping("/despesas")
    public List<Despesas> getDespesas(){
        List<Deputados> deputados = deputadosController.getDeputadosList();
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

}
