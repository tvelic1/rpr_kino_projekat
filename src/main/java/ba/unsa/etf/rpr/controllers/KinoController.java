package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KinoController  {
    public TextField tekst;
    @FXML
    public TableView tableview;
    public TableColumn<filmovi,Integer> id1;
    public TableColumn<filmovi,Integer> trajanje;
    public TableColumn<filmovi,String> ocjena;
    public TableColumn<filmovi,String>ime;

    public TextField text;
    public Button addbutton;

    public TextField trajanjee;
    public TextField zaanr;
    public TextField ocjenaa;
    public TextField imeee;

    private ObservableList<String> names;
    public Button closeButton;
    public ListView<vrstafilma> listView;
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

        id1.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleIntegerProperty(filmovi.getId()).asObject();
        });
        ocjena.setCellValueFactory(new PropertyValueFactory<filmovi, String>("ocjena"));
        trajanje.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleIntegerProperty(filmovi.getTrajanje()).asObject();});
        ime.setCellValueFactory(cellData->{filmovi filmovi=cellData.getValue(); return new SimpleStringProperty(filmovi.getIme());});

        tableview.setItems(FXCollections.observableList(manager.getAll()));

        listView.setItems(FXCollections.observableList(man.getAll()));
        //AtomicInteger a = new AtomicInteger();
        //AtomicInteger a = new AtomicInteger();
        listView.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
            if(n!=null){



                try {
                    tableview.setItems(FXCollections.observableList(manager.getFiltered(n.getZanr())));
                } catch (filmoviException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    







    public void addcat(ActionEvent actionEvent) throws IOException, ClassNotFoundException, filmoviException {
     vrstafilma v=new vrstafilma();
     List<vrstafilma> vr=new ArrayList<>(man.getAll());
     for(vrstafilma f: vr){
         if(f.getZanr().equals(tekst.getText())) throw new filmoviException("Vec postoji");
     }
     v.setZanr(tekst.getText());
     man.add(v);
     listView.setItems(FXCollections.observableList(man.getAll()));
     tekst.setText("");
    }

    public void search(ActionEvent actionEvent) throws filmoviException {
        ObservableList<filmovi>items=FXCollections.observableList(manager.search(text.getText()));
        tableview.setItems(items);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    CategoryManager man=new CategoryManager();
    FilmoviManager fii=new FilmoviManager();

    public void adddd(ActionEvent actionEvent) throws filmoviException {
        ObservableList<filmovi>itemss=FXCollections.observableList(manager.search(imeee.getText()));
        if(itemss.isEmpty()&&!zaanr.getText().isEmpty() && !ocjenaa.getText().isEmpty() && !trajanjee.getText().isEmpty() && !imeee.getText().isEmpty()){
        filmovi f=new filmovi();
        vrstafilma v=new vrstafilma();
        boolean e=false;
        v.setZanr(zaanr.getText());
        List<vrstafilma> cat=new ArrayList<>(man.getAll());
                for(vrstafilma vrr:cat){
                    if(vrr.getZanr().equals(v.getZanr())){
                        e=true;
                        v=vrr;
                        break;
                    }

                }
                if(!e) v=man.add(v);
           if(!zaanr.getText().isEmpty() && !ocjenaa.getText().isEmpty() && !trajanjee.getText().isEmpty() && !imeee.getText().isEmpty())
           {  f.setId_vrsta_filma(v);
                f.setOcjena(ocjenaa.getText());
                f.setTrajanje(Integer.parseInt(trajanjee.getText()));
                f.setIme(imeee.getText());
                fii.add(f);
                tableview.setItems(FXCollections.observableList(fii.getAll()));
                zaanr.setText("");
                ocjenaa.setText("");
                imeee.setText("");
                trajanjee.setText("");
           }}
    }

    public void clear(ActionEvent actionEvent) {
        trajanjee.setText("");
        zaanr.setText("");
        imeee.setText("");
        ocjenaa.setText("");
    }

    public void logoutt(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    } FilmoviManager fm=new FilmoviManager();

    public void delete(ActionEvent actionEvent) throws filmoviException, IOException, ClassNotFoundException {
       filmovi film = (filmovi) tableview.getSelectionModel().getSelectedItem();
       vrstafilma vm=listView.getSelectionModel().getSelectedItem();
       if(film!=null && vm==null){
       fm.delete(film.getId());
        tableview.setItems(FXCollections.observableList(fm.getAll()));}
    else if(vm!=null && film==null){
        man.delete1(vm.getZanr());
        listView.setItems(FXCollections.observableList(man.getAll()));

        }

      }


    public void load(ActionEvent actionEvent) throws filmoviException {
        tableview.setItems(FXCollections.observableList(fm.getAll()));
    }
}
