package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.FileReader;
import java.sql.*;
import java.util.TreeMap;
import java.util.Map;
import java.util.Properties;

public class vrstafilmaDaoSQLImpl extends AbstractDao<vrstafilma> implements vrstafilmaDao {
    public vrstafilmaDaoSQLImpl(){
        super("vrstafilma");
    }
    @Override
    public vrstafilma row2object(ResultSet rs) throws filmoviException{
        try{
            vrstafilma v=new vrstafilma();
            v.setId(rs.getInt("id"));
            v.setZanr(rs.getString("zanr"));
            return v;
        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public Map<String,Object> object2row(vrstafilma v){
        Map<String,Object> m=new TreeMap<String,Object>();
        m.put("id",v.getId());
        m.put("zanr",v.getZanr());
        return m;
    }


}
