package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.SQLException;

public class GledateljiManager {
    public boolean validate(String mail, String password) throws SQLException {
        return DaoFactory.gledateljiDao().validate(mail, password);
    }
    public void insertRecord(String name, String mail, String password) throws SQLException {
        DaoFactory.gledateljiDao().insertRecord(name, mail, password);
    }
}
