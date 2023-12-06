package com.example.dataAnalysisDeputados.DAO;

import com.example.dataAnalysisDeputados.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface ProposicaoDAO extends DAO<Proposicao> {
    public List<ProposicaoDeputado> getProposicaoDeputadoView() throws SQLException;
    public List<ProposicaoPartido> getProposicaoPartidoView() throws SQLException;
}


