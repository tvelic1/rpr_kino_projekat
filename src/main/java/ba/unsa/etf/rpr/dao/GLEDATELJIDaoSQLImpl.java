package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.GLEDATELJI;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.io.IOException;
import java.sql.*;
import java.util.*;




public class GLEDATELJIDaoSQLImpl extends AbstractDao<GLEDATELJI> implements  GLEDATELJIDao{

    public GLEDATELJIDaoSQLImpl(){
        super("GLEDATELJI","id");
    }
    @Override
    public GLEDATELJI row2object(ResultSet rs) throws filmoviException{
        try {
            GLEDATELJI g = new GLEDATELJI();
            g.setId(rs.getInt("id"));
            g.setIme(rs.getString("ime"));
            g.setId_film(DaoFactory.filmDao().getById(rs.getInt("id_film")));
            g.setEmail(rs.getString("email"));
            g.setPassword(rs.getString("password"));
            return g;

        }catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public Map<String,Object> object2row(GLEDATELJI gl) {
        Map<String,Object> item=new TreeMap<String,Object>();
        item.put("id",gl.getId());
        item.put("ime",gl.getIme());
        item.put("id_film",gl.getId_film().getId());
        item.put("email",gl.getEmail());
        item.put("password",gl.getPassword());
        return item;

    }
    @Override
    public List<GLEDATELJI> traziPoId(int id1,int id2) throws filmoviException{
        List<GLEDATELJI> lista=new ArrayList<>();
        try{
        String q="SELECT * FROM GLEDATELJI WHERE id BETWEEN ? AND ?";
        PreparedStatement st=getCon().prepareStatement(q);
        st.setObject(1,id1);
        st.setObject(2,id2);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            lista.add(row2object(rs));
        } rs.close(); return lista;}
        catch(SQLException e){
            throw new filmoviException(e.getMessage(),e);
        }
    }
    @Override
    public boolean validate(String mail, String password) throws SQLException {
        try {
            PreparedStatement stmt = getCon().prepareStatement("SELECT * FROM GLEDATELJI WHERE email = ? and password = ?");
            stmt.setString(1,mail);
            stmt.setString(2, password);

            System.out.println(stmt);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            printSQLException(e);

        } return false;}

    @Override
    public void insertRecord(String name, String mail, String password) throws SQLException {
        try {
            PreparedStatement stmt = getCon().prepareStatement("INSERT INTO GLEDATELJI (IMEPREZIME, email, password) VALUES (?, ?, ?)");
            stmt.setString(1,name);
            stmt.setString(2,mail);
            stmt.setString(3,password);

            System.out.println(stmt);

            stmt.executeUpdate();
        } catch (SQLException e) {

            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }

    }



