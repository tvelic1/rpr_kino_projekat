package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final vrstafilmaDao vrstaDao= new vrstafilmaDaoSQLImpl();
    private static final GLEDATELJIDao gledateljiiDao=new GLEDATELJIDaoSQLImpl();
    private static final filmoviDao filmDao=new filmoviDaoSQLImpl();

    private DaoFactory(){
    }

    public static vrstafilmaDao vrstaDao(){
        return vrstaDao;
    }

    public static GLEDATELJIDao gledateljiDao(){
        return gledateljiiDao;
    }

    public static filmoviDao filmDao(){
        return filmDao;
    }

}
