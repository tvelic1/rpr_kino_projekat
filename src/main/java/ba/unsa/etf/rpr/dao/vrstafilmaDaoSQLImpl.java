package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.TreeMap;
import java.util.Map;
import java.util.Properties;

public class vrstafilmaDaoSQLImpl extends AbstractDao<vrstafilma> implements vrstafilmaDao {
    public vrstafilmaDaoSQLImpl(){
        super("vrstafilma","idvrstafilma");
    }
    private static vrstafilmaDaoSQLImpl instance=null;
    public static vrstafilmaDaoSQLImpl getInstance(){
        if(instance==null) instance=new vrstafilmaDaoSQLImpl();
        return instance;
    }
    @Override
    public vrstafilma row2object(ResultSet rs) throws filmoviException{
        try{
            vrstafilma v=new vrstafilma();
            v.setId(rs.getInt("idvrstafilma"));
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
    public void deleteByName(String name) throws filmoviException{
        String q="DELETE FROM vrstafilma WHERE zanr=?";
        try{
            PreparedStatement st=getCon().prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,name);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }
    }



}
