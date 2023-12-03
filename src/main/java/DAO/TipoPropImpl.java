package DAO;

import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.TipoProposicao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPropImpl implements TipoPropDAO{
    @Override
    public TipoProposicao get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TipoProposicao> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM camara.proposicao_tipo";

        List<TipoProposicao> proposicaoTipoList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int oid = rs.getInt("cod");
            String sigla = rs.getString("sigla");
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            TipoProposicao tipoProposicao = new TipoProposicao(oid,sigla,nome,descricao);
            proposicaoTipoList.add(tipoProposicao);
        }

        return proposicaoTipoList;
    }

    @Override
    public int save(TipoProposicao employee) throws SQLException {
        return 0;
    }

    @Override
    public int insert(TipoProposicao tipoProposicao) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO camara.proposicao_tipo (cod,sigla,nome,descricao) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,tipoProposicao.getCod());
        ps.setString(2, tipoProposicao.getSigla());
        ps.setString(3, tipoProposicao.getNome());
        ps.setString(4, tipoProposicao.getDescricao());

        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    @Override
    public int update(TipoProposicao employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(TipoProposicao employee) {
        return 0;
    }
}
