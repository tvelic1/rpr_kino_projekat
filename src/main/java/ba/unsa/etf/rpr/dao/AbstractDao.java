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

    public void delete(int id) throws filmoviException{
        String q="DELETE FROM "+tableName+" WHERE id=?";
        try{
            PreparedStatement st=getCon().prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
            st.setObject(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }
    }
    private String prepareUpdateParts(Map<String,Object>row){
        StringBuilder s=new StringBuilder();
        int brojac=0;
        for(Map.Entry<String,Object>entry:row.entrySet()){
            brojac++;
            if(entry.getKey().equals("id") || entry.getKey().equals("idfilma") ) continue;
            s.append(entry.getKey()).append("=?");
            if(row.size()!=brojac);
            s.append(",");

    } return s.toString();}

    private  Map.Entry<String,String> prepareInsertParts(Map<String,Object> row){
        StringBuilder s=new StringBuilder();
        StringBuilder q=new StringBuilder();
        int b=0;
        for(Map.Entry<String,Object> entry: row.entrySet()){
            b++;
            if(entry.getKey().equals("id") || entry.getKey().equals("idfilma")  ) continue;
            s.append(entry.getKey());
            q.append("?");
            if(row.size()!=b){
                s.append(","); q.append(",");
            }
        } return new AbstractMap.SimpleEntry<String,String>(s.toString(),q.toString());
    }

    public T add(T item) throws filmoviException{
        Map<String,Object> row=object2row(item);
        Map.Entry<String,String> c=prepareInsertParts(row);
        StringBuilder s=new StringBuilder();
        s.append("INSERT INTO ").append(tableName);
        s.append(" (").append(c.getKey()).append(") ");
        s.append("VALUES (").append(c.getValue()).append(") ");
        try{
            PreparedStatement st= getCon().prepareStatement(s.toString(),Statement.RETURN_GENERATED_KEYS);
            int br=1;
            for(Map.Entry<String,Object> entry: row.entrySet()){
                if(entry.getKey().equals("id") || entry.getKey().equals("idfilma") ) continue;
                st.setObject(br,entry.getValue());
                br++;
            }
            st.executeUpdate();
            ResultSet r=st.getGeneratedKeys();
            r.next();
            item.setId(r.getInt(1));
            return item;
        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }
    }
    public T update(T item ) throws filmoviException{
        Map<String,Object>row=object2row(item);
        String c=prepareUpdateParts(row);
        StringBuilder s=new StringBuilder();
        s.append("UPDATE ").append(tableName).append(" SET ").append(c).append(" WHERE id=?");
        try{
            PreparedStatement st= getCon().prepareStatement(s.toString(),Statement.RETURN_GENERATED_KEYS);
            int br=1;
            for(Map.Entry<String,Object> entry: row.entrySet()){
                if(entry.getKey().equals("id") || entry.getKey().equals("idfilma") || entry.getKey().equals("jmbg")) continue;
                st.setObject(br,entry.getValue());
                br++;
            }
            st.setObject(br+1,item.getId());
            st.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new filmoviException(e.getMessage(),e);
        }

    }
    public List<T> executeQuery(String q, Object[] param) throws filmoviException{
        try{
            PreparedStatement st= getCon().prepareStatement(q);
            if(param!=null){
                for(int i=0;i<param.length;i++)
                    st.setObject(i,param[i-1]);
            }
        }catch (SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    public T executeQueryUnique(String query,Object[] a) throws filmoviException{

    }

}
