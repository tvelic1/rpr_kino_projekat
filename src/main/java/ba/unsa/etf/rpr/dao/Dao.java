package ba.unsa.etf.rpr.dao;
import java.util.List;

public interface Dao<tip> {
    List<tip> getAll();
    void delete(int id);
    tip getById(int id);
    tip add(tip item);
    tip update(tip item);
}
