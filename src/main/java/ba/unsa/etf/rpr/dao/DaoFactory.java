package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final vrstafilmaDao vrstaDao= vrstafilmaDaoSQLImpl.getInstance();
    private static final GLEDATELJIDao gledateljiiDao=GLEDATELJIDaoSQLImpl.getInstance();
    private static final filmoviDao filmDao=filmoviDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static vrstafilmaDao vrstaaDao(){
        return vrstaDao;
    }

    public static GLEDATELJIDao gledateljiDao(){
        return gledateljiiDao;
    }

    public static filmoviDao filmDao(){
        return filmDao;
    }

}
