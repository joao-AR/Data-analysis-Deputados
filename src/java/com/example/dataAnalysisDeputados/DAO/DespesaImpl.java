package com.example.dataAnalysisDeputados.DAO;

import com.example.dataAnalysisDeputados.entity.DespesaDeputado;
import com.example.dataAnalysisDeputados.entity.DespesaPartido;
import com.example.dataAnalysisDeputados.entity.Despesas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaImpl implements DespesasDAO {
    @Override
    public Despesas get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Despesas> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.despesa_deputado";

        List<Despesas> despesaList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("cod_documento");
            int idDeputado = rs.getInt("id_deputado");
            int ano = rs.getInt("ano");
            int mes = rs.getInt("mes");
            String tipoDespesa = rs.getString("tipo_despesa");
            float valorLiquedo = rs.getFloat("valor_liquedo");
            Despesas despesa = new Despesas(oid,idDeputado,ano,mes,tipoDespesa,valorLiquedo);
            despesaList.add(despesa);
        }

        return despesaList;
    }

    @Override
    public int save(Despesas employee) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Despesas despesa) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO camara.despesa_deputado (cod_documento,id_deputado,ano,mes,tipo_despesa,valor_liquedo) VALUES(?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,despesa.getCodDocumento());
        ps.setInt(2,despesa.getId_deputado());
        ps.setInt(3, despesa.getAno());
        ps.setInt(4, despesa.getMes());
        ps.setString(5, despesa.getTipoDespesa());
        if(despesa.getValorLiquido() < 0){
            despesa.setValorLiquido(0);
        }
        ps.setFloat(6,despesa.getValorLiquido());

        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    @Override
    public int update(Despesas employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Despesas employee) {
        return 0;
    }

    @Override
    public List<DespesaDeputado> getDespesasDeputadoView() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.despesas_deputados_view";

        List<DespesaDeputado> despesaList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("id_deputado");
            String nome = rs.getString("nome");
            int idPartido = rs.getInt("id_partido");
            String siglaPartido = rs.getString("sigla_partido");
            int qtd = rs.getInt("qtd");
            float total = rs.getFloat("total_gasto");
            DespesaDeputado despesa = new DespesaDeputado(oid,nome,idPartido,siglaPartido,qtd,total);
            despesaList.add(despesa);
        }

        return despesaList;
    }

    @Override
    public List<DespesaPartido> getDespesasPartidoView() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.despesas_partidos_view";

        List<DespesaPartido> despesaList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int idPartido = rs.getInt("id_partido");
            String siglaPartido = rs.getString("sigla");
            float total = rs.getFloat("total_liquido");
            DespesaPartido despesa = new DespesaPartido(idPartido,siglaPartido,total);
            despesaList.add(despesa);
        }

        return despesaList;
    }
}
