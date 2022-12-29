package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.util.List;


public class FilmoviManager  {
    public List<filmovi>getAll() throws filmoviException{
        return DaoFactory.filmDao().getAll();
    }
    public List<filmovi>search(String text)throws filmoviException{
        return DaoFactory.filmDao().searchByName(text);
    }
    public void delete (int id) throws filmoviException{
        DaoFactory.filmDao().delete(id);

    }
}
