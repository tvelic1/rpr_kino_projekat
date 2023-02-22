package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.filmoviException;
import java.util.List;
/**
 *
 * @author tvelic1
 *
 */

public interface Dao<tip> {
    List<tip> getAll() throws filmoviException;
    List<tip> getFiltered(String a) throws filmoviException;
    List<tip> getFiltered2(String a) throws filmoviException;
    void delete(int id) throws filmoviException;
   // void deleteByName(String id) throws filmoviException;
    tip getById(int id) throws filmoviException;
    tip add(tip item) throws filmoviException;
    tip update(tip item) throws filmoviException;


}
