package ba.unsa.etf.rpr;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;






    public class RegisterController {
        @FXML
        private TextField nameField;

        @FXML
        private TextField emailField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private void handleSubmit() {
            String name = nameField.getText();
        }
    }

