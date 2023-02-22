package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;

public interface vrstafilmaDao extends Dao<vrstafilma> {
     void deleteByName(String name) throws filmoviException;
}
