package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GledateljiManager;
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



    public void about(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/about.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }


    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();
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

        GledateljiManager g=new GledateljiManager();

        if (!g.validate(emailId,password)) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid data");
            return;

        } else {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!",
                    "Welcome ");
            root = FXMLLoader.load(getClass().getResource("/kino.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);


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