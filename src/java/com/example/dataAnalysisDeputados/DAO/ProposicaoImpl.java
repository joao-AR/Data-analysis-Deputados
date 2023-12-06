package com.example.dataAnalysisDeputados.DAO;

import com.example.dataAnalysisDeputados.entity.Proposicao;
import com.example.dataAnalysisDeputados.entity.ProposicaoDeputado;
import com.example.dataAnalysisDeputados.entity.ProposicaoPartido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProposicaoImpl implements ProposicaoDAO{

    @Override
    public Proposicao get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Proposicao> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.proposicao";

        List<Proposicao> proposicaoList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("id");
            int idDeputado = rs.getInt("id_deputado");
            int codTipo = rs.getInt("cod_tipo");
            int ano = rs.getInt("ano");
            String ementa = rs.getString("ementa");
            Proposicao proposicao = new Proposicao(oid,idDeputado,codTipo,ano,ementa);
            proposicaoList.add(proposicao);
        }

        return proposicaoList;
    }

    @Override
    public int save(Proposicao employee) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Proposicao proposicao) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO camara.proposicao (id,id_deputado,cod_tipo,ano,ementa) VALUES(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,proposicao.getId());
        ps.setInt(2,proposicao.getId_deputado());
        ps.setInt(3, proposicao.getCodTipo());
        ps.setInt(4, proposicao.getAno());
        ps.setString(5, proposicao.getEmenta());

        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    @Override
    public int update(Proposicao employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Proposicao employee) {
        return 0;
    }

    @Override
    public List<ProposicaoDeputado> getProposicaoDeputadoView() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.proposicao_deputado_view";

        List<ProposicaoDeputado> proposicaoList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int idDeputado = rs.getInt("id_deputado");
            String nome = rs.getString("nome");
            int qtd = rs.getInt("qtd_prop");
            ProposicaoDeputado proposicao = new ProposicaoDeputado(idDeputado,nome,qtd);
            proposicaoList.add(proposicao);
        }

        return proposicaoList;
    }

    @Override
    public List<ProposicaoPartido> getProposicaoPartidoView() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.proposicao_partido_view";

        List<ProposicaoPartido> proposicaoList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int idPartido = rs.getInt("id_partido");
            String sigla = rs.getString("sigla");
            int qtd = rs.getInt("qtd_prop");
            ProposicaoPartido proposicao = new ProposicaoPartido(idPartido,sigla,qtd);
            proposicaoList.add(proposicao);
        }

        return proposicaoList;
    }
}
