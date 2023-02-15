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
    public List<filmovi>getFiltered(String a) throws filmoviException{
        return DaoFactory.filmDao().getFiltered(a);
    }
    public List<filmovi>search(String text)throws filmoviException{
        return DaoFactory.filmDao().searchByName(text);
    }
    public void delete (int id) throws filmoviException{
        DaoFactory.filmDao().delete(id);

    }
    public filmovi getById(int id) throws filmoviException{
        return DaoFactory.filmDao().getById(id);

    }
    public filmovi update(filmovi f) throws filmoviException{
        return DaoFactory.filmDao().update(f);

    }
    public filmovi add(filmovi f) throws filmoviException{
        return DaoFactory.filmDao().add(f);
    }

    public String getZaanr(int a) throws filmoviException {
        return DaoFactory.filmDao().getZaanr(a);
    }
    public int getIdfilma (String ime) throws filmoviException{
        return DaoFactory.filmDao().getIdfilma(ime);
    }


}
