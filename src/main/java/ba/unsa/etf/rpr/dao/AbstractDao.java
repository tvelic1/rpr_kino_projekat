package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public  abstract  class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection con;
    private String tableName;

    public AbstractDao(String tableName){
        try{ this.tableName=tableName;
            FileReader fr=new FileReader("src/main/resources/db.properties");
            Properties p=new Properties();
            p.load(fr);
            String url=p.getProperty("url");
            String user =p.getProperty("user");
            String pass=p.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.con=DriverManager.getConnection(url,user,pass);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getCon(){
        return this.con;
    }
    public abstract T row2object(ResultSet rs) throws filmoviException;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws filmoviException{
        String query="SELECT * FROM "+tableName+"WHERE id=?";
        try{
            PreparedStatement st=this.con.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                T result=row2object(rs);
                rs.close();
                return result;
            }else{
                throw new filmoviException("Object not found!");
            }
        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }
    }
    public List<T> getAll() throws filmoviException{
        String q="SELECT * FROM "+tableName;
        List<T> results=new ArrayList<T>();
        try{
            PreparedStatement st=getCon().prepareStatement(q);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                T object=row2object(rs);
                results.add(object);
            }rs.close();
            return results;


        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }
    }

}
