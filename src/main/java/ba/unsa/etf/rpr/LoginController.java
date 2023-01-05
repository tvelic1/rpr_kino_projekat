package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {
    public Button submitButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField email;

    public void login(ActionEvent actionEvent){
        Window owner=submitButton.getScene().getWindow();

    }
    public void register(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
