package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;

public class GledateljiManager {
    public boolean validate(String mail, String password){
        return DaoFactory.gledateljiDao().validate(mail, password);
    }
    public void insertRecord(String name, String mail, String password){
        DaoFactory.gledateljiDao().insertRecord(name, mail, password);
    }
}
