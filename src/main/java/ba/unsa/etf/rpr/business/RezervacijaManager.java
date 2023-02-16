package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.util.List;

public class RezervacijaManager {
    public List<Rezervacija> getAll() throws filmoviException {
        return DaoFactory.rezDao().getAll();
    }
    public Rezervacija add(Rezervacija add) throws filmoviException{
        return DaoFactory.rezDao().add(add);
    }
    public List<Rezervacija>getFiltered2(String a) throws filmoviException{
        return DaoFactory.rezDao().getFiltered2(a);
    }
    public void delete(int a) throws filmoviException{
        DaoFactory.rezDao().delete(a);
    }
}
