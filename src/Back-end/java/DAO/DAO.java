package DAO;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    int save(T employee) throws SQLException;
    int insert(T employee) throws SQLException;
    int update (T employee) throws SQLException;
    int delete(T employee);
}
