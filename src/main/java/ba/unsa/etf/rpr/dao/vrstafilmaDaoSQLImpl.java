package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class vrstafilmaDaoSQLImpl implements vrstafilmaDao {
    private Connection con;
    public vrstafilmaDaoSQLImpl(){
        try{
            FileReader fr=new FileReader("src/main/resources/db.properties");
            Properties p=new Properties();
            p.load(fr);
            String url=p.getProperty("url");
            String user =p.getProperty("user");
            String pass=p.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.con=DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override

        public vrstafilma getById(int id){
            String query="SELECT * FROM vrstafilma WHERE id = ?";
            try{
                PreparedStatement stmt=this.con.prepareStatement(query);
                stmt.setInt(1,id);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    vrstafilma vrsta=new vrstafilma();
                    vrsta.setId(rs.getInt("id"));
                    vrsta.setZanr(rs.getString("zanr"));
                    rs.close();
                    return vrsta;
                }else return null;


        } catch (SQLException e) {
                e.printStackTrace();
            } return null;
    }
    public vrstafilma add(vrstafilma v){
        String insert="INSERT INTO vrstafilma(zanr) VALUES(?)";
        try{
            PreparedStatement stmt=this.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,v.getZanr());
            stmt.executeUpdate();
            ResultSet rs=stmt.getGeneratedKeys(); rs.next();
            v.setId(rs.getInt(1)); return v;
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
    @Override

    public vrstafilma update(vrstafilma v){
        String insert="UPDATE vrstafilma SET zanr=? WHERE id=?";
        try{
            PreparedStatement stmt=this.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,v.getZanr());
            stmt.setObject(2,v.getId());
            stmt.executeUpdate();

             return v;
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }@Override
    public void delete(int id){
        String insert="DELETE FROM vrstafilma WHERE id=?";
        try{
            PreparedStatement stmt=this.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<vrstafilma>getAll(){
        String query="SELECT * FROM vrstafilma";
        List<vrstafilma> v=new ArrayList<vrstafilma>();
        try{
            PreparedStatement st=this.con.prepareStatement(query);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                vrstafilma vr=new vrstafilma();
                vr.setId(rs.getInt("id"));
                vr.setZanr(rs.getString("zanr"));
                v.add(vr);
            } rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } return v;
    }


}
