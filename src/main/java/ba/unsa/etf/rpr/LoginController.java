package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.JdbcDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {
    public Button submitButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailIdField;


    public void register(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid data");
            return;

        } else {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                    "Welcome ");

        }
    }


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}