package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class filmoviDaoSQLImpl extends AbstractDao<filmovi> implements filmoviDao {
    public filmoviDaoSQLImpl(){
        super("filmovi","idfilma");
    }
    private static filmoviDaoSQLImpl instance=null;
    public static filmoviDaoSQLImpl getInstance(){
        if(instance==null) instance=new filmoviDaoSQLImpl();
        return instance;
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
    public String getZaanr(int a) {
        try{
            PreparedStatement st=getCon().prepareStatement("SELECT zanr FROM vrstafilma WHERE idvrstafilma=?");
            st.setInt(1,a);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            } return "nema";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int getIdfilma(String a){
        try{
            PreparedStatement st=getCon().prepareStatement("SELECT idfilma FROM filmovi WHERE IME=?  ");
            st.setString(1,a);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            } return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public filmovi update(filmovi item ) throws filmoviException{

        try{
            PreparedStatement st= getCon().prepareStatement("UPDATE filmovi SET ime=?, ocjena=?,trajanje=? WHERE idfilma=?");
            st.setString(1, item.getIme());
            st.setString(2, item.getOcjena());
            st.setInt(3,item.getTrajanje());
            st.setInt(4,item.getId());
            st.executeUpdate();
            return item;


        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }

    }


}

