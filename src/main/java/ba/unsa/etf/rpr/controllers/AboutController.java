package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {

    public TextArea tekst;
    Parent root;
    Stage stage;
    Scene scene;

    public void initialize() throws filmoviException{
        tekst.setEditable(false);
    }

    public void log(ActionEvent actionEvent) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
