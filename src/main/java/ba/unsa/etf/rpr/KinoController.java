package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.BorderPane.*;

public class KinoController implements Initializable {
    private ObservableList<String> names;
    public Button closeButton;
    public ListView<String> listView;


    public void exit(ActionEvent actionEvent){
        System.exit(0);
    }
    public void close(ActionEvent actionEvent){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();


    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        names= FXCollections.observableArrayList();
        names.addAll("Horor");
        listView.setItems(names);
    }



    public void add(ActionEvent actionEvent) {
    }
}
