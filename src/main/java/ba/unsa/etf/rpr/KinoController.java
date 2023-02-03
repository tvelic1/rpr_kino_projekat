package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.dao.JdbcDao;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.layout.BorderPane.*;

public class KinoController  {
    public TextField tekst;
    @FXML
    public TableView tableview;
    public TableColumn<filmovi,Integer> id1;
    public TableColumn<filmovi,Integer> trajanje;
    public TableColumn<filmovi,String> ocjena;
    public TableColumn<filmovi,String>ime;

    private ObservableList<String> names;
    public Button closeButton;
    public ListView<String> listView;
    public FilmoviManager manager=new FilmoviManager();


    public void exit(ActionEvent actionEvent){
        System.exit(0);
    }
    public void close(ActionEvent actionEvent){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();


    }



    @FXML
    public void initialize() throws filmoviException{
        //ime.setCellValueFactory(new PropertyValueFactory<filmovi, String>("IME"));
        id1.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleIntegerProperty(filmovi.getId()).asObject();
        });
        ocjena.setCellValueFactory(new PropertyValueFactory<filmovi, String>("ocjena"));
        trajanje.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleIntegerProperty(filmovi.getTrajanje()).asObject();});
        ime.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleStringProperty(filmovi.getIme());});

        tableview.setItems(FXCollections.observableList(manager.getAll()));
        names= FXCollections.observableArrayList();
        JdbcDao jdbc= new JdbcDao();
        try {
            jdbc.getIntoListView(names);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listView.setItems(names);
    }
    







    public void addcat(ActionEvent actionEvent) {
        //Object tekst;
        names.add(tekst.getText());
        JdbcDao j=new JdbcDao();
        j.insertIntoCategory(tekst.getText());
    }
}
