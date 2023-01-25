package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Properties;
import java.sql.ResultSet;

public class filmoviDaoSQLImpl extends AbstractDao<filmovi> implements filmoviDao {
    public filmoviDaoSQLImpl(){
        super("filmovi");
    }
    @Override
    public filmovi row2object(ResultSet rs) throws filmoviException{
        try{
            filmovi f=new filmovi();
            f.setId(rs.getInt("idfilma"));
            f.setIme(rs.getString("ime"));
            f.setTrajanje(rs.getInt("trajanje"));
            f.setOcjena(rs.getString("ocjena"));
            f.setId_vrsta_filma(DaoFactory.vrstaaDao().getById(rs.getInt("id_vrsta_filma")));
            return f;
        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public Map<String,Object> object2row(filmovi f){
        Map<String,Object> item=new TreeMap<String,Object>();
        item.put("id_vrsta_filma", f.getId_vrsta_filma().getId());
        item.put("idfilma",f.getId());
        item.put("ime",f.getIme());
        item.put("ocjena",f.getOcjena());
        item.put("trajanje",f.getTrajanje());
        return item;
    }
    @Override
    public List<filmovi> searchByName(String name) throws filmoviException{
        String q="SELECT * FROM filmovi WHERE ime LIKE concat('%', ? ,'%')";
        try{
            PreparedStatement st=getCon().prepareStatement(q);
            st.setString(1,name);
            ResultSet rs=st.executeQuery();
            ArrayList<filmovi> lista=new ArrayList<>();
            while(rs.next()){
                lista.add(row2object(rs));
            } return lista;
        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public List<filmovi> searchByCategory(vrstafilma v) throws filmoviException{
        String q="SELECT * FROM filmovi WHERE id_vrsta_filma=?";
        try{
            PreparedStatement st=getCon().prepareStatement(q);
            st.setInt(1,v.getId());
            ResultSet r=st.executeQuery();
            ArrayList<filmovi> lista= new ArrayList<>();
            while(r.next()){
                lista.add(row2object(r));
            } return lista;
        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public filmovi randomFilm() throws filmoviException{
        return executeQueryUnique("SELECT * FROM filmovi ORDER BY RAND() LIMIT 1", null);

    }


    }

