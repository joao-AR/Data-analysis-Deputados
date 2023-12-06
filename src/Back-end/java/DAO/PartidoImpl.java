package DAO;

import com.example.dataAnalysisDeputados.entity.Partidos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoImpl implements PartidoDAO {

    @Override
    public Partidos get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Partidos partido = null;

        String sql = "Select * from  camara.partido where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("id");
            String sigla = rs.getString("sigla");
            String nome = rs.getString("nome");

            partido = new Partidos(oid,sigla,nome);

        }
        return partido;
    }

    @Override
    public List<Partidos> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.partido";

        List<Partidos> Partidos = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("id");
            String sigla = rs.getString("sigla");
            String nome = rs.getString("nome");
            Partidos partido = new Partidos(oid,sigla,nome);
            Partidos.add(partido);
        }

        return Partidos;
    }

    @Override
    public int insert(Partidos partido) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO camara.partido (id,sigla,nome) VALUES(?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,partido.getId());
        ps.setString(2, partido.getSigla());
        ps.setString(3, partido.getNome());

        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    @Override
    public int save(Partidos employee) throws SQLException {
        return 0;
    }

    @Override
    public int update(Partidos employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Partidos employee) {
        return 0;
    }
}
