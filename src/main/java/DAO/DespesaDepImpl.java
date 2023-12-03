package DAO;

import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Despesas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDepImpl implements DespesasDepDAO{
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
            Despesas despesa = new Despesas(oid,idDeputado,ano,mes,tipoDespesa);
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
        String sql = "INSERT INTO camara.despesa_deputado (cod_documento,id_deputado,ano,mes,tipo_despesa) VALUES(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,despesa.getCodDocumento());
        ps.setInt(2,despesa.getId_deputado());
        ps.setInt(3, despesa.getAno());
        ps.setInt(4, despesa.getMes());
        ps.setString(5, despesa.getTipoDespesa());

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

}
