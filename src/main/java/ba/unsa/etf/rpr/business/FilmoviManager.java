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
    public filmovi getByID(int id) throws filmoviException{
        return DaoFactory.filmDao().getById(id);

    }
    public void update(filmovi f) throws filmoviException{
        DaoFactory.filmDao().update(f);

    }
    public filmovi add(filmovi f) throws filmoviException{
        return DaoFactory.filmDao().add(f);
    }



}
