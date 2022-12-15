package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import java.util.List;

public interface Dao<tip> {
    List<tip> getAll() throws filmoviException;
    void delete(int id) throws filmoviException;
    tip getById(int id) throws filmoviException;
    tip add(tip item) throws filmoviException;
    tip update(tip item) throws filmoviException;
}
