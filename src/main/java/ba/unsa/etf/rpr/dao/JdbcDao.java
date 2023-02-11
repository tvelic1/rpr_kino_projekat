package ba.unsa.etf.rpr.dao;

import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcDao {


    public static boolean validate(String emailId, String password) throws SQLException, IOException, ClassNotFoundException {


        FileReader fr=new FileReader("src/main/resources/db.properties");
        Properties p=new Properties();
        p.load(fr);
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));

             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GLEDATELJI WHERE email = ? and password = ?")) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
    public static void insertRecord(String fullName, String emailId, String password) throws SQLException, IOException, ClassNotFoundException {
        FileReader fr=new FileReader("src/main/resources/db.properties");
        Properties p=new Properties();
        p.load(fr);
        try (Connection connection = DriverManager
                .getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));

             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GLEDATELJI (IMEPREZIME, email, password) VALUES (?, ?, ?)")) {
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

    public static void deleteCategory(String name) throws IOException, ClassNotFoundException {
        FileReader fr=new FileReader("src/main/resources/db.properties");
        Properties p=new Properties();
        p.load(fr);
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vrstafilma WHERE zanr=?")) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    }







