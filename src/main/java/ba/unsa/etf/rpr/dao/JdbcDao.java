package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.filmovi;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.sql.*;

public class JdbcDao {
    private static final String DATABASE_URL = "jdbc:mysql://sql7.freemysqlhosting.net/sql7582892";
    private static final String DATABASE_USERNAME = "sql7582892";
    private static final String DATABASE_PASSWORD = "d6MUfiLahxx";
    private static final String INSERT_QUERY = "INSERT INTO GLEDATELJI (IMEPREZIME, email, password) VALUES (?, ?, ?)";
    private static final String INSERT1_QUERY = "INSERT INTO vrstafilma (zanr) VALUES (?)";
    private static final String SELECT_QUERY = "SELECT * FROM GLEDATELJI WHERE email = ? and password = ?";

    public boolean validate(String emailId, String password) throws SQLException {


        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);


             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }
    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void insert1Record(String fullName) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT1_QUERY)) {
            preparedStatement.setString(1, fullName);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public static void getIntoListView(ObservableList<String> value) throws SQLException{
       // Object PreparedStatement;
        try(Connection connection=DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM vrstafilma")){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String zaanr=rs.getString("zanr");
                value.add(zaanr);
            }

        }catch(SQLException e){
            printSQLException(e);
        }
    }
    public static void insertIntoCategory(String name){
        try(Connection con=DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);

            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO vrstafilma (zanr) VALUES (?)")) {
            preparedStatement.setString(1, name);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
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
    public static ObservableList<Integer> ab(ObservableList<Integer> a){
        try{

            Connection con=DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            PreparedStatement ps=con.prepareStatement("SELECT idfilma FROM filmovi");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                a.add(rs.getInt(1));

            } return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }







