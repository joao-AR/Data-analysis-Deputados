package com.example.dataAnalysisDeputados.DAO;

import com.example.dataAnalysisDeputados.entity.DespesaDeputado;
import com.example.dataAnalysisDeputados.entity.DespesaPartido;
import com.example.dataAnalysisDeputados.entity.Despesas;

import java.sql.SQLException;
import java.util.List;

public interface DespesasDAO extends DAO<Despesas> {
    public List<DespesaDeputado> getDespesasDeputadoView() throws SQLException;
    public List<DespesaPartido> getDespesasPartidoView() throws SQLException;
}
