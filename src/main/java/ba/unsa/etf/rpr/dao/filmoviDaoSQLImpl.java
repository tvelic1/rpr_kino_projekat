package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class filmoviDaoSQLImpl implements filmoviDao {
    private Connection con;
    public filmoviDaoSQLImpl(){
           try{
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
        @Override
    public filmovi getById(int id){
        String query="SELECT * FROM quotes WHERE id=?";
        try{
            PreparedStatement st=this.con.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                filmovi film=new filmovi();
                film.setIdfilma(rs.getInt("idfilma"));
                film.setIme(rs.getString("ime"));
                film.setOcjena(rs.getString("ocjena"));
                film.setTrajanje(rs.getInt("trajanje"));
                rs.close();
                return film;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();

        }return null;
        }
    }

