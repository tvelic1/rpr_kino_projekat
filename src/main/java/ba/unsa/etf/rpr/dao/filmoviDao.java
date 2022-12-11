package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;
import java.util.List;
public interface filmoviDao extends Dao<filmovi>{
    List<filmovi> searchByName(String name);
    List<filmovi> searchByZanr(vrstafilma zanr);
}
