package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.GLEDATELJI;
import java.util.List;

public interface GLEDATELJIDao extends Dao<GLEDATELJI> {
    List<GLEDATELJI> traziPoId(int id1,int id2);
    //List<GLEDATELJI> trazipoImenu(String pocetak,String kraj);
}
