package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.GLEDATELJI;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.util.List;

public interface GLEDATELJIDao extends Dao<GLEDATELJI> {
    List<GLEDATELJI> traziPoId(int id1,int id2)throws filmoviException;

    boolean validate(String mail, String password) ;
   void insertRecord(String name, String mail, String password) ;
}
