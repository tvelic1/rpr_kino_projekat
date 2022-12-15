package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import java.util.List;
public interface filmoviDao extends Dao<filmovi>{
    List<filmovi> searchByName(String name) throws filmoviException;
    List<filmovi> searchByVrsta(vrstafilma z) throws filmoviException;
}
