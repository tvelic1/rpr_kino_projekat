package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.GLEDATELJI;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class rezervacijaDaoSQLImpl extends AbstractDao<Rezervacija> implements rezervacijaDao {
    public rezervacijaDaoSQLImpl() {
        super("Rezervacija", "idRezervacija");
    }
    private static rezervacijaDaoSQLImpl instance=null;
    public static rezervacijaDaoSQLImpl getInstance(){
        if(instance==null) instance=new rezervacijaDaoSQLImpl();
        return instance;
    }

    @Override
    public Rezervacija row2object(ResultSet rs) throws filmoviException {
        try {
            Rezervacija g = new Rezervacija();
            g.setId(rs.getInt("idRezervacija"));
            g.setImee(rs.getString("ImeE"));
            g.setIdfilm(DaoFactory.filmDao().getById(rs.getInt("idfilm")));
            g.setPrezime(rs.getString("Prezime"));
            return g;

        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Rezervacija gl) {
        Map<String,Object> item=new TreeMap<String,Object>();
        item.put("idRezervacija",gl.getId());
        item.put("Imee",gl.getImee());
        item.put("idfilm",gl.getIdfilm().getId());
        item.put("Prezime",gl.getPrezime());
       // item.put("password",gl.getPassword());
        return item;
    }
}
