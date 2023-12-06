package com.example.dataAnalysisDeputados.DAO;

import com.example.dataAnalysisDeputados.entity.Deputados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeputadoImpl implements DeputadoDAO{
    @Override
    public Deputados get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Deputados deputado = null;

        String sql = "Select * from  camara.deputado where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("id");
            int idp = rs.getInt("id_partido");
            String nome = rs.getString("nome");
            String urlFoto = rs.getString("url_foto");
            String email = rs.getString("email");

            deputado = new Deputados(oid,idp,nome,urlFoto,email);

        }
        return deputado;
    }

    @Override
    public List<Deputados> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.deputado";

        List<Deputados> DeputadoList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("id");
            int idp = rs.getInt("id_partido");
            String nome = rs.getString("nome");
            String urlFoto = rs.getString("url_foto");
            String email = rs.getString("email");
            Deputados deputado = new Deputados(oid,idp,nome,urlFoto,email);
            DeputadoList.add(deputado);
        }

        return DeputadoList;
    }
    @Override
    public int insert(Deputados deputado) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO camara.deputado (id,id_partido,nome,url_foto,email) VALUES(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,deputado.getId());
        ps.setInt(2,deputado.getId_partido());
        ps.setString(3, deputado.getNome());
        ps.setString(4, deputado.getUrlFoto());
        ps.setString(5, deputado.getEmail());

        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }
    @Override
    public int save(Deputados employee) throws SQLException {
        return 0;
    }

    @Override
    public int update(Deputados employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Deputados employee) {
        return 0;
    }
}
