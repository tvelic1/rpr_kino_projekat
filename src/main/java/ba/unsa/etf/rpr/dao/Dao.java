package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Dao<tip> {
    List<tip> getAll() throws filmoviException;
    List<tip> getFiltered(String a) throws filmoviException;

    void delete(int id) throws filmoviException;
    void delete1(String id) throws filmoviException;
    tip getById(int id) throws filmoviException;
    tip add(tip item) throws filmoviException;
    tip update(tip item) throws filmoviException;
    //filmovi update(filmovi item) throws filmoviException;
}
